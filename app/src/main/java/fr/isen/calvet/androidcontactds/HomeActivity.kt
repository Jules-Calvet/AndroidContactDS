package fr.isen.calvet.androidcontactds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.calvet.androidcontactds.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactList = arrayListOf(
            Contact("Jules", "Calvet", "ISEN Yncrea Méditérranée", "jules.calvet@isen.yncrea.fr"),
            Contact("Marc", "Mollinari", "559", "marc.mollinari@yncrea.fr")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ContactAdapter(contactList)


    }

    data class Contact(val firstname : String, val lastname : String, val address : String, val email : String)
}