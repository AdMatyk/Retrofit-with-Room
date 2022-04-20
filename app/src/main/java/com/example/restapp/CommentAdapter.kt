package com.example.restapp

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.restapp.network.Comment
import com.example.restapp.network.Todos

class CommentAdapter (private val context: Activity, val list: List<Comment> ): ArrayAdapter<Comment>(context, R.layout.item_comment, list) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_comment, parent, false)

        val email = rowView.findViewById(R.id.commentEmail) as TextView
        val title = rowView.findViewById(R.id.commentName) as TextView
        val body = rowView.findViewById(R.id.commentBody) as TextView
        email.text = list[position].email
        title.text = list[position].name
        body.text = list[position].body

        return rowView
    }
}