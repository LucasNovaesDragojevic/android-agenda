package br.com.agenda.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.agenda.dao.ContactDao;
import br.com.agenda.model.Contact;

@Database(entities = {Contact.class},
          version = 3,
          exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    private static ContactsDatabase instance;

    public abstract ContactDao getContactDao();

    public static ContactsDatabase getInstance(Context context) {
        if (instance == null)
            return instance = Room.databaseBuilder(context, ContactsDatabase.class, "Contacts.db")
                                    .allowMainThreadQueries()
                                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                                    .build();

        return instance;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE contact ADD COLUMN middleName TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Contact_New` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `phone` TEXT, `email` TEXT)");
            database.execSQL("INSERT INTO `Contact_New` (`id`, `name`, `phone`, `email`) SELECT `id`, `name`, `phone`, `email` FROM `Contact`");
            database.execSQL("DROP TABLE `Contact`");
            database.execSQL("ALTER TABLE `Contact_New` RENAME TO `Contact`");
        }
    };
}
