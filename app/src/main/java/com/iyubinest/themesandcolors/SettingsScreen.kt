package com.iyubinest.themesandcolors

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    val context = LocalContext.current

    val viewModel = remember {
        ThemeViewModel(context.dataStore)
    }
    val value = viewModel.state.observeAsState().value
    val systemInDarkTheme = isSystemInDarkTheme()

    val darkModeChecked by remember(value) {
        val checked = when (value) {
            null -> systemInDarkTheme
            else -> value
        }
        mutableStateOf(checked)
    }
    val useDeviceModeChecked by remember(value) {
        val checked = when (value) {
            null -> true
            else -> false
        }
        mutableStateOf(checked)
    }

    LaunchedEffect(viewModel) {
        viewModel.request()
    }

    Box(
        modifier = Modifier
            .background(backgroundColor())
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Row {
                Caption(
                    text = "\uD83C\uDF19  Dark mode",
                    color = captionColor(),
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = darkModeChecked,
                    onCheckedChange = { viewModel.switchToUseDarkMode(it) })
            }
            Row {
                Caption(
                    text = "\uD83D\uDCF1  Use device settings",
                    color = captionColor(),
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = useDeviceModeChecked,
                    onCheckedChange = { viewModel.switchToUseSystemSettings(it) })
            }
        }
    }
}
