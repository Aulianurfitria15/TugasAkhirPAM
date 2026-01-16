package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tugasakhirpam.view.component.ReportCard
import com.example.tugasakhirpam.view.viewmodel.ReportViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    viewModel: ReportViewModel,
    onBack: () -> Unit
) {

    val total by viewModel.totalFilm.collectAsState()
    val topGenre by viewModel.mostGenre.collectAsState()
    val topRating by viewModel.highestRating.collectAsState()
    val top3Genre by viewModel.top3Genre.collectAsState()

    Scaffold(
        containerColor = Color(0xFF4F5F59), // background utama
        topBar = {
            TopAppBar(
                title = { Text("Laporan", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4F5F59)
                )
            )
        }
    )
    { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Ringkasan Data",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )


            Spacer(modifier = Modifier.height(12.dp))

            ReportCard(
                title = "Total Film",
                value = total.toString(),
                backgroundColor = Color(0xFFB8484E) // merah
            )

            ReportCard(
                title = "Genre Terbanyak",
                value = if (topGenre.isBlank()) "-" else topGenre,
                backgroundColor = Color(0xFFB8484E) // merah
            )

            ReportCard(
                title = "Rating Tertinggi",
                value = topRating.toString(),
                backgroundColor = Color(0xFFB8484E) // merah
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Ringkasan Data",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )


            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.LightGray, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                top3Genre.forEach { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = item.total.toString(),
                            style = MaterialTheme.typography.labelSmall
                        )

                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height((item.total * 15).dp)
                                .background(Color.Blue, RoundedCornerShape(6.dp))
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.genre,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}
