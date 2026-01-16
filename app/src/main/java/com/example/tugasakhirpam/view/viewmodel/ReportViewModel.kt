package com.example.tugasakhirpam.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhirpam.data.repository.ReportRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


//khusus untuk mengelola data laporan (report) film
class ReportViewModel(
    private val repository: ReportRepository
) : ViewModel() {


    //Mengambil jumlah total film yang ada di database
    val totalFilm = repository.getTotalFilm()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0
        )


    //Menampilkan genre yang paling sering muncul
    val mostGenre = repository.getMostGenre()
        .map { it ?: "-" } // 🔥 WAJIB
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            "-"
        )

    //Menampilkan rating tertinggi dari semua film
    val highestRating = repository.getHighestRating()
        .map { it ?: 0.0 } // 🔥 WAJIB
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0.0
        )

    //Mengambil 3 genre teratas beserta jumlahnya
    val top3Genre = repository.getTop3Genre()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )
}
