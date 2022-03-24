package br.com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.agenda.R;
import br.com.agenda.dao.ContactDao;
import br.com.agenda.model.Contact;

public class ListContactsActivity extends AppCompatActivity {

    private static final ContactDao CONTACT_DAO = new ContactDao();
    private static final String TITLE = "Contacts";
    private ListView contactsListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITLE);
        setContentView(R.layout.activity_list_contacts);
        initViews();
        configNewContactButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showListOfContacts();
    }

    private void initViews() {
        contactsListView = findViewById(R.id.activity_list_contacts_listview);
    }

    private void configNewContactButton() {
        final View btnNewContact = findViewById(R.id.activity_list_contacts_fab_new_contact);
        btnNewContact.setOnClickListener(view -> {
            final Intent intent = new Intent(ListContactsActivity.this, FormContactActivity.class);
            startActivity(intent);
        });
    }

    private void showListOfContacts() {
        final List<Contact> contacts = CONTACT_DAO.getAll();
        contactsListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts));
    }
}
