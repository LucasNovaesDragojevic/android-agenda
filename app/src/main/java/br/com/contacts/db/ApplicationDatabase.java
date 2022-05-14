package br.com.contacts.db;

import static br.com.contacts.db.migration.Migrations.MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.contacts.db.dao.ContactDao;
import br.com.contacts.db.converter.CalendarConverter;
import br.com.contacts.db.dao.PhoneDao;
import br.com.contacts.model.Contact;
import br.com.contacts.model.Phone;

@Database(entities = {Contact.class, Phone.class},
          version = 5,
          exportSchema = false)
@TypeConverters(CalendarConverter.class)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static ApplicationDatabase instance;

    public abstract ContactDao getContactDao();

    public abstract PhoneDao getPhoneDao();

    public static ApplicationDatabase getInstance(Context context) {
        if (instance == null)
            return instance = Room.databaseBuilder(context, ApplicationDatabase.class, "Contacts.db")
                                    .addMigrations(MIGRATIONS)
                                    .build();

        return instance;
    }
}
