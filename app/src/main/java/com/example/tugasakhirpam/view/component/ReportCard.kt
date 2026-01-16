package com.example.tugasakhirpam.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReportCard( //Menampilkan 1 informasi laporan / statistik dalam bentuk kartu
    title: String, //parameter judul kartu
    value: String, //parameter nisi/nilai kartu
    backgroundColor: Color = Color(0xFF8B7D6B), // default coklat
    textColor: Color = Color.White // default putih
) {
    Box( //wadah kotak untuk kartu laporan
        modifier = Modifier
            .fillMaxWidth() //mengisi lebar penuh
            .background(backgroundColor, RoundedCornerShape(12.dp)) //latar belakang dengan warna dan sudut membulat
            .padding(16.dp) //jarak isi ke tepi
    ) {
        Column { //menyusun teks secara vertikal
            Text(
                text = title,
                color = textColor,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = value,
                color = textColor,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
