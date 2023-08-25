package com.flyview.pharmmobile.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flyview.core.theme.AppTheme
import com.flyview.pharmmobile.settings.toolbar.SettingsToolbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsUi(component: SettingsComponent) {

    val agent = component.agent.collectAsState()
    val firm = component.firm.collectAsState()
    val host = component.host.collectAsState()
    val stock = component.stock.collectAsState()

    Scaffold(
        topBar = { SettingsToolbarUi(component.toolbarComponent) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            OutlinedTextField(
                value = host.value,
                onValueChange = component::onHostChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                label = { Text(text = "Сервер") },
                supportingText = { Text(text = "Обазательное поле") },
                isError = host.value.isEmpty(),
                shape = CircleShape
            )

            OutlinedNumberTextField(
                value = firm.value,
                onValueChange = component::onFirmChange,
                label = "Фирма",
                supportingText = "Обязательное поле",
                isError = firm.value.isEmpty()
            )

            OutlinedNumberTextField(
                value = stock.value,
                onValueChange = component::onStockChange,
                label = "Склад",
                supportingText = "Обязательное поле",
                isError = stock.value.isEmpty()
            )

            OutlinedNumberTextField(
                value = agent.value,
                onValueChange = component::onAgentChange,
                label = "Агент",
                supportingText = "Обязательное поле",
                isError = agent.value.isEmpty()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    supportingText: String,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        label = { Text(text = label) },
        supportingText = { Text(text = supportingText) },
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = CircleShape
    )
}

@Composable
fun SettingsUiPreview(darkTheme: Boolean) {
    AppTheme(darkTheme = darkTheme) {
        SettingsUi(FakeSettingsComponent())
    }
}

@Preview(name = "light")
@Composable
fun SettingsUiPreviewLight() {
    SettingsUiPreview(darkTheme = false)
}

@Preview(name = "dark")
@Composable
fun SettingsUiPreviewDark() {
    SettingsUiPreview(darkTheme = true)
}