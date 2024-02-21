package com.example.practic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
        val likeChecked = remember { mutableStateOf(false) }
        val CurrentReposts = remember { mutableStateOf(reposts) }
        val CurrentLikes = remember { mutableStateOf(likes) }
        val CurrentViews = remember { mutableStateOf(views) }
        val icons = Icons.Default
        Column( // Main UI
            Modifier
                .fillMaxWidth()
                .padding(paddingValues = PaddingValues(10.dp, 20.dp, 10.dp, 20.dp))
                .shadow(1.dp)
                .border(BorderStroke(Dp(2f), Color(168, 168, 168)))
        ) {
            Row( // Top row (Author, info.)
                Modifier
                    //.border(BorderStroke(Dp(0f), Color(168, 168, 168)))
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
                    .shadow(2.dp)
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
                                Icon(
                                    imageVector = icons.Favorite,
                                    contentDescription = v1
                                )
                            } else {
                                Icon(
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
                            Icon(
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
                Column(
                    horizontalAlignment = Alignment.End
                ) {
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
    private fun numberRangeSwitch(mutableState: MutableState<Int>): Unit { // Switches display based on the size of the number
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