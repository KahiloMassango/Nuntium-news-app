package com.example.nuntium.ui.commonUi

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MicNone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.nuntium.R

@Composable
fun SearchContainer(
    modifier: Modifier = Modifier,
    searchText: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    onMicClick: () -> Unit
) {
    CustomTextField(
        modifier = modifier,
        value = searchText,
        onValueChange = { onValueChange(it) },
        onMicClick = onMicClick,
        placeholder = stringResource(R.string.search_placeholder),
        leadingIcon = Icons.Outlined.Search,
        trailingIcon = Icons.Outlined.MicNone,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardAction = KeyboardActions(
            onSearch = { onSearch() }
        )
    )


}