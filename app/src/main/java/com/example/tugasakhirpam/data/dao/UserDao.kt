package com.example.tugasakhirpam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tugasakhirpam.data.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun checkUserExists(username: String): User?
}
