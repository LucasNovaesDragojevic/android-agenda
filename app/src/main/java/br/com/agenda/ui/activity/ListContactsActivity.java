package br.com.agenda.ui.activity;

import static br.com.agenda.ui.activity.ConstantsActivities.KEY_CONTACT;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.agenda.R;
import br.com.agenda.dao.ContactDao;
import br.com.agenda.model.Contact;
import br.com.agenda.ui.adapter.ListContactsAdapter;

public class ListContactsActivity extends AppCompatActivity {

    private static final ContactDao CONTACT_DAO = new ContactDao();
    private static final String TITLE = "Contacts";
    private ListView contactsListView;
    private List<Contact> contacts;
    private ListContactsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITLE);
        setContentView(R.layout.activity_list_contacts);
        contactsListView = findViewById(R.id.activity_list_contacts_listview);
        configAdapter();
        contacts = CONTACT_DAO.getAll();
        configNewContactButton();
        configClickOnItemList();
        registerForContextMenu(contactsListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_contacts_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_list_contacts_menu_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Contact contact = adapter.getItem(menuInfo.position);
            CONTACT_DAO.remove(contact);
            adapter.remove(contact);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.update(contacts);
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
        adapter = new ListContactsAdapter(this);
        contactsListView.setAdapter(adapter);
    }
}
