package com.psc.rickandmorty.feature.details.presentation.ui.route

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.designsystem.scaffold.ScrollableScreenContainer
import com.psc.rickandmorty.core.designsystem.spacer.VerticalSpacer
import com.psc.rickandmorty.core.designsystem.theme.AppTheme
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.feature.details.presentation.ui.component.CharacterAbout

@Composable
internal fun DetailsScreen(
    character: Character,
    onNavigateBackClicked: () -> Unit
) {
    ScrollableScreenContainer(
        onNavigateBack = onNavigateBackClicked,
        imageUri = character.image,
    ) {
        Column(modifier = Modifier.fillMaxWidth().background(AppTheme.colorScheme.background)) {
            CharacterAbout(
                character = character,
                modifier = Modifier.padding(start = Dimens.bigAlt)
            )
            VerticalSpacer(1000.dp) //fixme
        }

    }
}