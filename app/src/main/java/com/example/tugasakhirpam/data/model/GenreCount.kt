package com.example.tugasakhirpam.data.model

data class GenreCount(
    val genre: String,
    val total: Int
)

//dipake untuk menampung data genre dan jumlah film per genre
//room akan mencocokkan nama kolom SQL (genre, total)

//kenapa ga pake entity?
//karena
//Tidak disimpan sebagai tabel
//Hanya hasil perhitungan sementara
//Tidak perlu primary key