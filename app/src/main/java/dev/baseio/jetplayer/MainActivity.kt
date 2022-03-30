package dev.baseio.jetplayer

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import dev.baseio.jetplayer.ui.theme.JetPlayerTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    hideSystemBars(window)
    super.onCreate(savedInstanceState)
    setContent {
      JetPlayerTheme {
        GramophoneDisc()
      }
    }
  }
}

fun hideSystemBars(window:Window) {
  val windowInsetsController =
    ViewCompat.getWindowInsetsController(window.decorView) ?: return
  // Configure the behavior of the hidden system bars
  windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
  windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  JetPlayerTheme {
    GramophoneDisc()
  }
}