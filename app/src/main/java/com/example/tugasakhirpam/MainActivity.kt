package com.example.tugasakhirpam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tugasakhirpam.navigation.AppNavigation
import com.example.tugasakhirpam.ui.theme.TugasAkhirPAMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}


@Composable
fun Greeting(name: String) {
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TugasAkhirPAMTheme {
        Greeting("Android")
    }
}