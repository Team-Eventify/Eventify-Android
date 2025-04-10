package com.example.eventify.presentation.ui.events.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailEntry
import com.example.eventify.presentation.ui.events.search.components.EventsSearchBar
import com.example.eventify.presentation.ui.events.search.state.CategoryId
import com.example.eventify.presentation.ui.events.search.state.EventId
import com.example.eventify.presentation.ui.events.search.state.SearchListener
import com.example.eventify.presentation.ui.events.search.state.SearchMode

@Composable
fun SearchRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<SearchViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val features = LocalFeaturesProvider.current.features

    val listener = object : SearchListener {
        override fun changeSearchMode(mode: SearchMode) {
            viewModel.changeSearchMode(mode)
        }

        override fun onEventClick(eventId: EventId) {
            features.navigateToFeature<EventDetailEntry>(navController){
                path {
                    put(ARG_EVENT_ID, eventId)
                }
            }
        }

        override fun onCategoryClick(categoryId: CategoryId) {
            TODO("Not yet implemented")
        }

        override fun onChangeSearchQuery(value: String) {
            viewModel.changeSearchQuery(value)
        }

        override fun search() = Unit

        override fun cleanSearch() {
            viewModel.cleanSearchQuery()
        }

    }

    SearchScreen(
        state = uiState,
        listener = listener,
    )

    LaunchedEffect(Unit) {
        topBarState.hide()
    }
}

