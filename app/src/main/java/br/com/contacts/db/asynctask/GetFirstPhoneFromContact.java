package br.com.contacts.db.asynctask;

import android.os.AsyncTask;

import br.com.contacts.db.dao.PhoneDao;
import br.com.contacts.model.Phone;

public class GetFirstPhoneFromContact extends AsyncTask<Void, Void, Phone> {

    private final PhoneDao phoneDao;
    private final Long contactId;
    private final PhoneFoundListener listener;

    public GetFirstPhoneFromContact(PhoneDao phoneDao, Long contactId, PhoneFoundListener listener) {
        this.phoneDao = phoneDao;
        this.contactId = contactId;
        this.listener = listener;
    }

    @Override
    protected Phone doInBackground(Void... voids) {
        return phoneDao.getFirstContactPhone(contactId);
    }

    @Override
    protected void onPostExecute(Phone phones) {
        super.onPostExecute(phones);
        listener.whenFounded(phones);
    }

    public interface PhoneFoundListener {
        void whenFounded(Phone phone);
    }
}
