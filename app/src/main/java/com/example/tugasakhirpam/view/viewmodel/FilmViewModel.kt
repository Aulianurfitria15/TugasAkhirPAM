package com.example.tugasakhirpam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhirpam.data.model.Film
import com.example.tugasakhirpam.data.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn


class FilmViewModel(
    private val repository: FilmRepository
) : ViewModel() {

    val filmList = repository.getAllFilm()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun insertFilm(film: Film) {
        viewModelScope.launch {
            repository.insert(film)
        }
    }

    fun updateFilm(film: Film) {
        viewModelScope.launch {
            repository.update(film)
        }
    }

    fun getFilmById(id: Int): Flow<Film?> {
        return repository.getfilm(id)
    }

    fun deleteFilm(film: Film) {
        viewModelScope.launch {
            repository.delete(film)
        }
    }

    fun searchFilm(keyword: String): Flow<List<Film>> =
        repository.searchFilm(keyword)
}
