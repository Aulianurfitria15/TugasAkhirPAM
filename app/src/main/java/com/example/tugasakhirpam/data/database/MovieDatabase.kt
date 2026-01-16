package com.example.tugasakhirpam.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tugasakhirpam.data.dao.FilmDao
import com.example.tugasakhirpam.data.dao.ReportDao
import com.example.tugasakhirpam.data.dao.UserDao
import com.example.tugasakhirpam.data.model.Film
import com.example.tugasakhirpam.data.model.User

//DATABASE PUNYA 2 TABEL: FILM & USER
//ReportDao tidak punya entity karena dia hanya membaca data dari tabel film
@Database(
    entities = [
        Film::class,
        User::class
    ],
    version = 1,

    //Room tidak menyimpan riwayat perubahan schema
    exportSchema = false
)

//MENGEMBALIKAN Akses ke tabel film, Akses ke tabel user, Akses ke laporan
abstract class MovieDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
    abstract fun userDao(): UserDao
    abstract fun reportDao(): ReportDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null


        //Digunakan untuk mengambil instance database
        //Jika belum ada → dibuat
        //Jika sudah ada → dipakai ulang
        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
