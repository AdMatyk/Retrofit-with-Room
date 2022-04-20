package com.example.restapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.restapp.data.CommentDatabase
import com.example.restapp.data.UserDatabase
import com.example.restapp.databinding.ActivityCommentBinding
import com.example.restapp.databinding.ActivityUserBinding
import com.example.restapp.network.Comment
import com.example.restapp.network.User

class CommentActivity: AppCompatActivity() {
    var commentList = listOf<Comment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("postId", 0)
        val db = CommentDatabase.getDatabase(this)
        val binding = ActivityCommentBinding.inflate(layoutInflater)
        val thread = Thread(){
            run{
                commentList = db.commentDao().getItems(id)
            }
            runOnUiThread {
                val adapter = CommentAdapter(this, commentList)
                binding.lifecycleOwner = this
                binding.listView.adapter = adapter
            }

        }
        thread.start()
        setContentView(binding.root)

    }
}