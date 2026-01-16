package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tugasakhirpam.view.component.AppTopBar
import com.example.tugasakhirpam.view.component.FilmCard
import com.example.tugasakhirpam.viewmodel.FilmViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListAdminScreen(
    viewModel: FilmViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    var keyword by remember { mutableStateOf("") }

    val films by if (keyword.isEmpty()) {
        viewModel.filmList.collectAsState(initial = emptyList())
    } else {
        viewModel.searchFilm(keyword)
            .collectAsState(initial = emptyList())
    }

    Scaffold(
        containerColor = Color(0xFF4F5F59), // background hijau
        topBar = {
            AppTopBar(
                title = "Kelola Film",
                onBack = onBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = Color(0xFFB8484E), // merah
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Film")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // SEARCH
            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                label = { Text("Cari film") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Cari"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF4F5F59),
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color(0xFF4F5F59)
                ),
                shape = RoundedCornerShape(50)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // LIST FILM
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = films,
                    key = { film -> film.id }
                ) { film ->
                    FilmCard(
                        film = film,
                        onClick = { onEditClick(film.id) }
                    )
                }
            }
        }
    }
}
