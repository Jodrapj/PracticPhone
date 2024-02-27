package com.example.practic
import android.app.Activity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practic.ui.theme.PracticTheme
import com.example.practic.TimeParser
import org.jetbrains.annotations.Nullable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ThisViewModel by viewModels()
            viewModel.data.observe(this) { posts ->
            posts.map {post ->
                Post(
                    Author = post.Author,
                    text = post.text,
                    id = post.id,
                    likes = post.likes,
                    reposts = post.reposts,
                    views = post.views
                )
            }.forEach {
                setContent {
                    Surface(
                        content = it.DrawPost()
                    ) {
                    }
                }
            }
        }
    }
}
