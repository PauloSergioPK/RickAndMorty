package com.psc.rickandmorty.core.designsystem.text

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.psc.rickandmorty.core.designsystem.util.responsiveSp

@Composable
fun StrokedText(
    modifier: Modifier = Modifier,
    text: String = "",
    minLines: Int = 1,
    textColor: Color = Color.White,
    size: TextUnit = 20.responsiveSp,
    textStrokeColor: Color = Color.Black,
    textStrokeWidth: Float = 6f,
    textStrokeMiter: Float = 6f,
    textStrokeJoin: StrokeJoin = StrokeJoin.Round,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    color = textStrokeColor,
                    fontSize = size,
                    drawStyle = Stroke(
                        width = textStrokeWidth,
                        miter = textStrokeMiter,
                        join = textStrokeJoin
                    )
                )
            ),
            maxLines = maxLines,
            overflow = overflow,
            minLines = minLines,
            textAlign = TextAlign.Center
        )

        Text(
            text = text,
            color = textColor,
            fontSize = size,
            maxLines = maxLines,
            overflow = overflow,
            minLines = minLines,
            textAlign = TextAlign.Center
        )
    }
}
