package com.example.nuntium.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.R
import com.example.nuntium.ui.common.CustomButton
import com.example.nuntium.ui.theme.SfProFontFamily

@Composable
fun OnBoardingScreen(
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .width(16.dp)
                        .height(288.dp),
                    painter = painterResource(id = R.drawable.right_image),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .width(288.dp)
                        .height(336.dp),
                    painter = painterResource(id = R.drawable.center_image),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .width(16.dp)
                        .height(288.dp),
                    painter = painterResource(id = R.drawable.left_image),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "First to know",
                fontFamily = SfProFontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .width(216.dp)
                    .padding(top = 10.dp),
                text = stringResource(R.string.first_to_know_into),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        CustomButton(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.next),
            onClick = onNext
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreePreview(){
    MaterialTheme {
        OnBoardingScreen(onNext = { })
    }
}
