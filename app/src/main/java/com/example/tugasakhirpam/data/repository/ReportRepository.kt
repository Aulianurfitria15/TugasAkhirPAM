package com.example.tugasakhirpam.data.repository

import com.example.tugasakhirpam.data.dao.ReportDao
import com.example.tugasakhirpam.data.model.GenreCount
import com.example.tugasakhirpam.data.model.Report

import kotlinx.coroutines.flow.Flow

class ReportRepository(
    private val reportDao: ReportDao
) {
    // Laporan total film
    fun getTotalFilm(): Flow<Int> =
        reportDao.getTotalFilm()

    // Laporan genre paling banyak
    fun getMostGenre(): Flow<String?> =
        reportDao.getMostGenre()

    // Laporan rating tertinggi
    fun getHighestRating(): Flow<Double?> =
        reportDao.getHighestRating()

    // Laporan 3 genre teratas
    fun getTop3Genre(): Flow<List<GenreCount>> =
        reportDao.getTop3Genre()
}
