package com.example.restapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import com.example.restapp.network.User


class MainAdapter(private val context: Activity, val list: List<User> ): ArrayAdapter<User>(context, R.layout.item_user, list){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_user, parent, false)

        val name = rowView.findViewById(R.id.name) as TextView
        val username = rowView.findViewById(R.id.username) as TextView
        val email = rowView.findViewById(R.id.email) as TextView

        name.text = list[position].name
        username.text = list[position].username
        email.text = list[position].email

        return rowView
    }


}



