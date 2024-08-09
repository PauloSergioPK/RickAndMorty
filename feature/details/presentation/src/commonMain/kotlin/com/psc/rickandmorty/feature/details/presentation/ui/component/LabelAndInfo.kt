package com.psc.rickandmorty.feature.details.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.psc.rickandmorty.core.designsystem.theme.AppTheme
import com.psc.rickandmorty.core.designsystem.util.Dimens
import com.psc.rickandmorty.core.designsystem.util.responsiveSp

enum class InfoAlignment {
    START,
    END
}

@Composable
internal fun LabelAndInfo(
    label: String,
    info: String,
    alignment: InfoAlignment = InfoAlignment.START,
    modifier: Modifier = Modifier
) {
    val horizontalAlignment = when (alignment) {
        InfoAlignment.START -> Alignment.Start
        InfoAlignment.END -> Alignment.End
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.tiny, horizontalAlignment),
        modifier = modifier
    ) {
        Text(
            text = label.plus(":"),
            fontSize = 16.responsiveSp,
            fontWeight = FontWeight.Normal,
            color = AppTheme.colorScheme.onBackground.copy(alpha = 0.8F)
        )
        Text(
            text = info,
            fontSize = 16.responsiveSp,
            fontWeight = FontWeight.Medium,
            color = AppTheme.colorScheme.onBackground
        )
    }
}