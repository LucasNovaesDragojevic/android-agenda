package br.com.agenda;

import android.app.Application;

import br.com.agenda.dao.ContactDao;
import br.com.agenda.model.Contact;

public class ContactsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createExampleContact();
    }

    private void createExampleContact() {
        Contact contact = new Contact();
        contact.setName("Example");
        contact.setPhone("00 0000-0000");
        contact.setEmail("example@email.com");
        ContactDao contactDao = new ContactDao();
        contactDao.save(contact);
    }
}
