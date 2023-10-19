package com.example.nuntium.ui.screens.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
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
import com.example.nuntium.ui.common.CustomClickableText
import com.example.nuntium.ui.common.CustomTextField
import com.example.nuntium.ui.theme.SfProFontFamily

@Composable
fun SignUpScreen() {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 28.dp, bottom = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                modifier = Modifier.padding(top = 0.dp),
                text = "Welcome Back \uD83D\uDC4B",
                fontFamily = SfProFontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = stringResource(R.string.welcome_message),
                fontFamily = SfProFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.height(32.dp))
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange = { /*TODO*/ },
                placeholder = stringResource(R.string.username),
                leadingIcon = Icons.Outlined.Person,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    imeAction = ImeAction.Next
                ),
                keyboardAction = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            CustomTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                value = "",
                onValueChange = { /*TODO*/ },
                placeholder = stringResource(R.string.email_placeholder),
                leadingIcon = Icons.Outlined.Email,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardAction = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            CustomTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                value = "",
                onValueChange = { /*TODO*/ },
                placeholder = stringResource(R.string.password_placeholder),
                leadingIcon = Icons.Outlined.Lock,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardAction = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )
            CustomTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                value = "",
                onValueChange = { /*TODO*/ },
                placeholder = stringResource(R.string.repeat_password),
                leadingIcon = Icons.Outlined.Lock,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
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
                text = stringResource(R.string.sign_up),
                onClick = { /*TODO*/ }
            )

        }

        CustomClickableText(
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxWidth(),
            firstText = stringResource(R.string.already_have_an_account),
            secondText = stringResource(R.string.sign_in),
            onCLick = { /*TODO*/ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    MaterialTheme {
        SignUpScreen()
    }
}