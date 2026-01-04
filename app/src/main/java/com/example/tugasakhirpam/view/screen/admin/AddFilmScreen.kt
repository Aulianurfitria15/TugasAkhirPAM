package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugasakhirpam.data.model.Film
import com.example.tugasakhirpam.view.component.AppTopBar
import com.example.tugasakhirpam.viewmodel.FilmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFilmScreen(
    viewModel: FilmViewModel,
    filmId: Int? = null,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Film") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(title, { title = it }, label = { Text("Judul") })
            OutlinedTextField(genre, { genre = it }, label = { Text("Genre") })
            OutlinedTextField(year, { year = it }, label = { Text("Tahun") })
            OutlinedTextField(rating, { rating = it }, label = { Text("Rating") })
            OutlinedTextField(description, { description = it }, label = { Text("Deskripsi") })

            Button(onClick = {
                val posterUrl =
                    "https://picsum.photos/seed/${title.hashCode()}/300/450"

                viewModel.insertFilm(
                    Film(
                        title = title,
                        genre = genre,
                        year = year.toInt(),
                        rating = rating.toDouble(),
                        poster = posterUrl,
                        description = description
                    )
                )
                onBack()
            }) {
                Text("Simpan")
            }
        }
    }
}
