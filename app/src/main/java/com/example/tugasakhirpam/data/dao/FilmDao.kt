package com.example.tugasakhirpam.data.dao

import androidx.room.*
import com.example.tugasakhirpam.data.model.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Insert
    suspend fun insert(film: Film)



    @Update
    suspend fun update(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Query("SELECT * FROM film WHERE id = :id")
    fun getFilmById(id: Int): Flow<Film?>

    @Query("SELECT * FROM film ORDER BY title ASC")
    fun getAllFilm(): Flow<List<Film>>

    @Query("SELECT * FROM film WHERE title LIKE '%' || :query || '%'")
    fun searchFilm(query: String): Flow<List<Film>>

    //@Query("SELECT * FROM film WHERE id = :id")
    //suspend fun getFilmById(id: Int): Film
}
