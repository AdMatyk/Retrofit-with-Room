package com.example.restapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.restapp.data.CommentDao
import kotlinx.coroutines.coroutineScope


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val userDb = (this.application as RESTApplication).userDatabase.userDao()
        val postDb = (this.application as RESTApplication).postDatabase.postDao()
        val todosDb = (this.application as RESTApplication).todosDatabase.todosDao()
        val commentsDb = (this.application as RESTApplication).commentDatabase.commentDao()
        val factory = MainViewModel.Factory(userDb, postDb, todosDb, commentsDb) // Factory
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        val thread = Thread(){
            run{
                viewModel.getData()
            }
            runOnUiThread(){

                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
                while (!viewModel.done) Thread.sleep(1000)
                finish()
            }
        }
        thread.start()

    }




}