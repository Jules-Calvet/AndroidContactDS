package fr.isen.calvet.androidcontactds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ContactAdapter(var list: ArrayList<HomeActivity.Contact>, val onItemClickListener: (HomeActivity.Contact) -> Unit) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactFirstName : TextView = view.findViewById(R.id.firstname)
        val contactLastName : TextView = view.findViewById(R.id.lastname)
        val contactAddress : TextView = view.findViewById(R.id.address)
        val contactAddressGlobal : TextView = view.findViewById(R.id.city)
        val contactEmail : TextView = view.findViewById(R.id.email)
        val image : ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_data, parent, false)

        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = list[position]
        holder.contactFirstName.text = contact.name.first
        holder.contactLastName.text = contact.name.last.uppercase()
        holder.contactAddress.text = "${contact.location.street.number} ${contact.location.street.name}"
        holder.contactAddressGlobal.text = "${contact.location.city} ${contact.location.state}"
        holder.contactEmail.text = contact.email
        Picasso.get().load(contact.picture.thumbnail).into(holder.image)

        holder.itemView.setOnClickListener {
            onItemClickListener(contact)
        }
    }

    override fun getItemCount(): Int = list.size
}
