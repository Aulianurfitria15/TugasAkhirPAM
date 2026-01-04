package com.example.tugasakhirpam.data.repository

import com.example.tugasakhirpam.data.dao.FilmDao
import com.example.tugasakhirpam.data.model.Film
import kotlinx.coroutines.flow.Flow

class FilmRepository(private val dao: FilmDao) {

    fun getAllFilm(): Flow<List<Film>> = dao.getAllFilm()

    fun searchFilm(keyword: String): Flow<List<Film>> =
        dao.searchFilm(keyword)

    suspend fun insert(film: Film) = dao.insert(film)

    fun getfilm(id: Int): Flow<Film?> = dao.getFilmById(id)

    suspend fun update(film: Film) = dao.update(film)

    suspend fun delete(film: Film) = dao.delete(film)
}
