package br.com.contacts.db.asynctask;

import android.os.AsyncTask;

import br.com.contacts.db.dao.ContactDao;
import br.com.contacts.model.Contact;
import br.com.contacts.ui.adapter.ListContactsAdapter;

public class RemoveContact extends AsyncTask<Void, Void, Void> {

    private final Contact contact;
    private final ContactDao contactDao;
    private final ListContactsAdapter adapter;

    public RemoveContact(Contact contact, ContactDao contactDao, ListContactsAdapter adapter) {
        this.contact = contact;
        this.contactDao = contactDao;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        contactDao.delete(contact);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        adapter.remove(contact);
    }
}
