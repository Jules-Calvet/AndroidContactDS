package fr.isen.calvet.androidcontactds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.calvet.androidcontactds.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("Name")
        binding.name.text = name

        val image = intent.getStringExtra("Image")
        Picasso.get().load(image).into(binding.image)

        val address = intent.getStringExtra("Address")
        binding.textView3.text = address

        val email = intent.getStringExtra("Email")
        binding.textView4.text = email

        val cell = intent.getStringExtra("Mobile")
        binding.textView5.text = cell
    }
}