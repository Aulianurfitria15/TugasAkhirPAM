package com.example.tugasakhirpam.view.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onBack: (() -> Unit)? = null,
    onLogout: (() -> Unit)? = null
) {
    //menampilkan judul halaman
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White
            )
        },

        //menampiljan icon back
        //jika onBack tidak null maka icon back akan ditampilkan: detail screen
        //jika onBack null maka icon back tidak ditampilkan: dashboard screen
                navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF4F5F59)
        ),

        //menampilkan icon logout
        //jika onLogout tidak null maka icon logout akan ditampilkan: dashboard screen
        //jika onLogout null maka icon logout tidak ditampilkan: detail screen
        actions = {
            if (onLogout != null) {
                IconButton(onClick = onLogout) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout",
                        tint = Color.White
                    )
                }
            }
        }
    )
}
