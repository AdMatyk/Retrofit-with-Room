package com.example.restapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.restapp.data.UserDatabase
import com.example.restapp.databinding.ActivityUserBinding
import com.example.restapp.network.User
import kotlinx.coroutines.flow.toList

class UserActivity: AppCompatActivity() {
    var userList = listOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = UserDatabase.getDatabase(this)
        val binding = ActivityUserBinding.inflate(layoutInflater)
        val thread = Thread(){
            run{
                userList = db.userDao().getItems()
            }
            runOnUiThread {
                val adapter = MainAdapter(this,userList)
                binding.lifecycleOwner = this
                binding.listView.adapter = adapter
                binding.listView.setOnItemClickListener{ _, _, position, _ ->
                    val intent = Intent(this, PostTodosActivity::class.java)
                    intent.putExtra("id", position)
                    startActivity(intent)
                }
            }

        }
        thread.start()
        setContentView(binding.root)

    }
}