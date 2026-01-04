package com.example.tugasakhirpam.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tugasakhirpam.data.model.Film

@Composable
fun FilmCard(
    film: Film,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = film.poster,
                contentDescription = film.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    film.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    "Genre: ${film.genre}",
                    color = Color.DarkGray
                )

                Text(
                    "Rating: ${film.rating}",
                    color = Color(0xFFB8484E), // 🔥 aksen merah
                    fontWeight = FontWeight.SemiBold
                )

            }
        }
    }
}
