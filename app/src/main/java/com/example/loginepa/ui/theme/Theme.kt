package com.example.compose
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.loginepa.ui.theme.backgroundDark
import com.example.loginepa.ui.theme.backgroundLight
import com.example.loginepa.ui.theme.errorContainerDark
import com.example.loginepa.ui.theme.errorContainerLight
import com.example.loginepa.ui.theme.errorDark
import com.example.loginepa.ui.theme.errorLight
import com.example.loginepa.ui.theme.inverseOnSurfaceDark
import com.example.loginepa.ui.theme.inverseOnSurfaceLight
import com.example.loginepa.ui.theme.inversePrimaryDark
import com.example.loginepa.ui.theme.inversePrimaryLight
import com.example.loginepa.ui.theme.inverseSurfaceDark
import com.example.loginepa.ui.theme.inverseSurfaceLight
import com.example.loginepa.ui.theme.onBackgroundDark
import com.example.loginepa.ui.theme.onBackgroundLight
import com.example.loginepa.ui.theme.onErrorContainerDark
import com.example.loginepa.ui.theme.onErrorContainerLight
import com.example.loginepa.ui.theme.onErrorDark
import com.example.loginepa.ui.theme.onErrorLight
import com.example.loginepa.ui.theme.onPrimaryContainerDark
import com.example.loginepa.ui.theme.onPrimaryContainerLight
import com.example.loginepa.ui.theme.onPrimaryDark
import com.example.loginepa.ui.theme.onPrimaryLight
import com.example.loginepa.ui.theme.onSecondaryContainerDark
import com.example.loginepa.ui.theme.onSecondaryContainerLight
import com.example.loginepa.ui.theme.onSecondaryDark
import com.example.loginepa.ui.theme.onSecondaryLight
import com.example.loginepa.ui.theme.onSurfaceDark
import com.example.loginepa.ui.theme.onSurfaceLight
import com.example.loginepa.ui.theme.onSurfaceVariantDark
import com.example.loginepa.ui.theme.onSurfaceVariantLight
import com.example.loginepa.ui.theme.onTertiaryContainerDark
import com.example.loginepa.ui.theme.onTertiaryContainerLight
import com.example.loginepa.ui.theme.onTertiaryDark
import com.example.loginepa.ui.theme.onTertiaryLight
import com.example.loginepa.ui.theme.outlineDark
import com.example.loginepa.ui.theme.outlineLight
import com.example.loginepa.ui.theme.outlineVariantDark
import com.example.loginepa.ui.theme.outlineVariantLight
import com.example.loginepa.ui.theme.primaryContainerDark
import com.example.loginepa.ui.theme.primaryContainerLight
import com.example.loginepa.ui.theme.primaryDark
import com.example.loginepa.ui.theme.primaryLight
import com.example.loginepa.ui.theme.scrimDark
import com.example.loginepa.ui.theme.scrimLight
import com.example.loginepa.ui.theme.secondaryContainerDark
import com.example.loginepa.ui.theme.secondaryContainerLight
import com.example.loginepa.ui.theme.secondaryDark
import com.example.loginepa.ui.theme.secondaryLight
import com.example.loginepa.ui.theme.surfaceBrightDark
import com.example.loginepa.ui.theme.surfaceBrightLight
import com.example.loginepa.ui.theme.surfaceContainerDark
import com.example.loginepa.ui.theme.surfaceContainerHighDark
import com.example.loginepa.ui.theme.surfaceContainerHighLight
import com.example.loginepa.ui.theme.surfaceContainerHighestDark
import com.example.loginepa.ui.theme.surfaceContainerHighestLight
import com.example.loginepa.ui.theme.surfaceContainerLight
import com.example.loginepa.ui.theme.surfaceContainerLowDark
import com.example.loginepa.ui.theme.surfaceContainerLowLight
import com.example.loginepa.ui.theme.surfaceContainerLowestDark
import com.example.loginepa.ui.theme.surfaceContainerLowestLight
import com.example.loginepa.ui.theme.surfaceDark
import com.example.loginepa.ui.theme.surfaceDimDark
import com.example.loginepa.ui.theme.surfaceDimLight
import com.example.loginepa.ui.theme.surfaceLight
import com.example.loginepa.ui.theme.surfaceVariantDark
import com.example.loginepa.ui.theme.surfaceVariantLight
import com.example.loginepa.ui.theme.tertiaryContainerDark
import com.example.loginepa.ui.theme.tertiaryContainerLight
import com.example.loginepa.ui.theme.tertiaryDark
import com.example.loginepa.ui.theme.tertiaryLight
import com.example.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)


@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun LoginAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
  val colorScheme = when {

      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

