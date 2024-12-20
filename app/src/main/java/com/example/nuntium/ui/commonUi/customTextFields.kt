package com.example.nuntium.ui.commonUi

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.ui.theme.SfProFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardAction: KeyboardActions,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector? = null,
    onMicClick: () -> Unit = { }

) {

    OutlinedTextField(
        modifier = modifier
            .height(56.dp),
        value = value,
        onValueChange = { onValueChange(it) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardAction,
        shape = RoundedCornerShape(12.dp),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = SfProFontFamily,
            fontWeight = FontWeight.W500,

        ),
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = SfProFontFamily,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = if(value.isEmpty()) MaterialTheme.colorScheme.surfaceVariant else
                MaterialTheme.colorScheme.background,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        trailingIcon = {
            if (trailingIcon != null) {
                IconButton(
                    onClick = onMicClick
                ) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

fun AnnotatedString.Builder.appendLink(linkText: String, linkUrl: String) {
    pushStringAnnotation(tag = linkUrl, annotation = linkUrl)
    append(linkText)
    pop()
}

fun AnnotatedString.onLinkClick(offset: Int, onClick: () -> Unit) {
    getStringAnnotations(start = offset, end = offset).firstOrNull()?.let {
        onClick()
    }
}

@Composable
fun CustomText(
    text: String,
    link: String,
    onCLick: () -> Unit
) {
    val regexRule = "\\[.*?]".toRegex()
    val anotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = 16.sp,
            //lineHeight = 24.sp,
            fontFamily = SfProFontFamily,
            fontWeight = FontWeight(400),
        )) {
            append(text.replace(regexRule, ""))
        }


        //append("... ")

        withStyle(style = SpanStyle(
            fontSize = 16.sp,
            fontFamily = SfProFontFamily,
            fontWeight = FontWeight(400),
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        )) {
            appendLink("See more", link)
        }
    }

    ClickableText(
        text = anotatedString,
        onClick = { offset ->
            anotatedString.onLinkClick(
                offset = offset,
                onClick = onCLick
            )

        }
    )

}


@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview(){
    MaterialTheme {

    }
}