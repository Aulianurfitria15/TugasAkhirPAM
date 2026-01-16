package com.example.tugasakhirpam.data.repository

import com.example.tugasakhirpam.data.dao.UserDao
import com.example.tugasakhirpam.data.model.User

class UserRepository( //Mengatur login, register, dan user data
    private val userDao: UserDao
) {

    //Menyimpan user baru (register)
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    //Mengecek login
    //Jika cocok → return User
    //Jika tidak → null
    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }

    //Mengecek apakah username sudah terdaftar
    suspend fun checkUserExists(username: String): User? {
        return userDao.checkUserExists(username)
    }
}
