package com.example.tugasakhirpam.view.screen.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugasakhirpam.view.component.AppTopBar
import com.example.tugasakhirpam.view.component.FilmCard
import com.example.tugasakhirpam.viewmodel.FilmViewModel

@Composable
fun UserDashboardScreen(
    viewModel: FilmViewModel,
    onFilmClick: (Int) -> Unit,
    onLogout: () -> Unit
) {
    var keyword by remember { mutableStateOf("") }

    val films by if (keyword.isEmpty()) {
        viewModel.filmList.collectAsState()
    } else {
        viewModel.searchFilm(keyword).collectAsState(initial = emptyList())
    }

    Scaffold(
        containerColor = Color(0xFF4F5F59), // 🔥 background utama
        topBar = {
            AppTopBar(
                title = "Halo, User",
                onLogout = onLogout
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                placeholder = { Text("Cari film atau genre") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFFB8484E),
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(50)
            )


            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(
                    items = films,
                    key = { film -> film.id }
                ) { film ->
                    FilmCard(
                        film = film,
                        onClick = { onFilmClick(film.id) }
                    )
                }
            }
        }
    }
}
