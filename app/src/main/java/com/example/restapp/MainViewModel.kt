package com.example.restapp


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.restapp.data.CommentDao
import com.example.restapp.data.PostDao
import com.example.restapp.data.TodosDao
import com.example.restapp.data.UserDao
import com.example.restapp.network.*
import kotlinx.coroutines.launch


class MainViewModel(private val userDao: UserDao, private val postDao: PostDao, private val todosDao: TodosDao, private val commentDao: CommentDao) : ViewModel() {
    var done = false
    private val users = mutableListOf<User>()
    private val comments = mutableListOf<Comment>()
    private val posts = mutableListOf<Post>()
    val todos = mutableListOf<Todos>()

    suspend fun getData() {
            try {
                viewModelScope.launch {
                    users.addAll(Api.retrofitService.getUsers())

                    posts.addAll(Api.retrofitService.getPosts())

                    todos.addAll(Api.retrofitService.getTodos())

                    comments.addAll(Api.retrofitService.getComments())
                }

            }
                catch(e: Exception) {
                Log.i("mes", e.message!!)
            }
    }
    suspend fun insertData() {
        viewModelScope.launch {
            userDao.deleteItems()
            for (i in users) userDao.insert(i)
            postDao.deleteItems()
            for (i in posts) postDao.insert(i)
            todosDao.deleteItems()
            for (i in todos) todosDao.insert(i)
            commentDao.deleteItems()
            for (i in comments) commentDao.insert(i)
        }

    }



    class Factory(private val userDao: UserDao, private val postDao: PostDao, private val todosDao: TodosDao, private val commentDao: CommentDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(userDao, postDao, todosDao, commentDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}



