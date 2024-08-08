package com.psc.rickandmorty.feature.home.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.psc.rickandmorty.core.common.domain.model.CharacterStatus
import com.psc.rickandmorty.core.designsystem.resources.alive
import com.psc.rickandmorty.core.designsystem.resources.dead
import com.psc.rickandmorty.core.designsystem.resources.unknown
import com.psc.rickandmorty.core.designsystem.theme.AppTheme.extendedColors
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.core.designsystem.util.responsiveSp
import com.psc.rickandmorty.core.designsystem.util.stringDesignSystem
import org.jetbrains.compose.resources.stringResource

private const val STROKE_ALPHA = 0.4F

@Composable
internal fun CharacterStatus(
    status: CharacterStatus,
    modifier: Modifier = Modifier
) {
    val shape = CircleShape
    val backgroundColor: Color
    val text: String
    val textColor: Color
    val strokeColor = Color.Black.copy(alpha = STROKE_ALPHA)
    val strokeWidth = 1.dp

    when (status) {
        CharacterStatus.ALIVE -> {
            text = stringResource(stringDesignSystem.alive)
            backgroundColor = extendedColors.success
            textColor = extendedColors.onSuccess
        }

        CharacterStatus.DEAD -> {
            text = stringResource(stringDesignSystem.dead)
            backgroundColor = colorScheme.error
            textColor = colorScheme.onError
        }

        CharacterStatus.UNKNOWN -> {
            text = stringResource(stringDesignSystem.unknown)
            backgroundColor = extendedColors.warning
            textColor = extendedColors.onWarning
        }
    }

    Box(
        modifier = modifier.then(
            Modifier.background(color = backgroundColor, shape = shape)
                .border(width = strokeWidth, color = strokeColor, shape = shape)
                .padding(horizontal = Dimens.default, vertical = Dimens.tinyAlt)
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.responsiveSp
        )
    }
}