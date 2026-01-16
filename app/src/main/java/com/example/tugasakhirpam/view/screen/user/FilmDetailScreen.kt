package com.example.tugasakhirpam.view.screen.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tugasakhirpam.view.component.AppTopBar
import com.example.tugasakhirpam.viewmodel.FilmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFilmDetailScreen(
    viewModel: FilmViewModel,
    filmId: Int,
    onBack: () -> Unit
) {
    val film by viewModel.getFilmById(filmId).collectAsState(initial = null)

    Scaffold(
        containerColor = Color(0xFF4F5F59),
        topBar = {
            AppTopBar(
                title = "Detail Film",
                onBack = onBack
            )
        }
    ) { padding ->
        film?.let { filmDetail ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {

                //POSTER
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .padding(bottom = 12.dp)
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                ) {
                    AsyncImage(
                        model = filmDetail.poster,
                        contentDescription = filmDetail.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp),
                        contentScale = ContentScale.Crop
                    )
                }


                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        //JUDUL + RATING
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = filmDetail.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Surface(
                                color = Color(0xFFFFC107),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "⭐ ${filmDetail.rating}",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Text(
                            text = "${filmDetail.genre} • ${filmDetail.year}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Deskripsi",
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = filmDetail.description,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}
