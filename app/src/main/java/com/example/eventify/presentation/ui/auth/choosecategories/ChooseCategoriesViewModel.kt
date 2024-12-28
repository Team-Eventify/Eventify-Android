package com.example.eventify.presentation.ui.auth.choosecategories

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.SetUserCategoriesUseCase
import com.example.eventify.domain.usecases.categories.GetCategoriesUseCase
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ChooseCategoriesViewModel @Inject constructor(
    private val navigator: Navigator,
    private val setCategoriesUseCase: SetUserCategoriesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ChooseCategoriesState> =
        MutableStateFlow(ChooseCategoriesState())

    val stateFlow: StateFlow<ChooseCategoriesState> = _stateFlow.asStateFlow()

    fun loadData(){
        viewModelScope.launch {
            when (val categories = getCategoriesUseCase()){
                is Result.Error -> TODO()
                is Result.Success -> {
                    _stateFlow.update { currentState ->
                        currentState.copy(
                            categoryItems = categories.data.map {
                                CategorySelectItem(
                                    id = it.id,
                                    title = it.title
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    fun skipStep(){
        viewModelScope.launch {
            navigator.navigate(RootRouter.HomeRoute){
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }

    fun setCategories(){
        val selectedCategoryIds = stateFlow.value.categoryItems.filter { it.selected }.map { it.id }

        viewModelScope.launch {
            when (val result = setCategoriesUseCase(selectedCategoryIds)){
                is Result.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = result.error.asUiText().asString(context))
                    )
                }
                is Result.Success -> navigator.navigate(RootRouter.HomeRoute){
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        }
    }

    fun toggleCategorySelection(categoryId: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                categoryItems = currentState.categoryItems.map { category ->
                    if (category.id == categoryId) {
                        category.copy(selected = !category.selected)
                    } else {
                        category
                    }
                }
            )
        }
    }

}