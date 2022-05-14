package br.com.contacts.db.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.contacts.model.Phone;

@Dao
public interface PhoneDao {

    @Insert(onConflict = REPLACE)
    void save(Phone homePhone, Phone mobilePhone);

    @Query("SELECT p.* FROM Phone p WHERE p.contactId = :contactId LIMIT 1")
    Phone getFirstContactPhone(Long contactId);

    @Query("SELECT * FROM Phone p WHERE p.contactId = :contactId")
    List<Phone> getPhonesFromContact(Long contactId);
}
