package br.com.contacts.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(entity = Contact.class, parentColumns = "id", childColumns = "contactId", onUpdate = CASCADE, onDelete = CASCADE),
        indices = { @Index(value = "id", unique = true), @Index("contactId") })
public class Phone {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String number;
    private PhoneType type;
    private Long contactId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
