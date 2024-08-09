package com.psc.rickandmorty.feature.details.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.psc.rickandmorty.core.common.domain.model.Episode
import com.psc.rickandmorty.core.common.domain.util.LocalDateFormatter
import com.psc.rickandmorty.core.designsystem.resources.episode_appearances
import com.psc.rickandmorty.core.designsystem.resources.episode_details_placeholder
import com.psc.rickandmorty.core.designsystem.resources.ic_movie
import com.psc.rickandmorty.core.designsystem.theme.AppTheme
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.core.designsystem.util.drawableDesignSystem
import com.psc.rickandmorty.core.designsystem.util.responsiveSp
import com.psc.rickandmorty.core.designsystem.util.stringDesignSystem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private const val DIVIDER_ALPHA = 0.2F
private const val AIR_DATE_ALPHA = 0.8F

@Composable
internal fun EpisodeList(
    episodes: List<Episode>,
    modifier: Modifier = Modifier
) {
    val defaultModifier = Modifier
        .background(AppTheme.colorScheme.surface)
        .border(2.dp, Color.Black)
        .padding(Dimens.default)

    Box(modifier = modifier.then(defaultModifier)) {
        Column(verticalArrangement = Arrangement.spacedBy(Dimens.small)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimens.small, Alignment.Start)
            ) {
                Icon(
                    painter = painterResource(drawableDesignSystem.ic_movie),
                    contentDescription = null,
                    tint = AppTheme.colorScheme.primary,
                    modifier = Modifier.size(Dimens.medium)
                )
                Text(
                    text = stringResource(stringDesignSystem.episode_appearances),
                    fontSize = 18.responsiveSp,
                    fontWeight = FontWeight.Black,
                    color = AppTheme.colorScheme.onSurface
                )
            }
            episodes.forEachIndexed { index, episode ->
                EpisodeDetails(episode)
                if (index != episodes.lastIndex) {
                    HorizontalDivider(color = AppTheme.colorScheme.onSurface.copy(alpha = DIVIDER_ALPHA))
                }
            }
        }
    }
}

@Composable
private fun EpisodeDetails(episode: Episode) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.smallAlt),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(horizontal = Dimens.default, vertical = Dimens.small)
    ) {
        Text(
            text = stringResource(
                stringDesignSystem.episode_details_placeholder,
                episode.name,
                episode.seasonAndEpisode
            ),
            fontSize = 16.responsiveSp,
            fontWeight = FontWeight.Normal,
            color = AppTheme.colorScheme.onSurface
        )
        Text(
            text = LocalDateFormatter.getShortDateFromLocalDate(episode.airDate),
            fontSize = 12.responsiveSp,
            fontWeight = FontWeight.Normal,
            color = AppTheme.colorScheme.onSurface.copy(alpha = AIR_DATE_ALPHA)
        )
    }
}