package com.example.ngajiq.ui.main.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.R
import com.example.ngajiq.data.viewmodel.AuthViewModel
import com.example.ngajiq.ui.navigation.Routes
import com.google.firebase.auth.FirebaseAuth

// --- Color Definitions ---
val BlueLight = Color(0xFF64B5F6)
val BluePrimary = Color(0xFF42A5F5)
val BgLightBlue = Color(0xFFF0F4F8)
val RedSoft = Color(0xFFFF8A80)
val YellowAccent = Color(0xFFFFD54F)

@Composable
fun ProfileScreen(onLogoutClick: () -> Unit) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    val username = remember {
        currentUser?.displayName?.ifBlank { null }
            ?: currentUser?.email?.substringBefore("@")
            ?: "Teman Ngaji"
    }
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(BlueLight, BluePrimary)
    )

    Scaffold(
//        bottomBar = { BottomNavigationBar() },
        containerColor = BgLightBlue
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(gradientBrush)
        ) {
            // 1. Top Header Content (Title)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Profil",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(30.dp))
            }

            // 2. Main White Content Sheet
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 90.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(BgLightBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    // 3. Profile Info Card
                    ProfileInfoCard(username)

                    Spacer(modifier = Modifier.height(32.dp))

                    // 4. Menu Options
                    MenuOptionsList(onLogoutClick)

                    Spacer(modifier = Modifier.weight(1f))

                    // 5. Version Text
                    Text(
                        text = "v0.0.0.1",
                        color = BlueLight,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileInfoCard(username: String) {
    val cardGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF64B5F6), Color(0xFF42A5F5))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardGradient)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar with white border
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF81C784)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.brokolibenar),
                    contentDescription = "Profile Photo",

                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = username,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Level and XP
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Lvl.1", color = Color.White, fontSize = 10.sp)
                    Text(text = "2/15 XP", color = Color.White, fontSize = 10.sp)
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Progress Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.White.copy(alpha = 0.5f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.13f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(3.dp))
                            .background(YellowAccent)
                    )
                }
            }
        }
    }
}

@Composable
fun MenuOptionsList(onLogoutClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MenuItem(
            icon = Icons.Outlined.FavoriteBorder,
            text = "Favorit"
        )
        MenuItem(
            icon = Icons.Outlined.Delete,
            text = "Ubah kata sandi"
        )
        MenuItem(
            icon = Icons.Outlined.Description,
            text = "Syarat & Ketentuan"
        )
        MenuItem(
            icon = Icons.AutoMirrored.Outlined.HelpOutline,
            text = "Bantuan"
        )
        LogoutItem(onLogoutClick)
    }
}

@Composable
fun MenuItem(
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BlueLight,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            color = BlueLight,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = BlueLight
        )
    }
    HorizontalDivider(thickness = 1.dp, color = BlueLight.copy(alpha = 0.2f))
}

@Composable
fun LogoutItem(onLogoutClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{onLogoutClick()}
            .clip(RoundedCornerShape(8.dp))
            .background(RedSoft)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "Keluar",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.White
        )
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        onLogoutClick = {},
    )
}