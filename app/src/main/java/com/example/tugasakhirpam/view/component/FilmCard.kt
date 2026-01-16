package com.example.tugasakhirpam.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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


//Menampilkan 1 data film dalam bentuk kartu (card)
@Composable
fun FilmCard(
    film: Film,
    onClick: () -> Unit
) {

    //untuk mengatur tampilan card film
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick), //pindah ke detail film saat diklik
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Box( //wadah untuk poster film
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray)
            ) {
                //mengambil dan menampilkan poster film
                AsyncImage(
                    model = film.poster,
                    contentDescription = film.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop //menyesuaikan ukuran gambar
                )
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text( //JUDUL FILM
                    film.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text( //GENRE FILM
                    "Genre: ${film.genre}",
                    color = Color.DarkGray
                )

                Text( //RATING FILM
                    "Rating: ${film.rating}",
                    color = Color(0xFFB8484E),
                    fontWeight = FontWeight.SemiBold
                )
                //“Deskripsi dan tahun tidak muncul karena di FilmCard
            // tidak ada komponen Text yang menampilkan field tersebut,
            // meskipun datanya ada di database.”

            }
        }
    }
}
