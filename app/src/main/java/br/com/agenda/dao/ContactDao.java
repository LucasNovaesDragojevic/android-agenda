package br.com.agenda.dao;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.agenda.model.Contact;

public final class ContactDao {

    private static final List<Contact> CONTACTS = new ArrayList<>();

    public final void save(Contact contact) {
        CONTACTS.add(contact);
    }

    @NonNull
    public final List<Contact> getAll() {
        return Collections.unmodifiableList(CONTACTS);
    }

}
