package com.example.tugasakhirpam.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val role: String
)

//tabel user
//untuk menyimpan data user (register dan login)
//dan berisi kolom id, username, password, role
