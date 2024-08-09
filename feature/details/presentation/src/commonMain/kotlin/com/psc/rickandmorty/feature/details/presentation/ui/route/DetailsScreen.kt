package com.psc.rickandmorty.feature.details.presentation.ui.route

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.designsystem.scaffold.ScrollableScreenContainer
import com.psc.rickandmorty.core.designsystem.spacer.VerticalSpacer
import com.psc.rickandmorty.core.designsystem.theme.AppTheme
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.feature.details.presentation.ui.component.CharacterAbout
import com.psc.rickandmorty.feature.details.presentation.ui.component.LastKnowLocationInfo
import com.psc.rickandmorty.feature.details.presentation.ui.component.OriginInfo

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
            VerticalSpacer(Dimens.biggerAlt)
            character.origin?.let {
                OriginInfo(it)
                VerticalSpacer(Dimens.biggerAlt)
            }
            character.lastKnownLocation?.let {
                LastKnowLocationInfo(location = it, modifier = Modifier.align(Alignment.End))
                VerticalSpacer(Dimens.biggerAlt)
            }
        }

    }
}