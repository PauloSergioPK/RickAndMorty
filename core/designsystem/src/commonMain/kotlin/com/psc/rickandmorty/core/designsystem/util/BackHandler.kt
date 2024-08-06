package com.psc.rickandmorty.core.designsystem.util

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(action: () -> Unit)