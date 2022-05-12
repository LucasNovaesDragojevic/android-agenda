package br.com.contacts.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.contacts.model.Contact;

@Dao
public interface ContactDao {

    @Insert
    void create(Contact contact);

    @Query("SELECT * FROM contact")
    List<Contact> readAll();

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
