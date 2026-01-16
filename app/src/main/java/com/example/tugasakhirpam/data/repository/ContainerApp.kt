package com.example.tugasakhirpam.data.repository

import android.content.Context
import com.example.tugasakhirpam.data.database.MovieDatabase

//Menyediakan semua Repository dalam satu tempat
class ContainerApp(context: Context) {

    private val database = MovieDatabase.getDatabase(context)


    //Database punya DAO
    //DAO dimasukkan ke Repository
    //Repository siap dipakai
    val userRepository = UserRepository(database.userDao())
    val filmRepository = FilmRepository(database.filmDao())
    val reportRepository = ReportRepository(database.reportDao())
}
