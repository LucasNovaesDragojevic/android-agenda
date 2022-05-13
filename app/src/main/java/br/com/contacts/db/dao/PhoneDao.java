package br.com.contacts.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import br.com.contacts.model.Phone;

@Dao
public interface PhoneDao {

    @Query("SELECT p.* FROM Phone p WHERE p.contactId = :contactId LIMIT 1")
    Phone getFirstContactPhone(Long contactId);
}
