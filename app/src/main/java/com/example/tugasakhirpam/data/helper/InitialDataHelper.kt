package com.example.tugasakhirpam.data.helper

import com.example.tugasakhirpam.data.model.User
import com.example.tugasakhirpam.data.repository.UserRepository

object InitialDataHelper {
    suspend fun initializeAdminUser(userRepository: UserRepository) {
        val existingAdmin = userRepository.checkUserExists("admin")
        if (existingAdmin == null) {
            val adminUser = User(
                username = "admin",
                password = "123",
                role = "admin"
            )
            userRepository.insertUser(adminUser)
        }
    }
}

