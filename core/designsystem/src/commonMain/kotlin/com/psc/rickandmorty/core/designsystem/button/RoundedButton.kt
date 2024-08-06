package com.psc.rickandmorty.core.designsystem.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
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

private const val ALPHA_DISABLED_CONTAINER = 0.6F
private const val ALPHA_DISABLED_CONTENT = 0.6F
private const val ICON_PADDING = 12

@Composable
fun RoundedButtonWithIcon(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconRes: DrawableResource? = null,
    iconSize: Int = 24,
    labelSize: Int = 16,
    iconHorizontalOffset: Int = 0,
    fontWeight: FontWeight = FontWeight.Medium,
    shape: RoundedCornerShape = RoundedCornerShape(50),
    labelUppercase: Boolean = false,
    backgroundColor: Color = colorScheme.primary,
    contentColor: Color = colorScheme.onPrimary,
    contentPadding: PaddingValues = PaddingValues(
        vertical = 8.responsiveDp,
        horizontal = 12.responsiveDp
    ),
    iconAlignment: IconAlignment = IconAlignment.END,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = shape,
        contentPadding = contentPadding,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = colorScheme.surface.copy(alpha = ALPHA_DISABLED_CONTAINER),
            disabledContentColor = colorScheme.onSurface.copy(alpha = ALPHA_DISABLED_CONTENT)
        )
    ) {
        if(iconAlignment == IconAlignment.START) {
            iconRes?.let {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier
                        .size(iconSize.responsiveDp)
                        .offset(x = iconHorizontalOffset.responsiveDp)
                )
                HorizontalSpacer(width = ICON_PADDING.responsiveDp)
            }
        }
        Text(
            text = if (labelUppercase) label.uppercase() else label,
            fontSize = labelSize.responsiveSp,
            fontWeight = fontWeight
        )
        if(iconAlignment == IconAlignment.END) {
            iconRes?.let {
                HorizontalSpacer(width = ICON_PADDING.responsiveDp)
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier
                        .size(iconSize.responsiveDp)
                        .offset(x = iconHorizontalOffset.responsiveDp)
                )
            }
        }
    }
}

@Composable
fun RoundedButtonWithImage(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageRes: DrawableResource? = null,
    iconSize: Int = 24,
    labelSize: Int = 16,
    iconHorizontalOffset: Int = 0,
    fontWeight: FontWeight = FontWeight.Medium,
    shape: RoundedCornerShape = RoundedCornerShape(50),
    labelUppercase: Boolean = false,
    backgroundColor: Color = colorScheme.primary,
    contentColor: Color = colorScheme.onPrimary,
    contentPadding: PaddingValues = PaddingValues(
        vertical = 8.responsiveDp,
        horizontal = 12.responsiveDp
    ),
    imageAlignment: IconAlignment = IconAlignment.END,
    enabled: Boolean = true,
    ) {
    Button(
        onClick = onClick,
        shape = shape,
        contentPadding = contentPadding,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = colorScheme.surface.copy(alpha = ALPHA_DISABLED_CONTAINER),
            disabledContentColor = colorScheme.onSurface.copy(alpha = ALPHA_DISABLED_CONTENT)
        )
    ) {
        if(imageAlignment == IconAlignment.START) {
            imageRes?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSize.responsiveDp)
                        .offset(x = iconHorizontalOffset.responsiveDp)
                )
                HorizontalSpacer(width = ICON_PADDING.responsiveDp)
            }
        }
        Text(
            text = if (labelUppercase) label.uppercase() else label,
            fontSize = labelSize.responsiveSp,
            fontWeight = fontWeight
        )
        if(imageAlignment == IconAlignment.END) {
            imageRes?.let {
                HorizontalSpacer(width = ICON_PADDING.responsiveDp)
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSize.responsiveDp)
                        .offset(x = iconHorizontalOffset.responsiveDp)
                )
            }
        }
    }
}