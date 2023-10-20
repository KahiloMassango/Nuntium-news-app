package com.example.nuntium.ui.screens.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.R
import com.example.nuntium.ui.common.CustomButton
import com.example.nuntium.ui.common.CustomClickableText
import com.example.nuntium.ui.common.CustomSignInButton
import com.example.nuntium.ui.common.CustomTextField
import com.example.nuntium.ui.common.defaultPadding
import com.example.nuntium.ui.theme.SfProFontFamily

@Composable
fun SignInScreen() {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(defaultPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                modifier = Modifier.padding(top = 0.dp),
                text = "Welcome Back \uD83D\uDC4B",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = stringResource(R.string.welcome_message),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.height(32.dp))
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
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
                trailingIcon = Icons.Outlined.RemoveRedEye,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardAction = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )
            ClickableText(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 24.dp)
                    .fillMaxWidth(),
                text = AnnotatedString(stringResource(id = R.string.forgot_password_question)),
                onClick = { /*TODO*/ },
                style = TextStyle(
                    fontFamily = SfProFontFamily,
                    textAlign = TextAlign.Right,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp
                )
            )
            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.sign_in),
                onClick = { /*TODO*/ }
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 48.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.or),
                textAlign = TextAlign.Center
            )
            CustomSignInButton(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                text = "Sign In with Google",
                icon = R.drawable.google_logo,
                onClick = { /*TODO*/ }
            )
            CustomSignInButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign In with Facebook",
                icon = R.drawable.facebook_logo,
                onClick = { /*TODO*/ }
            )
        }

        CustomClickableText(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 10.dp)
                .fillMaxWidth(),
            firstText = stringResource(R.string.don_t_have_an_account),
            secondText = stringResource(R.string.sign_up),
            onCLick = { /*TODO*/ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    MaterialTheme {
        SignInScreen()
    }
}