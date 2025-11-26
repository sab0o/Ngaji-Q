package com.example.ngajiq.ui.main.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.ui.common.CustomTextField
import com.example.ngajiq.ui.common.GoogleButton
import com.example.ngajiq.ui.common.PrimaryButton
import com.example.ngajiq.R
import com.example.ngajiq.data.viewmodel.AuthViewModel
import com.example.ngajiq.ui.navigation.Routes

// ---------------------------------------------------------
// 1. STATEFUL COMPOSABLE (Logic, ViewModel, Validation)
// ---------------------------------------------------------
@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    val firebaseUser by authViewModel.userLiveData.observeAsState()
    val toastMsg by authViewModel.toastMessage.observeAsState()
    val context = LocalContext.current

    // 1. Listen for Register Success
    LaunchedEffect(firebaseUser) {
        if (firebaseUser != null) {
            onNavigateToHome()
        }
    }

    // 2. Listen for Errors/Messages
    LaunchedEffect(toastMsg) {
        toastMsg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            authViewModel.clearToast()
        }
    }

    // 3. Render the UI
    RegisterContent(
        onRegisterClick = { username, email, password, confirmPassword ->
            // Perform Validation Logic Here
            if (password != confirmPassword) {
                Toast.makeText(context, "Kata sandi tidak cocok", Toast.LENGTH_SHORT).show()
            } else {
                // Call the ViewModel to register
                authViewModel.register(email, password)
            }
        },
        onNavigateToLogin = onNavigateToLogin,
        onGoogleClick = { /* TODO: Google Auth */ }
    )
}

// ---------------------------------------------------------
// 2. STATELESS COMPOSABLE (UI Only - Safe for Preview)
// ---------------------------------------------------------
@Composable
fun RegisterContent(
    onRegisterClick: (String, String, String, String) -> Unit, // username, email, pass, confirm
    onNavigateToLogin: () -> Unit,
    onGoogleClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppLightBlue)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Header Section ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Yuk Teman-Teman",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Gabung Bersama Kami",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ngaji_header),
                    contentDescription = "Ngaji-Q Header",
                    modifier = Modifier
                        .height(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }

            // --- Form Section ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.5f)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(16.dp))
                CustomTextField(
                    label = "Nama Pengguna",
                    value = username,
                    onValueChange = { username = it }
                )
                Spacer(Modifier.height(16.dp))
                CustomTextField(
                    label = "Alamat Email",
                    value = email,
                    onValueChange = { email = it }
                )
                Spacer(Modifier.height(16.dp))
                CustomTextField(
                    label = "Kata Sandi",
                    value = password,
                    onValueChange = { password = it },
                    isPassword = true
                )
                Spacer(Modifier.height(16.dp))
                CustomTextField(
                    label = "Konfirmasi Kata Sandi",
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    isPassword = true
                )
                Spacer(Modifier.height(24.dp))

                // Register Button connected to logic
                PrimaryButton(
                    text = "DAFTAR",
                    onClick = {
                        onRegisterClick(username, email, password, confirmPassword)
                    }
                )
                Spacer(Modifier.height(16.dp))

                GoogleButton(onClick = { onGoogleClick() })
                Spacer(Modifier.height(24.dp))

                // Login Link
                Row {
                    Text("Sudah punya akun? ", fontSize = 14.sp)
                    ClickableText(
                        text = AnnotatedString("Masuk"),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.tertiary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        onClick = { onNavigateToLogin() }
                    )
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

// ---------------------------------------------------------
// 3. PREVIEW (Uses Stateless Content)
// ---------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    var navController = rememberNavController()
    RegisterContent(
        onRegisterClick = { _, _, _, _ -> },
        onNavigateToLogin = {navController.navigate(Routes.LOGIN)},
        onGoogleClick = {}
    )
}