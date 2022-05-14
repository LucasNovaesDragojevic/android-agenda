package br.com.contacts.db.asynctask;

import android.os.AsyncTask;

import br.com.contacts.db.dao.ContactDao;
import br.com.contacts.db.dao.PhoneDao;
import br.com.contacts.function.LinkContactWithPhones;
import br.com.contacts.model.Contact;
import br.com.contacts.model.Phone;

public class SaveContact extends AsyncTask<Void, Void, Void> {

    private ContactDao contactDao;
    private PhoneDao phoneDao;
    private Contact contact;
    private Phone homePhone;
    private Phone mobilePhone;
    private final Runnable listener;

    public SaveContact(ContactDao contactDao, PhoneDao phoneDao, Contact contact, Phone homePhone, Phone mobilePhone, Runnable listener) {
        this.contactDao = contactDao;
        this.phoneDao = phoneDao;
        this.contact = contact;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        final Long contactId = contactDao.create(contact);
        LinkContactWithPhones.execute(contactId, homePhone, mobilePhone);
        phoneDao.save(homePhone, mobilePhone);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.run();
    }
}
