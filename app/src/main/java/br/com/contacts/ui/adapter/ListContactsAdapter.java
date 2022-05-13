package br.com.contacts.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.contacts.R;
import br.com.contacts.db.dao.PhoneDao;
import br.com.contacts.model.Contact;
import br.com.contacts.model.Phone;

public class ListContactsAdapter extends BaseAdapter {

    private final List<Contact> contacts;
    private final Context context;
    private final PhoneDao phoneDao;

    public ListContactsAdapter(Context context, List<Contact> contacts, PhoneDao phoneDao) {
        this.context = context;
        this.contacts = contacts;
        this.phoneDao = phoneDao;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int id) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewCreated = LayoutInflater
                .from(context)
                .inflate(R.layout.item_contact, viewGroup, false);
        Contact contact = contacts.get(position);
        TextView nameTextView = viewCreated.findViewById(R.id.item_contact_name);
        nameTextView.setText(contact.getName());
        TextView phoneTextView = viewCreated.findViewById(R.id.item_contact_phone);
        final Phone phone = phoneDao.getFirstContactPhone(contact.getId());
        phoneTextView.setText(phone.getNumber());
        return viewCreated;
    }

    public void update(List<Contact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    public void remove(Contact contact) {
        this.contacts.remove(contact);
        notifyDataSetChanged();
    }
}
