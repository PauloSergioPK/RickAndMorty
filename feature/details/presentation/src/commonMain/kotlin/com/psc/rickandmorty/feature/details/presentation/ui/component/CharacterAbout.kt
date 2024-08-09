package com.psc.rickandmorty.feature.details.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.model.CharacterGender
import com.psc.rickandmorty.core.common.domain.model.CharacterStatus
import com.psc.rickandmorty.core.designsystem.resources.alive
import com.psc.rickandmorty.core.designsystem.resources.dead
import com.psc.rickandmorty.core.designsystem.resources.female
import com.psc.rickandmorty.core.designsystem.resources.gender
import com.psc.rickandmorty.core.designsystem.resources.genderless
import com.psc.rickandmorty.core.designsystem.resources.male
import com.psc.rickandmorty.core.designsystem.resources.species
import com.psc.rickandmorty.core.designsystem.resources.status
import com.psc.rickandmorty.core.designsystem.resources.type
import com.psc.rickandmorty.core.designsystem.resources.unknown
import com.psc.rickandmorty.core.designsystem.spacer.VerticalSpacer
import com.psc.rickandmorty.core.designsystem.theme.AppTheme.colorScheme
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.core.designsystem.util.responsiveSp
import com.psc.rickandmorty.core.designsystem.util.stringDesignSystem
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CharacterAbout(
    character: Character,
    modifier: Modifier = Modifier
) {
    val species = character.species
    val type = character.type
    val status = when (character.status) {
        CharacterStatus.ALIVE -> {
            stringResource(stringDesignSystem.alive)
        }

        CharacterStatus.DEAD -> {
            stringResource(stringDesignSystem.dead)
        }

        CharacterStatus.UNKNOWN -> {
            stringResource(stringDesignSystem.unknown)
        }
    }

    val gender = when (character.gender) {
        CharacterGender.MALE -> {
            stringResource(stringDesignSystem.male)
        }

        CharacterGender.FEMALE -> {
            stringResource(stringDesignSystem.female)
        }

        CharacterGender.GENDERLESS -> {
            stringResource(stringDesignSystem.genderless)
        }

        CharacterGender.UNKNOWN -> {
            stringResource(stringDesignSystem.unknown)
        }
    }


    Column(modifier = modifier) {
        Text(
            text = character.name,
            fontSize = 28.responsiveSp,
            fontWeight = FontWeight.SemiBold,
            color = colorScheme.onBackground
        )
        VerticalSpacer(Dimens.small)
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.smallAlt)
        ) {
            Info(
                label = stringResource(stringDesignSystem.status),
                text = status
            )
            if (species.isNotEmpty()) {
                Info(
                    label = stringResource(stringDesignSystem.species),
                    text = species
                )
            }
            if (type.isNotEmpty()) {
                Info(
                    label = stringResource(stringDesignSystem.type),
                    text = type
                )
            }
            Info(
                label = stringResource(stringDesignSystem.gender),
                text = gender
            )
        }
    }
}

@Composable
private fun Info(
    label: String,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.tiny, Alignment.Start)
    ) {
        Text(
            text = label.plus(":"),
            fontSize = 16.responsiveSp,
            fontWeight = FontWeight.Normal,
            color = colorScheme.onBackground.copy(alpha = 0.8F)
        )
        Text(
            text = text,
            fontSize = 16.responsiveSp,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onBackground
        )
    }

}