package br.com.agenda.db;

import static br.com.agenda.db.migration.Migrations.MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.agenda.db.dao.ContactDao;
import br.com.agenda.db.converter.CalendarConverter;
import br.com.agenda.model.Contact;

@Database(entities = {Contact.class},
          version = 4,
          exportSchema = false)
@TypeConverters(CalendarConverter.class)
public abstract class ContactsDatabase extends RoomDatabase {

    private static ContactsDatabase instance;

    public abstract ContactDao getContactDao();

    public static ContactsDatabase getInstance(Context context) {
        if (instance == null)
            return instance = Room.databaseBuilder(context, ContactsDatabase.class, "Contacts.db")
                                    .allowMainThreadQueries()
                                    .addMigrations(MIGRATIONS)
                                    .build();

        return instance;
    }
}
