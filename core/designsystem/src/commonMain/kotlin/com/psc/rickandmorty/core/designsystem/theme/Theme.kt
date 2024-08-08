package com.psc.rickandmorty.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
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

@Immutable
data class ExtendedColors(
    val success: Color,
    val onSuccess: Color,
    val warning: Color,
    val onWarning: Color
)

private val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        success = Color.Unspecified,
        onSuccess = Color.Unspecified,
        warning = Color.Unspecified,
        onWarning = Color.Unspecified
    )
}


@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(
        primary = Color(0xFF6F65DC),
        onPrimary = Color(0xFFFFFFFF),
        error = Color(0XFFFF0A0A),
        onError = Color(0xFFFFFFFF),
        background = Color(0xFFD6D3FD),
        onBackground = Color(0xFF141414)
    )

    val extendedColors = ExtendedColors(
        success = Colors.GREEN,
        onSuccess = Color.Black,
        warning = Colors.YELLOW,
        onWarning = Color.Black
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

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

object AppTheme {
    val extendedColors: ExtendedColors @Composable get() = LocalExtendedColors.current
    val colorScheme: ColorScheme @Composable get() = MaterialTheme.colorScheme
    val shapes: Shapes @Composable get() = MaterialTheme.shapes
    val typography: Typography @Composable get() = MaterialTheme.typography
}
