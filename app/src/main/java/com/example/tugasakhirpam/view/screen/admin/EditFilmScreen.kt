package com.example.tugasakhirpam.view.screen.admin

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tugasakhirpam.data.model.Film
import com.example.tugasakhirpam.view.component.AppTopBar
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
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
    var posterUri by remember { mutableStateOf<Uri?>(null) }
    var posterPath by remember(film) { mutableStateOf(film?.poster ?: "") }

    val context = LocalContext.current

    // Launcher untuk membuka file picker
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            posterUri = it
            // Simpan foto ke internal storage
            posterPath = savePosterImage(context, it)
        }
    }

    Scaffold(
        containerColor = Color(0xFF4F5F59), // 🌿 background hijau
        topBar = {
            AppTopBar(
                title = "Edit Film",
                onBack = onBack
            )
        }
    ) { padding ->
        film?.let { existingFilm ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // 🎬 POSTER - Upload Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .clickable { launcher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (posterUri != null) {

                        // Tampilkan preview gambar baru
                        Image(
                            painter = rememberAsyncImagePainter(posterUri),
                            contentDescription = "Preview Poster Baru",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    } else if (posterPath.isNotEmpty()) {

                        // Tampilkan poster lama jika tidak ada upload baru
                        Image(
                            painter = rememberAsyncImagePainter(posterPath),
                            contentDescription = "Poster Lama",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    } else {

                        // Tampilkan icon kamera jika belum ada gambar
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhotoCamera,
                                contentDescription = "Upload Foto",
                                tint = Color(0xFF4F5F59),
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Tap untuk Upload Poster Baru",
                                color = Color(0xFF4F5F59)
                            )
                        }
                    }
                }

                // JUDUL
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    leadingIcon = {
                        Icon(Icons.Default.Movie, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // GENRE
                OutlinedTextField(
                    value = genre,
                    onValueChange = { genre = it },
                    leadingIcon = {
                        Icon(Icons.Default.Category, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // TAHUN
                OutlinedTextField(
                    value = year,
                    onValueChange = { year = it },
                    leadingIcon = {
                        Icon(Icons.Default.DateRange, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // RATING
                OutlinedTextField(
                    value = rating,
                    onValueChange = { rating = it },
                    leadingIcon = {
                        Icon(Icons.Default.Star, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(50)
                )

                // DESKRIPSI
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    leadingIcon = {
                        Icon(Icons.Default.Description, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors(),
                    shape = RoundedCornerShape(24.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // BUTTON UPDATE
                Button(
                    onClick = {
                        onSave(
                            Film(
                                id = existingFilm.id,
                                title = title,
                                genre = genre,
                                year = year.toIntOrNull() ?: 0,
                                rating = rating.toDoubleOrNull() ?: 0.0,
                                description = description,
                                poster = posterPath //Gunakan posterPath yang sudah di-update
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB8484E),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("UPDATE")
                }
            }
        }
    }
}
