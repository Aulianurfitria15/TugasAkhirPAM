package com.example.tugasakhirpam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tugasakhirpam.data.model.User

@Dao
interface UserDao {

    //Menyimpan user baru (register)
    @Insert
    suspend fun insertUser(user: User)

    //Mengecek login
    //Jika cocok → return User
    //Jika tidak → null
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

    //Mengecek apakah username sudah terdaftar
    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun checkUserExists(username: String): User?
}
