package com.example.nuntium.ui.commonUi

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.R
import com.example.nuntium.ui.theme.SfProFontFamily

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = SfProFontFamily,
            fontWeight = FontWeight.W600,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
        )
    }
}
@Composable
fun CustomSignInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
   @DrawableRes icon: Int
) {
    OutlinedButton(
        modifier = modifier
            .height(58.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.surfaceVariant),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(42.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                fontFamily = SfProFontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun signInScreenPreview(){
    MaterialTheme {
        CustomSignInButton(
            text = "ffffff",
            icon = R.drawable.google_logo,
            onClick =  { }
        )
    }
}

