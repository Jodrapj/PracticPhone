package com.example.practic
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.practic.ui.theme.PracticTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val btpit = Author(painterResource(id = R.mipmap.ic_launcher_foreground), "BTPIT", getTimeNow())
                    val post = Post("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lacus magna tincidunt consectetur lacus fringilla consequat aliquam maximus habitasse. Enim dapibus class dictumst lectus mauris est ipsum nostra id molestie arcu class. Habitant turpis cursus habitasse netus imperdiet egestas augue taciti.",
                        10,10,10, btpit)
                    post.Draw()
                }
            }
        }
    }
}
@Composable
fun getTimeNow(): String {
    val month: String
    val hourMinutes: String
    when (LocalDateTime.now().month.value) {
        1 -> month = stringResource(R.string.january)
        2 -> month = stringResource(R.string.february)
        3 -> month = stringResource(R.string.march)
        4 -> month = stringResource(R.string.april)
        5 -> month = stringResource(R.string.may)
        6 -> month = stringResource(R.string.june)
        7 -> month = stringResource(R.string.july)
        8 -> month = stringResource(R.string.august)
        9 -> month = stringResource(R.string.september)
        10 -> month = stringResource(R.string.october)
        11 -> month = stringResource(R.string.november)
        12 -> month = stringResource(R.string.december)
        else -> {
            month = ""
        }
    }
    hourMinutes = LocalDateTime.now().hour.toString() + ":" + LocalDateTime.now().toLocalTime().minute
    return LocalDateTime.now().dayOfMonth.toString() + " " + month + " " + hourMinutes
}
class Author( // Author class for post class top row.
    var Avatar: Painter,
    val Name: String,
    val PostTime: String
)

class Post(
    val text: String,
    val likes: Int,
    val reposts: Int,
    val views: Int,
    val Author: Author
) {
    @Composable
    fun Draw() {
        val icons = Icons.Default
        Column( // Main UI
            Modifier
                .fillMaxWidth()
                .padding(paddingValues = PaddingValues(10.dp, 20.dp, 10.dp, 20.dp))
                .border(BorderStroke(Dp(2f), Color(168, 168, 168)))
        ) {
            Row( // Top row (Author, info.)
                Modifier
                    .border(BorderStroke(Dp(2f), Color(168, 168, 168)))
                    .fillMaxWidth()
                    .height(Dp(80f))
            ) {
                Image(
                    painter = Author.Avatar,
                    contentDescription = Author.Name
                )
                Column {
                    Text(Author.Name, Modifier.padding(0.dp,18.dp,0.dp,0.dp))
                    Text(Author.PostTime.toString())
                }
            }
            Row( // Main row (Text.)
                Modifier
                    .border(BorderStroke(Dp(2f), Color(168, 168, 168)))
                    .fillMaxWidth()
            ) {
                Text(text = text,
                    Modifier.padding(Dp(10f)))
            }
            Row( // Bottom row (like, repost, etc.)
                Modifier
                    .fillMaxWidth()
                    .height(Dp(40f))
                    .padding(Dp(10f))
            ) {
                Icon(icons.FavoriteBorder, stringResource(R.string.likes))
                Text(text = " $likes")
                Icon(icons.Share, stringResource(R.string.reposts))
                Text(text = " $reposts")
                Icon(icons.AccountCircle, stringResource(R.string.views), Modifier.padding(220.dp, 0.dp, 0.dp, 0.dp))
                Text(text = " $views")
            }
        }
    }
}