package com.example.ngajiq.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R


// ---------------- LIGHT COLOR SCHEME ----------------

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = Color.White,

    primaryContainer = SoftAzure,
    onPrimaryContainer = Color.Black,

    secondary = AquaBlue,
    onSecondary = Color.Black,

    secondaryContainer = IceBlue,
    onSecondaryContainer = Color.Black,

    tertiary = DeepBlue,
    onTertiary = Color.White,

    background = Color(0xFFF9FBFF),
    onBackground = Color(0xFF0F1A2A),

    surface = Color.White,
    onSurface = Color(0xFF0F1A2A),

    surfaceVariant = IceBlue,
    onSurfaceVariant = Color(0xFF274060),

    outline = Color(0xFF6D86A6)
)

// ---------------- DARK COLOR SCHEME ----------------

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = Color.Black,

    primaryContainer = DeepBlue,
    onPrimaryContainer = IceBlue,

    secondary = AquaBlue,
    onSecondary = Color.Black,

    secondaryContainer = SoftAzure,
    onSecondaryContainer = Color.Black,

    tertiary = IceBlue,
    onTertiary = Color.Black,

    background = Color(0xFF0D1117),
    onBackground = IceBlue,

    surface = Color(0xFF12161C),
    onSurface = Color(0xFFE7F5FF),

    surfaceVariant = Color(0xFF182028),
    onSurfaceVariant = Color(0xFFBFD9FF),

    outline = Color(0xFF5F6E82)
)

// ---------------- FONTS ----------------

val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_bold, FontWeight.Bold)
)

// ---------------- TYPOGRAPHY ----------------

val AppTypography = androidx.compose.material3.Typography(
    bodyLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    )
)

// ---------------- THEME WRAPPER ----------------

@Composable
fun NgajiQTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
