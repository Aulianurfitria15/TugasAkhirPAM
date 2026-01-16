package com.example.tugasakhirpam.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//table film untuk menyimpan data film
//beserta isi column nya
@Entity(tableName = "film")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val genre: String,
    val rating: Double,
    val description: String,
    val year: Int,
    val poster: String
)
