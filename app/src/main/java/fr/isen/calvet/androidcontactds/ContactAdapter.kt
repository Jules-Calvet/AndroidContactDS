package fr.isen.calvet.androidcontactds

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(var list: ArrayList<HomeActivity.Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactFirstName : TextView = view.findViewById(R.id.firstname)
        val contactLastName : TextView = view.findViewById(R.id.lastname)
        val contactAddress : TextView = view.findViewById(R.id.address)
        val contactAddressGlobal : TextView = view.findViewById(R.id.city)
        val contactEmail : TextView = view.findViewById(R.id.email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_data, parent, false)

        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = list[position]
        holder.contactFirstName.text = contact.name.first
        holder.contactLastName.text = contact.name.last
        holder.contactAddress.text = "${contact.location.street.number} ${contact.location.street.name}"
        holder.contactAddressGlobal.text = "${contact.location.city} ${contact.location.state} ${contact.location.country}"
        holder.contactEmail.text = contact.email
    }

    override fun getItemCount(): Int = list.size
}
