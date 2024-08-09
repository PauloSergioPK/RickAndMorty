package com.psc.rickandmorty.feature.details.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            LabelAndInfo(
                label = stringResource(stringDesignSystem.status),
                info = status
            )
            if (species.isNotEmpty()) {
                LabelAndInfo(
                    label = stringResource(stringDesignSystem.species),
                    info = species
                )
            }
            if (type.isNotEmpty()) {
                LabelAndInfo(
                    label = stringResource(stringDesignSystem.type),
                    info = type
                )
            }
            LabelAndInfo(
                label = stringResource(stringDesignSystem.gender),
                info = gender
            )
        }
    }
}