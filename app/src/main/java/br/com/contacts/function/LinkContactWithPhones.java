package br.com.contacts.function;

import br.com.contacts.model.Phone;

public interface LinkContactWithPhones {

    static void execute(Long contactId, Phone... phones) {
        for (Phone phone : phones)
            phone.setContactId(contactId);
    }
}
