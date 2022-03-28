package br.com.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.agenda.R;
import br.com.agenda.model.Contact;

public class ListContactsAdapter extends BaseAdapter {

    private final List<Contact> contacts = new ArrayList<>();
    private final Context context;

    public ListContactsAdapter(Context context) {
        this.context = context;
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
        TextView name = viewCreated.findViewById(R.id.item_contact_name);
        name.setText(contact.getName());
        TextView phone = viewCreated.findViewById(R.id.item_contact_phone);
        phone.setText(contact.getPhone());
        return viewCreated;
    }

    public void addAll(List<Contact> contacts) {
        this.contacts.addAll(contacts);
    }

    public void clear() {
        this.contacts.clear();
    }

    public void remove(Contact contact) {
        this.contacts.remove(contact);
    }
}
