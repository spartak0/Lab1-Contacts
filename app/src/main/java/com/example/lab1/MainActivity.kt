package com.example.lab1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contactsAdapter: ContactsAdapter by lazy { initAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupAdapter()
        setupObserver()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        fbtnAdd.setOnClickListener {
            startActivity(Intent(this.root.context, AddContactActivity::class.java))
        }
    }

    private fun setupObserver() {
        ContactsHolder.contacts.observe(this) { contacts ->
            contactsAdapter.submitList(contacts)
        }
    }

    private fun initAdapter(): ContactsAdapter {
        return ContactsAdapter { contact ->
            ContactsHolder.deleteContact(contact)
        }
    }

    private fun setupAdapter() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = contactsAdapter
    }
}
