package com.example.ngajiq.ui.theme

import com.example.ngajiq.R
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)


val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_bold, FontWeight.Bold)
)
val Otomanopeeone = FontFamily(
    Font(R.font.otomanopeeone_regular, FontWeight.Normal),
)
val AppTypography = Typography(
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
val AppLightBlue = Color(0xFF5696F5) // Background
val AppButtonBlue = Color(0xFF4FC3F7)
val AppTextLinkBlue = Color(0xFF03A9F4)
val AppLightBackgroundBlue = Color(0xFFCEF0FF)
private val LightColorScheme = lightColorScheme(
    primary = AppLightBlue,
    secondary = AppButtonBlue,
    tertiary = AppTextLinkBlue,
)

@Composable
fun NgajiQTheme(
    darkTheme: Boolean = false,

    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {


    MaterialTheme(

        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}