package com.psc.rickandmorty.feature.home.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.designsystem.spacer.VerticalSpacer
import com.psc.rickandmorty.core.designsystem.text.StrokedText
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.core.designsystem.util.shimmerBrush

@Composable
internal fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(Dimens.defaultAlt)
    val strokeColor = Color.Black
    val strokeWidth = 2.dp

    Box(
        modifier = modifier.then(
            Modifier.clip(shape)
                .border(width = strokeWidth, color = strokeColor, shape = shape)
                .background(color = MaterialTheme.colorScheme.primary, shape = shape)
                .padding(bottom = Dimens.defaultAlt)
        ),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier.height(170.dp).fillMaxWidth(),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(shimmerBrush())
                    )
                }
            )
            HorizontalDivider(color = strokeColor, thickness = strokeWidth)
            VerticalSpacer(Dimens.small)
            StrokedText(
                modifier = Modifier.fillMaxWidth().padding(horizontal = Dimens.smallAlt),
                text = character.name,
                textColor = MaterialTheme.colorScheme.onPrimary,
            )
        }
        CharacterStatus(
            status = character.status,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = Dimens.small, end = Dimens.small)
        )
    }
}

@Composable
internal fun CharacterLoadingCard() {
    val shape = RoundedCornerShape(Dimens.defaultAlt)
    val strokeColor = Color.Black
    val strokeWidth = 2.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape)
            .border(width = strokeWidth, color = strokeColor, shape = shape)
            .background(color = MaterialTheme.colorScheme.primary, shape = shape)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(shimmerBrush()))
    }
}
