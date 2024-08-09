package com.psc.rickandmorty.core.designsystem.scaffold

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.psc.rickandmorty.core.designsystem.resources.ic_arrow_back
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.core.designsystem.util.drawableDesignSystem
import com.psc.rickandmorty.core.designsystem.util.responsiveDp
import org.jetbrains.compose.resources.painterResource

private const val CORNER = 42
private const val IMAGE_HEIGHT = 300
private const val PARALLAX_COEFFICIENT = 0.75F

@Composable
fun ScrollableScreenContainer(
    onNavigateBack: () -> Unit,
    imageUri: String?,
    scrollableContent: @Composable () -> Unit
) {
    Scaffold {
        val lazyListState = rememberLazyListState()
        val cornerSizeInPx = with(LocalDensity.current) { CORNER.dp.toPx() }

        fun getCollapsingProgress(): Float {
            val visibleItemsInfo = lazyListState.layoutInfo.visibleItemsInfo
            val imageInfo = visibleItemsInfo.find { it.index == 0 }
            val imageSize = imageInfo?.size?.minus(cornerSizeInPx)
            val imageOffset = imageInfo?.offset?.toFloat() ?: 0F

            var progress = (imageSize?.plus(imageOffset))?.div(imageSize)
            if (progress == null || progress < 0) progress = 0F

            return progress
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Image(
                        imageUri = imageUri,
                        lazyListState = lazyListState,
                        getCollapsingProgress = { getCollapsingProgress() },
                    )
                }
                item { scrollableContent() }
            }

            ToolbarContainer(
                getCollapsingProgress = { getCollapsingProgress() },
                onNavigateBack = onNavigateBack
            )
        }
    }
}

@Composable
internal fun Image(
    imageUri: String?,
    lazyListState: LazyListState,
    getCollapsingProgress: () -> Float
) {
    var scrolledY = 0f
    var previousOffset = 0

    Box(modifier = Modifier.fillMaxWidth()) {
        val imageModifier = Modifier
            .height(IMAGE_HEIGHT.responsiveDp)
            .fillMaxWidth()
            .graphicsLayer {
                scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                translationY = scrolledY * PARALLAX_COEFFICIENT
                previousOffset = lazyListState.firstVisibleItemScrollOffset
                alpha = getCollapsingProgress()
            }

        AsyncImage(
            model = imageUri,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = imageModifier,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(CORNER.dp)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    val progress = getCollapsingProgress()
                    val animatedCorner = CORNER - (CORNER - (CORNER * progress))

                    shape = RoundedCornerShape(
                        topStart = animatedCorner.dp,
                        topEnd = animatedCorner.dp
                    )
                    clip = true
                }
                .background(colorScheme.background)
        )
    }
}


@Composable
fun ToolbarContainer(
    getCollapsingProgress: () -> Float,
    onNavigateBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        ToolbarShadow(getCollapsingProgress)
        ToolbarBackground(getCollapsingProgress)
        ToolbarIcons(onNavigateBack)
    }
}

@Composable
private fun ToolbarShadow(getCollapsingProgress: () -> Float) {
    val shadowBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Black.copy(alpha = 0.85F),
            Color.Transparent
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.responsiveDp)
            .graphicsLayer {
                alpha = getCollapsingProgress()
            }
            .background(shadowBrush)
    )
}

@Composable
private fun BoxScope.ToolbarBackground(getCollapsingProgress: () -> Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                val progress = getCollapsingProgress()
                alpha = when {
                    progress <= 0.1F -> 1F - progress
                    progress <= 0.15F -> 0.9F - progress
                    progress <= 0.2F -> 0.8F - progress
                    progress <= 0.25F -> 0.7F - progress
                    progress <= 0.3F -> 0.6F - progress
                    progress <= 0.35F -> 0.5F - progress
                    progress <= 0.4F -> 0.4F - progress
                    progress <= 0.45F -> 0.3F - progress
                    progress <= 0.5F -> 0.2F - progress
                    progress <= 0.55F -> 0.1F - progress
                    progress <= 0.6F -> 0.05F - progress
                    else -> 0F
                }
            }
            .background(colorScheme.background)
            .statusBarsPadding()
            .padding(top = 52.responsiveDp)
            .align(Alignment.TopCenter)
    ) {
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = colorScheme.onBackground.copy(alpha = 0.3F)
        )
    }
}

@Composable
private fun BoxScope.ToolbarIcons(
    onNavigateBack: () -> Unit
) {
    val iconBackgroundAlpha = 0.5F

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = Dimens.small, horizontal = Dimens.default)
            .align(Alignment.TopCenter)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(36.responsiveDp)
                .clip(CircleShape)
                .background(Color.Black.copy(iconBackgroundAlpha))
                .clickable(onClick = onNavigateBack)
        ) {
            Icon(
                painter = painterResource(drawableDesignSystem.ic_arrow_back),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(18.responsiveDp)
                    .offset(x = -1.dp)
            )
        }
    }
}