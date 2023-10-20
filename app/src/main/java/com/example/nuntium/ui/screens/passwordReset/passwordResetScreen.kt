package com.example.nuntium.ui.screens.passwordReset

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.R
import com.example.nuntium.ui.common.CustomButton
import com.example.nuntium.ui.common.CustomTextField
import com.example.nuntium.ui.theme.NuntiumTheme
import com.example.nuntium.ui.theme.SfProFontFamily

@Composable
fun PasswordResetScreen() {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 28.dp)
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(R.string.forgot_password),
            fontFamily = SfProFontFamily,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = stringResource(R.string.reset_password_text),
            fontFamily = SfProFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
        CustomTextField(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            value = "",
            onValueChange = { /*TODO*/ },
            placeholder = stringResource(R.string.email_placeholder),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardAction = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
        CustomButton(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.done),
            onClick = { /*TODO*/ }
        )

    }
}


@Preview(showBackground = true)
@Composable
fun PasswordResetScreenPreview() {
    MaterialTheme {
        PasswordResetScreen()
    }
}