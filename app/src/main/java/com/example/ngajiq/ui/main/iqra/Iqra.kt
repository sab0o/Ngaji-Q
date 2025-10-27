import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R
import com.example.ngajiq.ui.theme.NgajiQTheme

@Composable
fun IqraScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Lapisan 1: background gradien biru → putih
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF86D1FF), Color.White),
                        startY = 0f,
                        endY = 1000f
                    )
                )
        )

        // Lapisan 2: kontainer putih rounded di bawah
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp), // tinggi header biru
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(34.dp))

                LessonCard(
                    title = "Iqra 1",
                    desc = "Mengenal huruf hijaiyah",
                    progress = 3,
                    isActive = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                LessonCard(
                    title = "Iqra 2",
                    desc = "Belajar membaca kata",
                    progress = 0
                )
                Spacer(modifier = Modifier.height(12.dp))
                LessonCard(
                    title = "Iqra 3",
                    desc = "Belajar mad thabi’i",
                    progress = 0
                )
                Spacer(modifier = Modifier.height(12.dp))
                LessonCard(
                    title = "Iqra 4",
                    desc = "Mengenal tanda bacaan",
                    progress = 0
                )
                Spacer(modifier = Modifier.height(12.dp))
                LessonCard(
                    title = "Iqra 5",
                    desc = "Belajar Waqaf dan Qalqalah",
                    progress = 0
                )
                Spacer(modifier = Modifier.height(12.dp))
                LessonCard(
                    title = "Iqra 6",
                    desc = "Memahami Tajwid",
                    progress = 0
                )
            }
        }
        // Header "Belajar Iqra"
        Text(
            text = "Belajar Iqra",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 37.dp, top = 65.dp, end = 24.dp)
                .align(Alignment.TopStart)
        )
    }
}

@Composable
fun LessonCard(title: String, desc: String, progress: Int, isActive: Boolean = false) {
    val cardColor = if (isActive) Brush.horizontalGradient(
        listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
    ) else Brush.verticalGradient(
        listOf(Color.White, Color.White)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(cardColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isActive) Color.White else Color.Gray
                )
                Text(
                    text = desc,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isActive) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { progress / 30f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(50)),
                    color = if (isActive) Color.White else Color(0xFF42A5F5),
                    trackColor = Color.LightGray,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
                Text(
                    text = "${progress}/30 level   ${progress * 100 / 30}%",
                    fontSize = 10.sp,
                    color = if (isActive) Color.White else Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.iqra),
                contentDescription = "Quran",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IqraPreview() {
    NgajiQTheme {
        IqraScreen()
    }
}
