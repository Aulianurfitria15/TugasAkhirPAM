package com.example.tugasakhirpam.data.repository

import com.example.tugasakhirpam.data.dao.FilmDao
import com.example.tugasakhirpam.data.model.Film
import kotlinx.coroutines.flow.Flow


//mengatur semua urusan data FILM
class FilmRepository(private val dao: FilmDao) {

    //Ambil semua film
    fun getAllFilm(): Flow<List<Film>> = dao.getAllFilm()

    //Mencari film berdasarkan keyword
    fun searchFilm(keyword: String): Flow<List<Film>> =
        dao.searchFilm(keyword)

    //Menambahkan film baru
    suspend fun insert(film: Film) = dao.insert(film)

    //Mengambil 1 film berdasarkan ID
    fun getfilm(id: Int): Flow<Film?> = dao.getFilmById(id)

    //Mengubah data film
    suspend fun update(film: Film) = dao.update(film)

    //Menghapus film
    suspend fun delete(film: Film) = dao.delete(film)
}
