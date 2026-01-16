package com.example.tugasakhirpam.viewmodel.provider

import android.content.Context
import com.example.tugasakhirpam.data.database.MovieDatabase
import com.example.tugasakhirpam.data.repository.FilmRepository
import com.example.tugasakhirpam.data.repository.ReportRepository
import com.example.tugasakhirpam.data.repository.UserRepository
import com.example.tugasakhirpam.view.viewmodel.ReportViewModel
import com.example.tugasakhirpam.viewmodel.AuthViewModel
import com.example.tugasakhirpam.viewmodel.FilmViewModel

//untuk MENYIAPKAN dan MEMBERIKAN ViewModel (dan Repository)
// ke UI tanpa UI harus tahu cara bikin database dan DAO.
object PenyediaViewModel {

    //Menyediakan AuthViewModel lengkap dengan UserRepository dan UserDao
    fun provideAuthViewModel(context: Context): AuthViewModel {
        val db = MovieDatabase.getDatabase(context)
        return AuthViewModel(UserRepository(db.userDao()))
    }


    //Menyediakan FilmViewModel lengkap dengan FilmRepository
    fun provideFilmViewModel(context: Context): FilmViewModel {
        val db = MovieDatabase.getDatabase(context)
        return FilmViewModel(FilmRepository(db.filmDao()))
    }


    //Menyediakan ReportViewModel lengkap dengan ReportRepository
    fun provideReportViewModel(context: Context): ReportViewModel {
        val db = MovieDatabase.getDatabase(context)
        return ReportViewModel(ReportRepository(db.reportDao()))
    }

    //Menyediakan FilmRepository
    fun provideFilmRepository(context: Context): FilmRepository {
        val db = MovieDatabase.getDatabase(context)
        return FilmRepository(db.filmDao())
    }


    //Menyediakan UserRepository
    fun provideUserRepository(context: Context): UserRepository {
        val db = MovieDatabase.getDatabase(context)
        return UserRepository(db.userDao())
    }
}
