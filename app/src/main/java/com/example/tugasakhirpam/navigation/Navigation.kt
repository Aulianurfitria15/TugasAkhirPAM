package com.example.tugasakhirpam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugasakhirpam.data.helper.InitialDataHelper
import com.example.tugasakhirpam.view.screen.admin.AddFilmScreen
import com.example.tugasakhirpam.view.screen.auth.LoginScreen
import com.example.tugasakhirpam.view.screen.auth.RegisterScreen
import com.example.tugasakhirpam.view.screen.admin.AdminDashboardScreen
import com.example.tugasakhirpam.view.screen.admin.EditFilmScreen
import com.example.tugasakhirpam.view.screen.admin.FilmDetailScreen
import com.example.tugasakhirpam.view.screen.admin.FilmListAdminScreen
import com.example.tugasakhirpam.view.screen.admin.ReportScreen
import com.example.tugasakhirpam.view.screen.user.UserDashboardScreen
import com.example.tugasakhirpam.view.screen.user.UserFilmDetailScreen
import com.example.tugasakhirpam.viewmodel.FilmViewModel
import com.example.tugasakhirpam.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch


//fungsi navigasi ini akan mengatur alur navigasi antar screen di dalam aplikasi
//menentukan ada screen apa aja
//dan bagaimana cara berpindah dari satu screen ke screen lainnya

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val filmViewModel = PenyediaViewModel.provideFilmViewModel(context)
    val authViewModel = PenyediaViewModel.provideAuthViewModel(context)
    val userRepository = PenyediaViewModel.provideUserRepository(context)
    val coroutineScope = rememberCoroutineScope()


    //Menjalankan proses pengecekan & pembuatan user admin SATU KALI
    //saat aplikasi pertama kali dibuka
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                InitialDataHelper.initializeAdminUser(userRepository)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    //wadah semua screen
    NavHost(
        navController = navController,
        startDestination = "login" //ketika di run aplikasi, screen pertama yang muncul adalah login screen
    ) {

        //definisi tiap screen dan cara navigasinya
        //kalau admin login, navigasi ke admin dashboard
        //kalau user biasa login, navigasi ke user dashboard
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onLoginSuccess = { user ->
                    if (user.role == "admin") {
                        navController.navigate("admin_dashboard") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        navController.navigate("user_dashboard") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        //Menampilkan halaman REGISTER (daftar akun)
        //dan mengatur aksi ketika user ingin kembali ke halaman LOGIN
        composable("register") {
            RegisterScreen(
                authViewModel = authViewModel,
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        //Menampilkan halaman dashboard ADMIN dan mengatur aksi navigasi admin
        //(kelola film, lihat laporan, dan logout)
        composable("admin_dashboard") {
            AdminDashboardScreen(
                onKelolaFilm = {
                    navController.navigate("kelola_film")
                },
                onReport = {
                    navController.navigate("report")
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("admin_dashboard") {
                            inclusive = true
                        }
                    }
                }

            )
        }

        //Menampilkan halaman detail film untuk USER
        composable(
            route = "user_film_detail/{filmId}", // Buat route unik
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt("filmId") ?: 0
            UserFilmDetailScreen( // Panggil versi User
                viewModel = filmViewModel,
                filmId = filmId,
                onBack = { navController.popBackStack() }
            )
        }

        //Menampilkan halaman kelola film khusus ADMIN
        composable("kelola_film") {
            FilmListAdminScreen(
                viewModel = filmViewModel,
                onAddClick = { navController.navigate("add_film") },
                onEditClick = { filmId ->
                    navController.navigate("admin_film_detail/$filmId") // Arahkan ke route admin
                },
                onBack = { navController.popBackStack() }
            )
        }

        //Menampilkan halaman detail film untuk ADMIN
        composable(
            route = "admin_film_detail/{filmId}", // Buat route unik
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt("filmId") ?: 0
            FilmDetailScreen( // Panggil versi Admin
                viewModel = filmViewModel,
                filmId = filmId,
                onBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("edit_film/$filmId") },
                onDeleteConfirm = { navController.popBackStack() }
            )
        }

        //Menampilkan halaman edit film untuk ADMIN
        composable(
            route = "edit_film/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt("filmId") ?: 0

            // Ambil data film dari StateFlow untuk di-pass ke EditFilmScreen
            val film by filmViewModel.getFilmById(filmId).collectAsState(initial = null)
            EditFilmScreen(
                film = film,
                onSave = { updatedFilm ->
                    filmViewModel.updateFilm(updatedFilm)

                    // Kembali ke detail screen setelah update
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }

        //Menampilkan halaman tambah film untuk ADMIN
        composable("add_film?filmId={filmId}") { backStackEntry ->
            val filmId =
                backStackEntry.arguments?.getString("filmId")?.toIntOrNull()

            val filmViewModel =
                PenyediaViewModel.provideFilmViewModel(context)

            AddFilmScreen(
                viewModel = filmViewModel,
                //filmId = filmId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        //Menampilkan halaman laporan untuk ADMIN
        composable("report") {
            val reportViewModel =
                PenyediaViewModel.provideReportViewModel(context)

            ReportScreen(
                viewModel = reportViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }


        //Menampilkan halaman dashboard USER dan mengatur aksi navigasi user (lihat detail film & logout)
        composable("user_dashboard") {
            UserDashboardScreen(
                viewModel = filmViewModel,
                onFilmClick = { filmId ->
                    navController.navigate("user_film_detail/$filmId") // Arahkan ke route user
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("user_dashboard") {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
