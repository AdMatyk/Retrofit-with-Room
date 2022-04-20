package com.example.restapp.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todos(
    @ColumnInfo(name = "userId")
    val userId: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "completed")
    val completed: Boolean)