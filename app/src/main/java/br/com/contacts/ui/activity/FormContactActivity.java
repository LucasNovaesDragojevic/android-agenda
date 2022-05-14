package br.com.contacts.ui.activity;

import static br.com.contacts.model.PhoneType.HOME;
import static br.com.contacts.model.PhoneType.MOBILE;
import static br.com.contacts.ui.activity.ConstantsActivities.KEY_CONTACT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.contacts.R;
import br.com.contacts.db.ApplicationDatabase;
import br.com.contacts.db.asynctask.GetPhonesFromContact;
import br.com.contacts.db.asynctask.SaveContact;
import br.com.contacts.db.asynctask.UpdateContact;
import br.com.contacts.db.dao.ContactDao;
import br.com.contacts.db.dao.PhoneDao;
import br.com.contacts.model.Contact;
import br.com.contacts.model.Phone;

public class FormContactActivity extends AppCompatActivity {

    private static final String TITLE_NEW_CONTACT = "New Contact";
    private static final String TITLE_EDIT_CONTACT = "Edit Contact";
    private static ContactDao CONTACT_DAO;
    private static PhoneDao PHONE_DAO;
    private final List<Phone> phones = new ArrayList<>();
    private TextView nameTextView;
    private TextView homePhoneTextView;
    private TextView mobilePhoneTextView;
    private TextView emailTextView;
    private Contact contact;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contact);
        final ApplicationDatabase database = ApplicationDatabase.getInstance(this);
        CONTACT_DAO = database.getContactDao();
        PHONE_DAO = database.getPhoneDao();
        intent = getIntent();
        initViews();
        initContact();
        initPhones();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_form_contact_menu_item_save) {
            fillContactWithView();
            final Phone homePhone = new Phone(homePhoneTextView.getText().toString(), HOME);
            final Phone mobilePhone = new Phone(mobilePhoneTextView.getText().toString(), MOBILE);
            if (contact.getId() != null)
                new UpdateContact(CONTACT_DAO, PHONE_DAO, contact, homePhone, mobilePhone, phones, this::finish).execute();
            else
                new SaveContact(CONTACT_DAO, PHONE_DAO, contact, homePhone, mobilePhone, this::finish).execute();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        nameTextView = findViewById(R.id.activity_form_new_contact_name);
        homePhoneTextView = findViewById(R.id.activity_form_new_contact_phone_home);
        mobilePhoneTextView = findViewById(R.id.activity_form_new_contact_phone_mobile);
        emailTextView = findViewById(R.id.activity_form_new_contact_email);
    }

    private void initContact() {
        if (intent.hasExtra(KEY_CONTACT.name())) {
            setTitle(TITLE_EDIT_CONTACT);
            contact = (Contact) intent.getSerializableExtra(KEY_CONTACT.name());
            fillViewWithContact();
        } else {
            setTitle(TITLE_NEW_CONTACT);
            contact = new Contact();
        }
    }

    private void initPhones() {
        if (intent.hasExtra(KEY_CONTACT.name())) {
            new GetPhonesFromContact(PHONE_DAO, contact.getId(), phones -> {
                this.phones.addAll(phones);
                fillViewWithPhone();
            }).execute();
        }
    }

    private void fillViewWithContact() {
        nameTextView.setText(contact.getName());
        emailTextView.setText(contact.getEmail());
    }

    private void fillViewWithPhone() {
        for (Phone phone : phones)
            if (phone.getType().equals(HOME))
                homePhoneTextView.setText(phone.getNumber());
            else
                mobilePhoneTextView.setText(phone.getNumber());
    }

    private void fillContactWithView() {
        contact.setName(nameTextView.getText().toString());
        contact.setEmail(emailTextView.getText().toString());
    }
}