package com.example.lab1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object ContactsHolder {

    private val _contacts = MutableLiveData<List<Contact>>(emptyList())
    val contacts: LiveData<List<Contact>> get() = _contacts

    fun addContact(contact: Contact) {
        _contacts.value = _contacts.value.orEmpty() + contact
    }

    fun deleteContact(contact: Contact) {
        _contacts.value = _contacts.value.orEmpty().toMutableList()
            .apply { remove(contact) }
    }
}