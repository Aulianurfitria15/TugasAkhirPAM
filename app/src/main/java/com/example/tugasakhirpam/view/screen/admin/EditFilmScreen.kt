package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugasakhirpam.data.model.Film
import com.example.tugasakhirpam.view.component.AppTopBar

@Composable
fun EditFilmScreen(
    film: Film?,
    onSave: (Film) -> Unit,
    onBack: () -> Unit
) {
    var title by remember(film) { mutableStateOf(film?.title ?: "") }
    var genre by remember(film) { mutableStateOf(film?.genre ?: "") }
    var year by remember(film) { mutableStateOf(film?.year?.toString() ?: "") }
    var rating by remember(film) { mutableStateOf(film?.rating?.toString() ?: "") }
    var description by remember(film) { mutableStateOf(film?.description ?: "") }
    var poster by remember(film) { mutableStateOf(film?.poster ?: "") }

    Scaffold(
        topBar = { AppTopBar("Edit Film") }
    ) { padding ->
        film?.let { existingFilm ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Judul") })
                OutlinedTextField(
                    value = genre,
                    onValueChange = { genre = it },
                    label = { Text("Genre") })
                OutlinedTextField(
                    value = year,
                    onValueChange = { year = it },
                    label = { Text("Tahun") })
                OutlinedTextField(
                    value = rating,
                    onValueChange = { rating = it },
                    label = { Text("Rating") })
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Deskripsi") })
                OutlinedTextField(
                    value = poster,
                    onValueChange = { poster = it },
                    label = { Text("Poster URL") })

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    onSave(
                        Film(
                            id = existingFilm.id, // 🔴 INI YANG PALING PENTING
                            title = title,
                            genre = genre,
                            year = year.toInt(),
                            rating = rating.toDouble(),
                            description = description,
                            poster = poster
                        )
                    )
                }) {
                    Text("Update")
                }
            }
        }
    }
}