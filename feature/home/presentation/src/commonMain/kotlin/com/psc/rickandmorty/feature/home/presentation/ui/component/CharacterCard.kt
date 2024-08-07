package com.psc.rickandmorty.feature.home.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.designsystem.spacer.VerticalSpacer
import com.psc.rickandmorty.core.designsystem.util.Dimens

@Composable
internal fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.then(
            Modifier.border(2.dp, Color.Black).background(MaterialTheme.colorScheme.primary)
        )
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = null,
            modifier = Modifier.height(170.dp).fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        VerticalSpacer(Dimens.small)
        Text(text = character.name, color = MaterialTheme.colorScheme.onPrimary)
        VerticalSpacer(Dimens.small)
    }
}