package br.com.agenda.ui.activity;

import static br.com.agenda.ui.activity.ConstantsActivities.KEY_CONTACT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.agenda.R;
import br.com.agenda.dao.ContactDao;
import br.com.agenda.model.Contact;

public class FormContactActivity extends AppCompatActivity {

    private static final ContactDao CONTACT_DAO = new ContactDao();
    private static final String TITLE_NEW_CONTACT = "New Contact";
    private static final String TITLE_EDIT_CONTACT = "Edit Contact";
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contact);
        initViews();
        initContact();
        configSaveButton();
    }

    private void initContact() {
        final Intent intent = getIntent();
        if (intent.hasExtra(KEY_CONTACT.name())) {
            setTitle(TITLE_EDIT_CONTACT);
            contact = (Contact) intent.getSerializableExtra(KEY_CONTACT.name());
            fillViewWithContact();
        } else {
            setTitle(TITLE_NEW_CONTACT);
            contact = new Contact();
        }
    }

    private void initViews() {
        nameTextView = findViewById(R.id.activity_form_new_contact_name);
        phoneTextView = findViewById(R.id.activity_form_new_contact_phone);
        emailTextView = findViewById(R.id.activity_form_new_contact_email);
    }

    private void configSaveButton() {
        final View btnSave = findViewById(R.id.activity_form_new_contact_btn_save);
        btnSave.setOnClickListener(view -> {
            fillContactWithView();
            CONTACT_DAO.save(contact);
            finish();
        });
    }

    private void fillViewWithContact() {
        nameTextView.setText(contact.getName());
        phoneTextView.setText(contact.getPhone());
        emailTextView.setText(contact.getEmail());
    }

    private void fillContactWithView() {
        contact.setName(nameTextView.getText().toString());
        contact.setPhone(phoneTextView.getText().toString());
        contact.setEmail(emailTextView.getText().toString());
    }
}