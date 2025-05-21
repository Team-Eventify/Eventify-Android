package feature.searchResult.impl.components

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
import core.common.extentions.asDate
import core.common.extentions.asTime
import feature.searchResult.impl.state.SearchData
import feature.searchResult.impl.state.SearchDetailListener
import feature.searchResult.impl.state.SearchDetailUiState
import uikit.components.cards.EventCard
import uikit.space16
import uikit.space6
import uikit.space18

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
                    title = event.title,
                    description = event.description,
                    duration = event.duration,
                    location = event.location,
                    startTime = event.start.asDate(),
                    coverId = event.cover,
                ) {
                    actions.onEventClick(event.id)
                }
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