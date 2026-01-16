package com.example.tugasakhirpam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhirpam.data.model.User
import com.example.tugasakhirpam.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


//penghubung antara UI (Login/Register Screen) dan data user (Repository/Database).
class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    //Menyimpan hasil login:
    //User → login sukses
    //null → gagal / belum login
    private val _loginResult = MutableStateFlow<User?>(null) //Menyimpan hasil login:

    //Dibaca oleh UI
    //Tidak bisa diubah langsung oleh UI
    val loginResult = _loginResult.asStateFlow()


    //Dipanggil dari RegisterScreen
    //Membuat user baru
    fun register(username: String, password: String, role: String) {
        viewModelScope.launch {
            val user = User( //Membuat objek User
                username = username,
                password = password,
                role = role
            )
            userRepository.insertUser(user)
        }
    }

    //Dipanggil saat user klik tombol Login
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = userRepository.login(username, password)
        }
    }

    //menghapus data login saat logout
    fun logout() {
        _loginResult.value = null
    }

    //meriset login, supaya tidak trigger berulang kali
    fun clearLoginResult() {
        _loginResult.value = null
    }
}
