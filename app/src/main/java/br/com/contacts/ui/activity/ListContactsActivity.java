package br.com.contacts.ui.activity;

import static br.com.contacts.ui.activity.ConstantsActivities.KEY_CONTACT;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.contacts.R;
import br.com.contacts.db.ApplicationDatabase;
import br.com.contacts.db.asynctask.GetAllContacts;
import br.com.contacts.db.asynctask.RemoveContact;
import br.com.contacts.db.dao.ContactDao;
import br.com.contacts.db.dao.PhoneDao;
import br.com.contacts.model.Contact;
import br.com.contacts.ui.adapter.ListContactsAdapter;

public class ListContactsActivity extends AppCompatActivity {

    private static final String TITLE = "Contacts";
    private static ContactDao CONTACT_DAO;
    private static PhoneDao PHONE_DAO;
    private ListView contactsListView;
    private List<Contact> contacts = new ArrayList<>();
    private ListContactsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITLE);
        setContentView(R.layout.activity_list_contacts);
        configDao();
        configListView();
        configAdapter();
        configNewContactButton();
        configClickOnItemList();
        registerForContextMenu(contactsListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetAllContacts(CONTACT_DAO, contactsFounded -> {
            contacts.clear();
            contacts.addAll(contactsFounded);
            adapter.update(contacts);
        }).execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_contacts_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == R.id.activity_list_contacts_menu_remove)
            configRemoveContactDialog(item);
        return super.onContextItemSelected(item);
    }

    private void configDao() {
        final ApplicationDatabase database = ApplicationDatabase.getInstance(this);
        CONTACT_DAO = database.getContactDao();
        PHONE_DAO = database.getPhoneDao();
    }

    private void configListView() {
        contactsListView = findViewById(R.id.activity_list_contacts_listview);
    }

    private void configRemoveContactDialog(@NonNull final MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Remove contact")
                .setMessage("Are you sure you want to remove this contact?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    final Contact contact = adapter.getItem(menuInfo.position);
                    new RemoveContact(contact, CONTACT_DAO, adapter).execute();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void configNewContactButton() {
        final View btnNewContact = findViewById(R.id.activity_list_contacts_fab_new_contact);
        btnNewContact.setOnClickListener(view -> {
            final Intent intent = new Intent(ListContactsActivity.this, FormContactActivity.class);
            startActivity(intent);
        });
    }

    private void configClickOnItemList() {
        contactsListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Contact contact = contacts.get(position);
            Intent goToFormActivity = new Intent(ListContactsActivity.this, FormContactActivity.class);
            goToFormActivity.putExtra(KEY_CONTACT.name(), contact);
            startActivity(goToFormActivity);
        });
    }

    private void configAdapter() {
        adapter = new ListContactsAdapter(this, new ArrayList<>(), PHONE_DAO);
        contactsListView.setAdapter(adapter);
    }
}
