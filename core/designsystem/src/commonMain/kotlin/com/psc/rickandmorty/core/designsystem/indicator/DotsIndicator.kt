package com.psc.rickandmorty.core.designsystem.indicator

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.psc.rickandmorty.core.designsystem.util.responsiveDp

@Composable
fun DotsIndicator(
    count: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.surface,
    dotSize: Int = 8,
    spacing: Int = 4,
    onDotClicked: (Int) -> Unit = {}
) {
    Row(modifier = modifier) {
        repeat(count) { index ->
            val isSelected = selectedIndex == index
            val dotColor = animateColorAsState(
                targetValue = if (isSelected) selectedColor else unSelectedColor
            )
            Box(
                modifier = Modifier
                    .size(dotSize.responsiveDp)
                    .clip(RoundedCornerShape(50))
                    .background(dotColor.value)
                    .clickable {
                        onDotClicked.invoke(index)
                    }
            )
            Spacer(modifier = Modifier.width(spacing.responsiveDp))
        }
    }
}

@Composable
fun RectangularIndicator(
    count: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.surface,
    selectedRectangularWidth: Int = 38,
    selectedRectangularHeight: Int = 8,
    unSelectedRectangularWidth: Int = 8,
    unSelectedRectangularHeight: Int = 8,
    spacing: Int = 4,
    onDotClicked: (Int) -> Unit = {}
) {
    Row(modifier = modifier) {
        repeat(count) { index ->
            val isSelected = selectedIndex == index
            val dotColor = animateColorAsState(
                targetValue = if (isSelected) selectedColor else unSelectedColor
            )

            val width = animateDpAsState(
                if (isSelected) {
                    selectedRectangularWidth.responsiveDp
                } else unSelectedRectangularWidth.responsiveDp
            )
            val height = animateDpAsState(
                if (isSelected) {
                    selectedRectangularHeight.responsiveDp
                } else unSelectedRectangularHeight.responsiveDp
            )

            Box(
                modifier = Modifier
                    .width(width.value)
                    .height(height.value)
                    .clip(RoundedCornerShape(50))
                    .background(dotColor.value)
                    .clickable {
                        onDotClicked.invoke(index)
                    }
            )
            Spacer(modifier = Modifier.width(spacing.responsiveDp))
        }
    }
}