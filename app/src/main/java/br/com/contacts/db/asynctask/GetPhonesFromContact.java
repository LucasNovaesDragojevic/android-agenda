package br.com.contacts.db.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.contacts.db.dao.PhoneDao;
import br.com.contacts.model.Phone;

public class GetPhonesFromContact extends AsyncTask<Void, Void, List<Phone>> {

    private final PhoneDao phoneDao;
    private final Long contactId;
    private final PhonesFoundListener listener;

    public GetPhonesFromContact(PhoneDao phoneDao, Long contactId, PhonesFoundListener listener) {
        this.phoneDao = phoneDao;
        this.contactId = contactId;
        this.listener = listener;
    }

    @Override
    protected List<Phone> doInBackground(Void... voids) {
        return phoneDao.getPhonesFromContact(contactId);
    }

    @Override
    protected void onPostExecute(List<Phone> phones) {
        super.onPostExecute(phones);
        listener.whenFounded(phones);
    }

    public interface PhonesFoundListener {
        void whenFounded(List<Phone> phones);
    }
}
