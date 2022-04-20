package com.example.restapp

import android.app.Application
import com.example.restapp.data.CommentDatabase
import com.example.restapp.data.PostDatabase
import com.example.restapp.data.TodosDatabase
import com.example.restapp.data.UserDatabase

class RESTApplication: Application() {
    val userDatabase: UserDatabase by lazy { UserDatabase.getDatabase(this) }
    val postDatabase: PostDatabase by lazy { PostDatabase.getDatabase(this) }
    val todosDatabase: TodosDatabase by lazy { TodosDatabase.getDatabase(this) }
    val commentDatabase: CommentDatabase by lazy { CommentDatabase.getDatabase(this) }
}