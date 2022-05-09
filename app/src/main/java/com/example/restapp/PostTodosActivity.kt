package com.example.restapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Visibility
import com.example.restapp.data.PostDatabase
import com.example.restapp.data.TodosDatabase
import com.example.restapp.data.UserDatabase
import com.example.restapp.databinding.ActivityPostTodosBinding
import com.example.restapp.databinding.ActivityUserBinding
import com.example.restapp.network.Post
import com.example.restapp.network.Todos
import com.example.restapp.network.User

class PostTodosActivity: AppCompatActivity()  {


        var postList = listOf<Post>()
        var todosList = listOf<Todos>()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val id = intent.getIntExtra("id", 0)
            val postDb = PostDatabase.getDatabase(this)
            val todosDb = TodosDatabase.getDatabase(this)
            val binding = ActivityPostTodosBinding.inflate(layoutInflater)
            val thread = Thread(){

                run{
                    postList = postDb.postDao().getItems(id + 1)
                    todosList = todosDb.todosDao().getItems(id + 1)
                }
                runOnUiThread {
                    val postAdapter = PostAdapter(this,postList)
                    val todosAdapter = TodosAdapter(this, todosList)
                    binding.apply {
                        postsButton.isChecked = true
                        todosListView.visibility = View.GONE
                        todosListView.isClickable = false
                        postsButton.setOnClickListener {
                            postListView.visibility = View.VISIBLE
                            todosListView.visibility = View.GONE
                        }
                        toDosButton.setOnClickListener {
                            postListView.visibility = View.GONE
                            todosListView.visibility = View.VISIBLE
                        }
                        postListView.adapter = postAdapter
                        todosListView.adapter = todosAdapter
                        postListView.setOnItemClickListener{parent, view, position, id ->
                                    val intent = Intent(this@PostTodosActivity, CommentActivity::class.java)
                            intent.putExtra("postId",postList[position].id)
                            startActivity(intent)
                        }
                    }
                    binding.lifecycleOwner = this


                }

            }
            thread.start()
            setContentView(binding.root)

        }

}