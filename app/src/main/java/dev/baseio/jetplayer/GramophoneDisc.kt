package dev.baseio.jetplayer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import dev.baseio.jetplayer.ui.CircularList
import dev.baseio.jetplayer.ui.theme.JetPlayerTheme
import kotlin.math.min


val discBackground = Color(229, 236, 229)

val colorRedStart = Color(230, 100, 91)

val innerDisc = Color(52, 46, 45)

@Composable
fun GramophoneDisc() {
  val screenHeight = LocalConfiguration.current.screenHeightDp
  val screenWidth = LocalConfiguration.current.screenWidthDp
  val gramophoneSize = min(screenWidth, screenHeight)
  val radius = gramophoneSize.div(2)

  val y = screenHeight.div(2).minus(radius)
  val x = 0.minus(radius)

  val offsetDisc = Offset(x.toFloat(), y.toFloat())

  Box(
    modifier = Modifier.fillMaxSize()
      .background(discBackground)
  ) {
    AlbumArtBackground()
    BlurredAlbumArt()
    Disc(gramophoneSize, offsetDisc)
    AlbumSongsList(gramophoneSize,offsetDisc)
  }
}

@Composable
private fun AlbumArtBackground() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(colorRedStart)
  )
}

@Composable
fun AlbumSongsList(gramophoneSize: Int, offsetDisc: Offset) {
  CircularList(
    modifier = Modifier
      .size(gramophoneSize.dp)
      .offset(
        y = offsetDisc.y.dp,
        x = offsetDisc.x.div(2.4).dp
      ),
    circularFraction = 1f,
    visibleItems = 5,
  ) {
    repeat(10) {
      Album()
    }
  }
}

@Composable
fun Album() {
  GlideImage(
    imageModel = "https://wpimg.pixelied.com/blog/wp-content/uploads/2021/06/15134504/Spotify-Cover-Art-with-Text-Aligned-480x480.png",
    contentDescription = null,
  )
}

@Composable
private fun BlurredAlbumArt() {
  GlideImage(
    imageModel = "https://wpimg.pixelied.com/blog/wp-content/uploads/2021/06/15134504/Spotify-Cover-Art-with-Text-Aligned-480x480.png",
    contentDescription = null, modifier = Modifier
      .fillMaxSize()
      .blur(16.dp),
    previewPlaceholder = R.drawable.ic_launcher_background
  )
}

@Composable
private fun InnerRing(
  modifier: Modifier,
) {
  GlideImage(
    imageModel = "https://wpimg.pixelied.com/blog/wp-content/uploads/2021/06/15134504/Spotify-Cover-Art-with-Text-Aligned-480x480.png",
    contentDescription = null, modifier = modifier
      .clip(
        shape = CircleShape
      ), previewPlaceholder = R.drawable.ic_launcher_background
  )
}

@Composable
private fun Disc(
  gramophoneSize: Int,
  offsetDisc: Offset
) {
  val infiniteRotate = rememberInfiniteTransition()
  val rotateAnimation by infiniteRotate.animateFloat(
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = InfiniteRepeatableSpec(
      tween(durationMillis = 6000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    )
  )

  Box(
    modifier = Modifier
      .offset(
        y = offsetDisc.y.dp,
        x = offsetDisc.x.dp
      )
      .size(gramophoneSize.dp)
      .rotate(rotateAnimation)
      .background(
        discBlackGradient(), shape = CircleShape
      )
  ) {
    InnerRing(
      Modifier
        .size(gramophoneSize.dp.div(2))
        .align(Alignment.Center)
    )
  }


}

@Composable
private fun discBlackGradient() = Brush.radialGradient(
  colors = listOf(
    innerDisc,
    innerDisc.copy(alpha = 0.8f),
    innerDisc.copy(alpha = 0.7f),
    innerDisc.copy(alpha = 0.6f),
    innerDisc,
    innerDisc,
    innerDisc.copy(alpha = 0.6f),
    innerDisc.copy(alpha = 0.7f),
    innerDisc.copy(alpha = 0.8f),
    innerDisc,
  )
)

@Preview()
@Composable
fun PreviewGramophone() {
  JetPlayerTheme {
    GramophoneDisc()
  }
}