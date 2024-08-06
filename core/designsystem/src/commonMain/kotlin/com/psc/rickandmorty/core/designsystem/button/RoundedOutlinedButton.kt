package com.psc.rickandmorty.core.designsystem.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.psc.rickandmorty.core.designsystem.spacer.HorizontalSpacer
import com.psc.rickandmorty.core.designsystem.util.responsiveDp
import com.psc.rickandmorty.core.designsystem.util.responsiveSp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoundedOutlinedButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    strokeColor: Color = MaterialTheme.colorScheme.surface,
    strokeWidth: Int = 1,
    labelColor: Color = MaterialTheme.colorScheme.primary,
    iconRes: DrawableResource? = null,
    iconTint: Color = Color.Unspecified,
    iconSize: Int = 24,
    labelSize: Int = 16,
    iconHorizontalOffset: Int = 0,
    shape: RoundedCornerShape = RoundedCornerShape(50),
    fontWeight: FontWeight = FontWeight.Medium,
    contentPadding: PaddingValues = PaddingValues(vertical = 8.responsiveDp, horizontal = 12.responsiveDp),
) {
    OutlinedButton(
        onClick = onClick,
        shape = shape,
        contentPadding = contentPadding,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
        border = BorderStroke(strokeWidth.responsiveDp, strokeColor)
    ) {
        Text(
            text = label,
            color = labelColor,
            fontSize = labelSize.responsiveSp,
            fontWeight = fontWeight
        )
        iconRes?.let {
            HorizontalSpacer(width = 8.responsiveDp)
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier
                    .size(iconSize.responsiveDp)
                    .weight(0.1F)
                    .offset(x = iconHorizontalOffset.responsiveDp)
            )
        }
    }
}