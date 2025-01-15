package com.example.lab1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1.databinding.ActivityAddContactBinding

class AddContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnSave.setOnClickListener {
            val contact = Contact(
                etFullName.text.toString(),
                etPhone.text.toString(),
                etEmail.text.toString()
            )

            if (validate(contact)) {
                ContactsHolder.addContact(contact)
                finish()
            }
        }
    }

    private fun validate(contact: Contact): Boolean = with(contact) {
        return fullName.isNotEmpty() && validatePhone(phoneNumber) && email.isNotEmpty()
    }

    private fun validatePhone(phone: String): Boolean {
        return phone.isNotEmpty() && phone.startsWith("+7") && phone.length == 12
    }
}
