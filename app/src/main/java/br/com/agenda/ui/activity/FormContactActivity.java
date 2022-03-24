package br.com.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.agenda.R;
import br.com.agenda.dao.ContactDao;
import br.com.agenda.model.Contact;

public class FormContactActivity extends AppCompatActivity {

    private static final ContactDao CONTACT_DAO = new ContactDao();
    private static final String TITLE = "New Contact";
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITLE);
        setContentView(R.layout.activity_form_contact);
        initViews();
        configSaveButton();
    }

    private void initViews() {
        nameTextView = findViewById(R.id.activity_form_new_contact_name);
        phoneTextView = findViewById(R.id.activity_form_new_contact_phone);
        emailTextView = findViewById(R.id.activity_form_new_contact_email);
    }

    private void configSaveButton() {
        final View btnSave = findViewById(R.id.activity_form_new_contact_btn_save);
        btnSave.setOnClickListener(view -> {
            final String name = nameTextView.getText().toString();
            final String phone = phoneTextView.getText().toString();
            final String email = emailTextView.getText().toString();
            final Contact contact = new Contact(name, phone, email);
            CONTACT_DAO.save(contact);
            finish();
        });
    }
}