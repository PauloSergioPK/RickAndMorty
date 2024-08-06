package com.psc.rickandmorty.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.psc.rickandmorty.core.designsystem.resources.Res
import com.psc.rickandmorty.core.designsystem.resources.maven_pro_black
import com.psc.rickandmorty.core.designsystem.resources.maven_pro_bold
import com.psc.rickandmorty.core.designsystem.resources.maven_pro_extra_bold
import com.psc.rickandmorty.core.designsystem.resources.maven_pro_medium
import com.psc.rickandmorty.core.designsystem.resources.maven_pro_regular
import com.psc.rickandmorty.core.designsystem.resources.maven_pro_semi_bold
import com.psc.rickandmorty.core.designsystem.util.responsiveDp
import org.jetbrains.compose.resources.Font


@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(
        primary = Color(0xFF6F65DC),
        secondary = Color(0xFFE0FE10),
        tertiary = Color(0xFFC1D300),
        onPrimary = Color(0xFFFFFFFF),
        onSecondary = Color(0xFF181818),
        onTertiary = Color(0xFF181818),
        error = Color(0xFFB00020),
        onError = Color(0xFFFFFFFF),
        background = Color(0xFFD6D3FD),
        onBackground = Color(0xFF141414),
        surface = Color(0xFF24262F),
        onSurface = Color(0xFFFFFFFF),
        surfaceContainerLowest = Color(0XFF1F212F),
        surfaceContainerHighest = Color(0xFF52576D),
        surfaceContainerLow = Color(0xFF383B4B)
    )

    val fontFamily = FontFamily(
        listOf(
            Font(Res.font.maven_pro_black, FontWeight.Black),
            Font(Res.font.maven_pro_extra_bold, FontWeight.ExtraBold),
            Font(Res.font.maven_pro_bold, FontWeight.Bold),
            Font(Res.font.maven_pro_semi_bold, FontWeight.SemiBold),
            Font(Res.font.maven_pro_medium, FontWeight.Medium),
            Font(Res.font.maven_pro_regular, FontWeight.Normal),
        )
    )

    val defaultTypography = Typography()
    val typography = Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = fontFamily),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = fontFamily),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = fontFamily),

        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = fontFamily),

        titleLarge = defaultTypography.titleLarge.copy(fontFamily = fontFamily),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = fontFamily),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = fontFamily),

        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = fontFamily),

        labelLarge = defaultTypography.labelLarge.copy(fontFamily = fontFamily),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = fontFamily),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = fontFamily)
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.responsiveDp),
        medium = RoundedCornerShape(4.responsiveDp),
        large = RoundedCornerShape(0.responsiveDp)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
