package com.example.nuntium.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.R
import com.example.nuntium.ui.common.CustomButton
import com.example.nuntium.ui.theme.SfProFontFamily

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.hands_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(120.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_text),
            contentDescription = "logo text",
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .width(216.dp),
            text = "All news in one place, be the first to know last news",
            fontFamily = SfProFontFamily,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary

        )
        Spacer(modifier = Modifier.height(64.dp))
        CustomButton(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            text = "Get Started",
            onClick = onGetStarted
        )
    }
}


@Preview(showBackground = true)
@Composable
fun welcomeScreenPreview(){
    MaterialTheme {
        WelcomeScreen(onGetStarted = { })
    }
}