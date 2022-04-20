package com.example.restapp

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.restapp.network.Todos

class TodosAdapter (private val context: Activity, val list: List<Todos> ): ArrayAdapter<Todos>(context, R.layout.item_post, list) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_post, parent, false)

        val title = rowView.findViewById(R.id.title) as TextView
        val completed = rowView.findViewById(R.id.body) as TextView

        title.text = list[position].title
        completed.text = list[position].completed.toString()

        return rowView
    }
}