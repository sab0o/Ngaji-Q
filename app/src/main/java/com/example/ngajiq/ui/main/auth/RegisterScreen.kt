package com.example.ngajiq.ui.main.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.ui.common.CustomTextField
import com.example.ngajiq.ui.common.GoogleButton
import com.example.ngajiq.ui.common.PrimaryButton


@Composable
fun RegisterScreen(
    onLoginClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
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
//                    Image(
//                        painter = painterResource(id = R.drawable.ngaji_header), // Use same header
//                        contentDescription = "Ngaji-Q Header",
//                        modifier = Modifier.height(120.dp)
//                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Yuk Teman-Teman\nGabung Bersama Kami",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            // 2. Form Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.5f) // Give more weight for more fields
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()), // Make form scrollable
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

                PrimaryButton(text = "DAFTAR", onClick = { /* TODO: Handle Register */ })
                Spacer(Modifier.height(16.dp))
                GoogleButton(onClick = { /* TODO: Handle Google Sign-In */ })
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
                        onClick = { onLoginClick() }
                    )
                }
                Spacer(Modifier.height(16.dp)) // Extra space for scroll
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen({})
}