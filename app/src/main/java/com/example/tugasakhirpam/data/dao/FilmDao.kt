package com.example.tugasakhirpam.data.dao

import androidx.room.*
import com.example.tugasakhirpam.data.model.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    //Menyimpan 1 data film ke database
    @Insert
    suspend fun insert(film: Film)

    //Mengubah data film yang sudah ada
    @Update
    suspend fun update(film: Film)

    //Menghapus 1 data film dari database
    @Delete
    suspend fun delete(film: Film)

    //Mengambil 1 film berdasarkan ID
    //pake flow agar data otomatis update
    @Query("SELECT * FROM film WHERE id = :id")
    fun getFilmById(id: Int): Flow<Film?>

    //Mengambil semua film
    @Query("SELECT * FROM film ORDER BY title ASC")
    fun getAllFilm(): Flow<List<Film>>

    //Mencari film berdasarkan judul
    @Query("SELECT * FROM film WHERE title LIKE '%' || :query || '%'")
    fun searchFilm(query: String): Flow<List<Film>>
}
