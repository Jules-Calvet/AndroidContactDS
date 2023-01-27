package fr.isen.calvet.androidcontactds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.calvet.androidcontactds.databinding.ActivityHomeBinding
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadContactsFromAPI()

        //Question 5 avec une liste créée dans le code

        /*
        val contactList = arrayListOf(
            Contact("Jules", "Calvet", "ISEN Yncrea Méditérranée", "jules.calvet@isen.yncrea.fr"),
            Contact("Marc", "Mollinari", "559", "marc.mollinari@yncrea.fr")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ContactAdapter(contactList)
        */


    }

    private fun loadContactsFromAPI() {
        val url = "https://randomuser.me/api/?results=10&nat=fr"
        val jsonObject = JSONObject()
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, jsonObject, {
                val gson = GsonBuilder().create()
                val results = gson.fromJson(it.toString(), Results::class.java)
                //Log.w("Results", "resp : ${results.results}")
                val contactList = results.results
                for(contacts in results.results) {
                    binding.recyclerView.layoutManager = LinearLayoutManager(this)
                    binding.recyclerView.adapter = ContactAdapter(contactList) {
                        val intent = Intent(this, ContactDetailActivity::class.java)
                        intent.putExtra("Image", it.picture.medium)
                        intent.putExtra("Name", it.name.first + " " + it.name.last.uppercase())
                        intent.putExtra("Address", "${it.location.street.number}  ${it.location.street.name}")
                        intent.putExtra("Email", it.email)
                        intent.putExtra("Mobile", it.cell)
                        startActivity(intent)
                    }
                }
            }, {
                Log.w("HomeActivity", "erreur : $it")
            }
        )
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    // 1ere version de la data class Contact pour le test manuel de l'adapter
    // data class Contact(val firstname : String, val lastname : String, val address : String, val email : String)

    data class Results(
        var results: ArrayList<Contact>
    )

    data class Contact(
        var name : ContactName,
        var location : Location,
        var email : String,
        var cell : String,
        var picture : Picture
    )

    data class ContactName(
        var first : String,
        var last : String
    )

    data class Location(
        var street : StreetNumber,
        var city : String,
        var state : String
    )

    data class StreetNumber(
        var number : Int,
        var name : String
    )

    data class Picture(
        var medium : String,
        var thumbnail : String
    )
}