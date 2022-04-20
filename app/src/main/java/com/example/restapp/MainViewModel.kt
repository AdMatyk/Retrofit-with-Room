package com.example.restapp


import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel.*
import com.example.restapp.data.CommentDao
import com.example.restapp.data.PostDao
import com.example.restapp.data.TodosDao
import com.example.restapp.data.UserDao
import com.example.restapp.network.*


class MainViewModel(private val userDao: UserDao, private val postDao: PostDao, private val todosDao: TodosDao, private val commentDao: CommentDao) : ViewModel() {
    var done = false

    fun getData() {
        viewModelScope.launch {
            try {
                    insertTodos()
                    insertPosts()
                    insertUsers()
                    insertComments()

            } catch (e: Exception) {
                Log.i("mes", e.message!!)
            }

        }
        done = true


    }

    private fun insertUsers() {
        viewModelScope.launch {
            for (i in Api.retrofitService.getUsers()) userDao.insert(i)
        }
    }
    private fun insertPosts() {
        viewModelScope.launch {
            for (i in Api.retrofitService.getPosts()) postDao.insert(i)
        }
    }
    private fun insertTodos() {
        viewModelScope.launch {
            for (i in Api.retrofitService.getTodos()) todosDao.insert(i)

        }
    }
    private fun insertComments() {
        viewModelScope.launch {
            for (i in Api.retrofitService.getComments()) commentDao.insert(i)

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

/**
 * Factory class to instantiate the [ViewModel] instance.
 */



