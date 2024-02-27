package com.example.practic

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private val timeParser = TimeParser()
interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
}
private val authors: List<Author> = listOf(
    Author(
        Name = "BTPIT",
        PostTime = timeParser.getTimeNow()
    ),
    Author(
        Name = "TEST",
        PostTime = timeParser.getTimeNow()
    )
)
class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            id = 2,
            Author = authors[0],
            text = "Ехал грека через реку видит грека в реке рак сунул грека руку в реку рак за руку греку цап",
            likes = 1,
            reposts = 1,
            views = 1,
        ),
        Post(
            id = 1,
            Author = authors[1],
            text = "test",
            likes = 2,
            reposts = 2,
            views = 2
        )
    )
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy()
        }
        data.value = posts
    }
}
