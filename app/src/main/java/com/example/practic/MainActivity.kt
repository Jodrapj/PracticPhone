package com.example.practic
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.practic.ui.theme.PracticTheme
import com.example.practic.TimeParser

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
                    val time = TimeParser()
                    val btpit = Author(painterResource(id = R.mipmap.ic_launcher_foreground), "BTPIT", time.getTimeNow())
                    val post = Post("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lacus magna tincidunt consectetur lacus fringilla consequat aliquam maximus habitasse. Enim dapibus class dictumst lectus mauris est ipsum nostra id molestie arcu class. Habitant turpis cursus habitasse netus imperdiet egestas augue taciti.",
                        100,100,1_300_000, btpit)
                    post.Draw()
                }
            }
        }
    }
}
