package com.example.restapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.restapp.network.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private val users = mutableListOf<User>()
    private val comments = mutableListOf<Comment>()
    private val posts = mutableListOf<Post>()
    private val todos = mutableListOf<Todos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val userDb = (this.application as RESTApplication).userDatabase.userDao()
        val postDb = (this.application as RESTApplication).postDatabase.postDao()
        val todosDb = (this.application as RESTApplication).todosDatabase.todosDao()
        val commentsDb = (this.application as RESTApplication).commentDatabase.commentDao()


        val factory = MainViewModel.Factory(userDb, postDb, todosDb, commentsDb) // Factory
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        runBlocking {
            try {
                users.addAll(Api.retrofitService.getUsers())
                posts.addAll(Api.retrofitService.getPosts())
                todos.addAll(Api.retrofitService.getTodos())
                comments.addAll(Api.retrofitService.getComments())
                for (i in users) userDb.insert(i)
                for (i in posts) postDb.insert(i)
                for (i in todos) todosDb.insert(i)
                for (i in comments) commentsDb.insert(i)
            } catch (e:Exception) {
                Log.i("mes", e.message!!)
            }

        }
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
        finish()

    }


}
