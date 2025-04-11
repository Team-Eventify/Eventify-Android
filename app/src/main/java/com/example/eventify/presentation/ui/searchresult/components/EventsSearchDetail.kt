package com.example.eventify.presentation.ui.searchresult.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.eventify.presentation.ui.events.eventsfeed.components.EventCard
import com.example.eventify.presentation.ui.searchresult.state.SearchData
import com.example.eventify.presentation.ui.searchresult.state.SearchDetailListener
import com.example.eventify.presentation.ui.searchresult.state.SearchDetailUiState
import com.example.eventify.presentation.ui.theme.space16
import com.example.eventify.presentation.ui.theme.space18
import com.example.eventify.presentation.ui.theme.space6

@Composable
fun EventsSearchDetail(
    state: SearchDetailUiState.ShowEvents,
    actions: SearchDetailListener,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = space16)
    ) {

        state.searchData?.let {
            SearchDataDescription(
                data = it,
            )
        }

        val eventsAmount = buildAnnotatedString {
            append("Найдено событий: ")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append(state.size.toString())
            }
        }

        Text(
            text = eventsAmount,
            modifier = Modifier
                .padding(vertical = space6)
        )


        LazyColumn {
            items(
                key = { it.id },
                items = state.items
            ) { event ->
                EventCard(
                    event = event,
                    onClick = actions::onEventClick
                )
                Spacer(Modifier.height(space18))
            }
        }
    }
}


@Composable
private fun SearchDataDescription(
    data: SearchData,
    modifier: Modifier = Modifier
) {

    data.category?.let { category ->
        val categoryFilterDescription = buildAnnotatedString {
            append("Все события категории ")
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append(category.title)
            }
        }
        Text(
            text = categoryFilterDescription,
            modifier = Modifier
                .padding(vertical = space6)
                .then(modifier)
        )
    }
}