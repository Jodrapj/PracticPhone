package com.example.practic

import android.inputmethodservice.Keyboard
import android.media.Image
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.practic.ui.theme.PracticTheme
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
                    val post = Post()
                    post.Draw("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lacus magna tincidunt consectetur lacus fringilla consequat aliquam maximus habitasse. Enim dapibus class dictumst lectus mauris est ipsum nostra id molestie arcu class. Habitant turpis cursus habitasse netus imperdiet egestas augue taciti.",
                    10,10,10)
                }
            }
        }
    }
}

class Author: Post { // Author class for post class top row.
    var Avatar: Image
    val Name: String
    val PostTime: String
    constructor(Avatar: Image, Name: String, PostTime: String) {
        this.Avatar = Avatar
        this.Name = Name
        this.PostTime = PostTime
    }
}
open class Post {
    @Composable
    fun Draw(text: String, likes: Int, reposts: Int, views: Int) {
        val icons = Icons.Default
        Column(
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