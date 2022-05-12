package br.com.contacts.db.migration;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public interface Migrations {

    Migration[] MIGRATIONS = {
            new Migration(1, 2) {
                @Override
                public void migrate(@NonNull SupportSQLiteDatabase database) {
                    database.execSQL("ALTER TABLE contact ADD COLUMN middleName TEXT");
                }
            },
            new Migration(2, 3) {
                @Override
                public void migrate(@NonNull SupportSQLiteDatabase database) {
                    database.execSQL("CREATE TABLE IF NOT EXISTS `Contact_New` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `phone` TEXT, `email` TEXT)");
                    database.execSQL("INSERT INTO `Contact_New` (`id`, `name`, `phone`, `email`) SELECT `id`, `name`, `phone`, `email` FROM `Contact`");
                    database.execSQL("DROP TABLE `Contact`");
                    database.execSQL("ALTER TABLE `Contact_New` RENAME TO `Contact`");
                }
            },
            new Migration(3, 4) {
                @Override
                public void migrate(@NonNull SupportSQLiteDatabase database) {
                    database.execSQL("ALTER TABLE `Contact` ADD COLUMN `createAt` INTEGER");
                }
            }
    };
}
