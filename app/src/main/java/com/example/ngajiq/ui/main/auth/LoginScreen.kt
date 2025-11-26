package com.example.ngajiq.ui.main.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ngajiq.R
import com.example.ngajiq.data.viewmodel.AuthViewModel
import com.example.ngajiq.ui.common.CustomTextField
import com.example.ngajiq.ui.common.GoogleButton
import com.example.ngajiq.ui.common.PrimaryButton

// Define your app's colors
val AppLightBlue = Color(0xFF5696F5)
val AppButtonBlue = Color(0xFF4FC3F7)
val AppTextLinkBlue = Color(0xFF03A9F4)

// ---------------------------------------------------------
// 1. STATEFUL COMPOSABLE (Handles Logic & Data)
// Use this inside your Navigation Graph
// ---------------------------------------------------------
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current

    // OBSERVING STATE
    val firebaseUser by authViewModel.userLiveData.observeAsState()
    val toastMsg by authViewModel.toastMessage.observeAsState()

    // Listen for Login Success
    LaunchedEffect(firebaseUser) {
        if (firebaseUser != null) {
            onNavigateToHome()
        }
    }

    // Listen for Errors/Messages
    LaunchedEffect(toastMsg) {
        toastMsg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            authViewModel.clearToast()
        }
    }

    // Pass logic to the stateless content
    LoginContent(
        onLoginClick = { email, pass ->
            authViewModel.login(email, pass)
        },
        onNavigateToRegister = onNavigateToRegister,
        onGoogleClick = { /* TODO */ },
        onForgotPasswordClick = { /* TODO */ }
    )
}

// ---------------------------------------------------------
// 2. STATELESS COMPOSABLE (Handles UI Only)
// Use this for the Preview
// ---------------------------------------------------------
@Composable
fun LoginContent(
    onLoginClick: (String, String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onGoogleClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    // These states are strictly for the UI text fields
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

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
                    // Make sure this drawable exists, otherwise Preview will show empty space
                    Image(
                        painter = painterResource(id = R.drawable.ngaji_header),
                        contentDescription = "Ngaji-Q Header",
                        modifier = Modifier.height(120.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    Box {
                        Text(
                            text = "Selamat datang",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(5.dp),
                        )
                        Spacer(Modifier.height(40.dp))
                        Text(
                            text = "di Ngaji-Q",
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
            }

            // --- Form Section ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(16.dp))

                // Changed "Nama Pengguna" to "Email" because Firebase requires Email
                Text("Email", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
                CustomTextField(
                    label = "Masukkan Email",
                    value = emailInput,
                    onValueChange = { emailInput = it }
                )

                Spacer(Modifier.height(16.dp))

                Text("Kata Sandi", textAlign = TextAlign.Left, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
                CustomTextField(
                    label = "Kata Sandi",
                    value = passwordInput,
                    onValueChange = { passwordInput = it },
                    isPassword = true
                )

                Spacer(Modifier.height(16.dp))

                // Remember Me & Forgot Password
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            colors = CheckboxDefaults.colors(checkedColor = AppButtonBlue)
                        )
                        Text("Ingat saya", fontSize = 14.sp)
                    }
                    ClickableText(
                        text = AnnotatedString("Lupa kata sandi?"),
                        style = TextStyle(color = AppTextLinkBlue, fontSize = 14.sp),
                        onClick = { onForgotPasswordClick() }
                    )
                }
                Spacer(Modifier.height(24.dp))

                // Buttons
                PrimaryButton(
                    text = "MASUK",
                    onClick = { onLoginClick(emailInput, passwordInput) }
                )
                Spacer(Modifier.height(16.dp))

                GoogleButton(onClick = { onGoogleClick() })
                Spacer(Modifier.height(24.dp))

                // Register Link
                Row {
                    Text("Belum memiliki akun? ", fontSize = 14.sp)
                    ClickableText(
                        text = AnnotatedString("Daftar"),
                        style = TextStyle(
                            color = AppTextLinkBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        onClick = { onNavigateToRegister() }
                    )
                }
            }
        }
    }
}

// ---------------------------------------------------------
// 3. PREVIEW (Uses Stateless Content)
// This will now render without crashing
// ---------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // We pass empty lambdas because we don't need logic in the preview
    LoginContent(
        onLoginClick = { _, _ -> },
        onNavigateToRegister = {},
        onGoogleClick = {},
        onForgotPasswordClick = {}
    )
}