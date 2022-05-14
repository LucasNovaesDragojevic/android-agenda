package br.com.contacts.db.asynctask;

import static br.com.contacts.model.PhoneType.HOME;

import android.os.AsyncTask;

import java.util.List;

import br.com.contacts.db.dao.ContactDao;
import br.com.contacts.db.dao.PhoneDao;
import br.com.contacts.function.LinkContactWithPhones;
import br.com.contacts.model.Contact;
import br.com.contacts.model.Phone;
import br.com.contacts.model.PhoneType;

public class UpdateContact extends AsyncTask <Void, Void, Void> {

    private ContactDao contactDao;
    private PhoneDao phoneDao;
    private Contact contact;
    private Phone homePhone;
    private Phone mobilePhone;
    private List<Phone> contactPhones;
    private Runnable listener;

    public UpdateContact(ContactDao contactDao, PhoneDao phoneDao, Contact contact, Phone homePhone, Phone mobilePhone, List<Phone> contactPhones, Runnable listener) {
        this.contactDao = contactDao;
        this.phoneDao = phoneDao;
        this.contact = contact;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.contactPhones = contactPhones;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        contactDao.update(contact);
        LinkContactWithPhones.execute(contact.getId(), homePhone, mobilePhone);
        updatePhones();
        phoneDao.save(homePhone, mobilePhone);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.run();
    }

    private void updatePhones() {
        for (Phone phone : contactPhones)
            if (phone.getType().equals(HOME))
                homePhone.setId(phone.getId());
            else
                mobilePhone.setId(phone.getId());

    }
}
