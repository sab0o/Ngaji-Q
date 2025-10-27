package com.example.ngajiq.ui.main.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.ui.common.CustomTextField
import com.example.ngajiq.ui.common.GoogleButton
import com.example.ngajiq.ui.common.PrimaryButton
import com.example.ngajiq.R

// Define your app's colors
val AppLightBlue = Color(0xFF5696F5) // Background
val AppButtonBlue = Color(0xFF4FC3F7)
val AppTextLinkBlue = Color(0xFF03A9F4)

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
            // 1. Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Note: Replace 'R.drawable.ngaji_header' with your actual image asset
                    Image(
                        painter = painterResource(id = R.drawable.ngaji_header),
                        contentDescription = "Ngaji-Q Header",
                        modifier = Modifier.height(120.dp) // Adjust size as needed
                    )
                    Spacer(Modifier.height(16.dp))
                    Box(){
                        Text(
                            text = "Selamat datang",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                            ,modifier=Modifier.padding(5.dp),
                        )
                        Text(
                            text = "di Ngaji-Q",
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                }
            }

            // 2. Form Section
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
                Text("Nama Pengguna", fontWeight = FontWeight.Bold, fontSize=20.sp , modifier = Modifier.fillMaxWidth())
                CustomTextField(
                    label = "Nama Pengguna",
                    value = username,
                    onValueChange = { username = it }
                )
                Spacer(Modifier.height(16.dp))
                Text("Kata Sandi", textAlign = TextAlign.Left, fontWeight = FontWeight.Bold, fontSize=20.sp , modifier = Modifier.fillMaxWidth() )
                CustomTextField(
                    label = "Kata Sandi",
                    value = password,
                    onValueChange = { password = it },
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

                PrimaryButton(text = "MASUK", onClick = { /* TODO: Handle Login */ })
                Spacer(Modifier.height(16.dp))
                GoogleButton(onClick = { /* TODO: Handle Google Sign-In */ })
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
                        onClick = { onRegisterClick() }
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen({}, {})
}