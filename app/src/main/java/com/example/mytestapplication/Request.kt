package com.example.mytestapplication

import androidx.compose.ui.graphics.painter.Painter

class Request(
    var result: String,
    var name: String,
    var image: Painter,
) {
    val results: Any
        get() {
            TODO()
        }
}