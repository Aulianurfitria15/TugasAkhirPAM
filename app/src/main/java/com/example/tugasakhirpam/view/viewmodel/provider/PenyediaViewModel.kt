package com.example.tugasakhirpam.viewmodel.provider

import android.content.Context
import com.example.tugasakhirpam.data.database.MovieDatabase
import com.example.tugasakhirpam.data.repository.FilmRepository
import com.example.tugasakhirpam.data.repository.ReportRepository
import com.example.tugasakhirpam.data.repository.UserRepository
import com.example.tugasakhirpam.view.viewmodel.ReportViewModel
import com.example.tugasakhirpam.viewmodel.AuthViewModel
import com.example.tugasakhirpam.viewmodel.FilmViewModel

object PenyediaViewModel {

    fun provideAuthViewModel(context: Context): AuthViewModel {
        val db = MovieDatabase.getDatabase(context)
        return AuthViewModel(UserRepository(db.userDao()))
    }

    fun provideFilmViewModel(context: Context): FilmViewModel {
        val db = MovieDatabase.getDatabase(context)
        return FilmViewModel(FilmRepository(db.filmDao()))
    }

    fun provideReportViewModel(context: Context): ReportViewModel {
        val db = MovieDatabase.getDatabase(context)
        return ReportViewModel(ReportRepository(db.reportDao()))
    }

    fun provideFilmRepository(context: Context): FilmRepository {
        val db = MovieDatabase.getDatabase(context)
        return FilmRepository(db.filmDao())
    }

    fun provideUserRepository(context: Context): UserRepository {
        val db = MovieDatabase.getDatabase(context)
        return UserRepository(db.userDao())
    }
}
