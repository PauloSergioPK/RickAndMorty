package com.psc.rickandmorty.feature.details.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.psc.rickandmorty.core.common.domain.model.Location
import com.psc.rickandmorty.core.designsystem.resources.current_location
import com.psc.rickandmorty.core.designsystem.resources.dimension
import com.psc.rickandmorty.core.designsystem.resources.ic_home
import com.psc.rickandmorty.core.designsystem.resources.ic_location
import com.psc.rickandmorty.core.designsystem.resources.name
import com.psc.rickandmorty.core.designsystem.resources.origin
import com.psc.rickandmorty.core.designsystem.resources.type
import com.psc.rickandmorty.core.designsystem.theme.AppTheme
import com.psc.rickandmorty.core.designsystem.theme.Colors
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.core.designsystem.util.drawableDesignSystem
import com.psc.rickandmorty.core.designsystem.util.responsiveSp
import com.psc.rickandmorty.core.designsystem.util.stringDesignSystem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun OriginInfo(
    location: Location,
    modifier: Modifier = Modifier
) {
    LocationInfo(
        title = stringResource(stringDesignSystem.origin),
        location = location,
        icon = drawableDesignSystem.ic_home,
        iconTint = Colors.BLUE,
        alignment = InfoAlignment.START,
        backgroundColor = Colors.PASTA,
        modifier = modifier
    )
}

@Composable
internal fun LastKnowLocationInfo(
    location: Location,
    modifier: Modifier = Modifier
) {
    LocationInfo(
        title = stringResource(stringDesignSystem.current_location),
        location = location,
        icon = drawableDesignSystem.ic_location,
        iconTint = Colors.RED,
        alignment = InfoAlignment.END,
        backgroundColor = Colors.MUSTARD,
        modifier = modifier
    )
}

@Composable
private fun LocationInfo(
    title: String,
    location: Location,
    icon: DrawableResource,
    iconTint: Color,
    backgroundColor: Color,
    alignment: InfoAlignment,
    modifier: Modifier = Modifier
) {
    val horizontalAlignment = when (alignment) {
        InfoAlignment.START -> Alignment.Start
        InfoAlignment.END -> Alignment.End
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.smallAlt),
        horizontalAlignment = horizontalAlignment,
        modifier = modifier.then(
            Modifier.background(backgroundColor)
                .border(2.dp, Color.Black)
                .padding(Dimens.default)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.small)
        ) {
            if (alignment == InfoAlignment.START) {
                TitleIcon(
                    icon = icon,
                    iconTint = iconTint
                )
            }
            Text(
                text = title,
                fontSize = 18.responsiveSp,
                fontWeight = FontWeight.Black,
                color = AppTheme.colorScheme.onBackground
            )
            if (alignment == InfoAlignment.END) {
                TitleIcon(
                    icon = icon,
                    iconTint = iconTint
                )
            }
        }

        LabelAndInfo(
            label = stringResource(stringDesignSystem.name),
            info = location.name,
            alignment = alignment
        )
        LabelAndInfo(
            label = stringResource(stringDesignSystem.type),
            info = location.type,
            alignment = alignment
        )
        LabelAndInfo(
            label = stringResource(stringDesignSystem.dimension),
            info = location.dimension,
            alignment = alignment
        )
    }
}

@Composable
private fun TitleIcon(
    icon: DrawableResource,
    iconTint: Color
) {
    Icon(
        painter = painterResource(icon),
        contentDescription = null,
        modifier = Modifier.size(Dimens.default),
        tint = iconTint
    )
}