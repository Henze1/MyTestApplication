package com.example.mytestapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.RoundedCornersTransformation

@Composable
fun loadImageFromUrl(url: String): Painter {
    val painter: Painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
                transformations(RoundedCornersTransformation())
                scale(Scale.FILL)
            }).build()
        )

    return painter
}
