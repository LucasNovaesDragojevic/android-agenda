package br.com.contacts.db.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.contacts.db.dao.ContactDao;
import br.com.contacts.model.Contact;

public class GetAllContacts extends AsyncTask<Void, Void, List<Contact>> {

    private final ContactDao contactDao;
    private final ContactsFoundedListener contactsFoundedListener;

    public GetAllContacts(ContactDao contactDao, ContactsFoundedListener contactsFoundedListener) {
        this.contactDao = contactDao;
        this.contactsFoundedListener = contactsFoundedListener;
    }

    @Override
    protected List<Contact> doInBackground(Void... voids) {
        return contactDao.readAll();
    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        super.onPostExecute(contacts);
        contactsFoundedListener.whenContactsFounded(contacts);
    }

    public interface ContactsFoundedListener {
        void whenContactsFounded(List<Contact> contactsFounded);
    }
}
