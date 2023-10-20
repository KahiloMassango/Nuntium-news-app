package com.example.nuntium.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.nuntium.R

val SfProFontFamily = FontFamily(
    Font(R.font.sf_pro_regular),
    Font(R.font.sf_pro_bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = SfProFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = SfProFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = SfProFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = SfProFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    )

)