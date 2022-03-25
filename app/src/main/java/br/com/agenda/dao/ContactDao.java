package br.com.agenda.dao;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.agenda.model.Contact;

public final class ContactDao {

    private static final List<Contact> CONTACTS;

    static {
         CONTACTS = new ArrayList<>();
         CONTACTS.add(new Contact("Jorge", "1234567890", "jorge@email.com"));
         CONTACTS.add(new Contact("Roberta", "0123456789", "roberta@email.com"));
    }

    @NonNull
    public final List<Contact> getAll() {
        return Collections.unmodifiableList(CONTACTS);
    }

    public final void save(@NonNull Contact contact) {
        if (CONTACTS.contains(contact)) {
            update(contact);
        } else {
            CONTACTS.add(contact);
        }
    }

    private void update(Contact contact) {
        int index = CONTACTS.indexOf(contact);
        CONTACTS.set(index, contact);
    }
}
