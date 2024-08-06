package com.psc.rickandmorty.core.designsystem.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.paintOver(brush: Brush, alpha: Float = 0.99F) = this.then(
    Modifier
        .graphicsLayer(alpha = alpha)
        .drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(brush, blendMode = BlendMode.SrcAtop)
            }
        }
)