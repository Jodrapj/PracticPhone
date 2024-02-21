package com.example.practic
import android.graphics.drawable.Icon
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.practic.ui.theme.PracticTheme
import org.w3c.dom.Text
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
                        123000,11000,1_300_000, btpit)
                    post.Draw()
                }
            }
        }
    }
}
@Composable
fun getTimeNow(): String {
    val month: String
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
    var minutes: String = LocalDateTime.now().toLocalTime().minute.toString()
    when (minutes) {
        "0" -> minutes = "00"
        "1" -> minutes = "01"
        "2" -> minutes = "02"
        "3" -> minutes = "03"
        "4" -> minutes = "04"
        "5" -> minutes = "05"
        "6" -> minutes = "06"
        "7" -> minutes = "07"
        "8" -> minutes = "08"
        "9" -> minutes = "09"
    }
    val hourMinutes: String = LocalDateTime.now().hour.plus(3).toString() + ":" + minutes
    return LocalDateTime.now().dayOfMonth.toString() + " " + month + " " + hourMinutes
}
class Author( // Author class for post class top row.
    var Avatar: Painter,
    val Name: String,
    val PostTime: String
)
class Post(
    val text: String,
    var likes: Int,
    var reposts: Int,
    var views: Int,
    val Author: Author
) {
    @Composable
    fun Draw() {
        val likeChecked = remember { mutableStateOf(false)}
        val CurrentReposts = remember { mutableStateOf(reposts)}
        val CurrentLikes = remember { mutableStateOf(likes) }
        val CurrentViews = remember { mutableStateOf(views) }
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
                    Text(Author.PostTime)
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
                Column {// Likes click script
                    Row {
                        val v1: String = CurrentLikes.value.toString()
                        IconToggleButton(
                            checked = likeChecked.value,
                            onCheckedChange = {
                                likeChecked.value = it
                                if (likeChecked.value) {
                                    CurrentLikes.value++
                                } else {
                                    CurrentLikes.value--
                                }
                            }) {
                            if (likeChecked.value) {
                                androidx.compose.material3.Icon(
                                    imageVector = icons.Favorite,
                                    contentDescription = v1
                                )
                            } else {
                                androidx.compose.material3.Icon(
                                    imageVector = icons.FavoriteBorder,
                                    contentDescription = v1
                                )
                            }
                        }
                        if (likes < 999)
                            Text(text = v1)
                        else {
                            numberRangeSwitch(CurrentLikes)
                        }
                    }
                }
                Column {// Reposts click script
                    Row {
                        val v1 : String = CurrentReposts.value.toString()
                        IconButton( onClick = {
                            CurrentReposts.value++
                        }) {
                            androidx.compose.material3.Icon(
                                imageVector = icons.Share,
                                contentDescription = v1
                            )
                        }
                        if (CurrentReposts.value <= 999)
                            Text(text = v1)
                        else {
                            numberRangeSwitch(CurrentReposts)
                        }
                    }
                }
                Column {
                    Row {
                        val v1 : String = CurrentViews.value.toString()
                        Icon(icons.AccountCircle, contentDescription = "$views",
                            Modifier
                                .padding(130.dp, 0.dp, 0.dp, 0.dp)
                                )
                        if (views <= 999)
                            Text(text = " $views")
                        else {
                            numberRangeSwitch(CurrentViews)
                        }
                    }
                }
            }
        }
    }
    @Composable
    private fun numberRangeSwitch(mutableState: MutableState<Int>): Unit {
        val v1: String = mutableState.value.toString()
        var result: Unit = Text("")
        when {
            mutableState.value in 1000..1099  -> result = Text(v1[0].toString() + "K")
            mutableState.value in 1100 .. 9999 -> result = Text(v1[0].toString() + "." + v1[1].toString() + "K")
            mutableState.value in 10000 .. 99999 -> result = Text(v1[0].toString() + v1[1].toString() + "K")
            mutableState.value in 100_000 .. 999_999 -> result = Text(v1[0].toString() + v1[1].toString() + v1[2].toString() + "K")
            mutableState.value in 1_000_000 .. 999_999_999 -> result = Text(v1[0].toString() + "." + v1[1].toString() + "M")
        }
        return result
    }
}