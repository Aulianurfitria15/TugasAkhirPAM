package com.example.tugasakhirpam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhirpam.data.model.Film
import com.example.tugasakhirpam.data.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

//penghubung antara UI (screen) dan data Film (Repository/Database).
class FilmViewModel( //menyimpan logika film
    private val repository: FilmRepository
) : ViewModel() {

    //untuk mengambil daftar film dari database dan menyimpannya sebagai
    // StateFlow agar UI bisa menampilkan dan otomatis update ketika data berubah.
        val filmList = repository.getAllFilm()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )

    //untuk menambahkan data film baru ke database secara aman
    // menggunakan coroutine, lewat ViewModel.
    fun insertFilm(film: Film) {
        viewModelScope.launch {
            repository.insert(film)
        }
    }

    //untuk mengubah data film baru ke database secara aman
    // menggunakan coroutine, lewat ViewModel.
    fun updateFilm(film: Film) {
        viewModelScope.launch {
            repository.update(film)
        }
    }

    //untuk mengambil 1 data film berdasarkan ID dari database,
    //dan mengirimkannya ke UI dalam bentuk Flow agar datanya bisa update otomatis.
    fun getFilmById(id: Int): Flow<Film?> {
        return repository.getfilm(id)
    }

    //uuntuk menghapus data film dari database secara aman (pakai coroutine) melalui Repository.
    fun deleteFilm(film: Film) {
        viewModelScope.launch {
            repository.delete(film)
        }
    }

    //untuk mencari film berdasarkan kata kunci (judul), lalu mengirim hasilnya
    // ke UI secara real-time menggunakan Flow.
    fun searchFilm(keyword: String): Flow<List<Film>> =
        repository.searchFilm(keyword)
}
