package br.com.agenda.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.agenda.dao.ContactDao;
import br.com.agenda.model.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();

    public static ContactsDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, ContactsDatabase.class, "Contacts.db")
                    .allowMainThreadQueries()
                    .build();
    }
}
