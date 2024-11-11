package com.example.eventify.presentation.ui.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
    ) {
        SearchBar(
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surface,
                inputFieldColors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Red,
                    focusedContainerColor = Color.Blue
                )
            ),
            placeholder = {
                Text(stringResource(R.string.search))
            },
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.ic_search), contentDescription = "search")
            },
            query = searchText,
            active = isActive,
            onActiveChange = {
                isActive = it
            },
            onQueryChange = {
                searchText = it
            },
            onSearch = {
                isActive = false
            },
            tonalElevation = 0.dp,
            modifier = modifier
                .fillMaxWidth()
        ){
            LazyColumn {
                items(5) {
                    Text(text = "Предложка запроса $it")
                }
            }
        }
    }

}

@Preview(name = "SearchScreen", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewSearchScreen() {
    SearchScreen()
}