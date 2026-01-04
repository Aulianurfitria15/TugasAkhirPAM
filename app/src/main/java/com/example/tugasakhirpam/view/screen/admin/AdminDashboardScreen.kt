package com.example.tugasakhirpam.view.screen.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugasakhirpam.view.component.AppTopBar

@Composable
fun AdminDashboardScreen(
    onKelolaFilm: () -> Unit,
    onReport: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Dashboard Admin",
                onLogout = onLogout   // ← PENTING
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = onKelolaFilm,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kelola Film")
            }

            Button(
                onClick = onReport,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lihat Laporan")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🔴 BUTTON LOGOUT

        }
    }
}