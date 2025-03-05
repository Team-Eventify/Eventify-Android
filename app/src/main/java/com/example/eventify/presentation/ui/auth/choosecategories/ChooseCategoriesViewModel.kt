package com.example.eventify.presentation.ui.auth.choosecategories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.account.SetUserCategoriesUseCase
import com.example.eventify.domain.usecases.categories.GetCategoriesUseCase
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.ui.auth.choosecategories.state.SetUpState
import com.example.eventify.presentation.ui.auth.choosecategories.state.SideEffect
import com.example.eventify.presentation.utils.asText
import com.example.eventify.presentation.utils.toColorOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ChooseCategoriesViewModel @Inject constructor(
    private val setCategoriesUseCase: SetUserCategoriesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<SetUpState> = MutableStateFlow(SetUpState())
    val stateFlow: StateFlow<SetUpState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            SetUpState()
        )

    fun loadData(){
        viewModelScope.launch {
            when (val result = getCategoriesUseCase()){
                is Result.Error -> {
                    mutableSideEffect.send(SideEffect.FailedProvideCategories(
                        message = result.asText(context)
                    ))
                }
                is Result.Success -> {
                    _stateFlow.update { currentState ->
                        currentState.copy(
                            categoryItems = result.data.map {
                                CategorySelectItem(
                                    id = it.id,
                                    title = it.title,
                                    color = it.color.toColorOrNull()!!
                                )
                            }
                        )
                    }
                }
            }
        }
    }


    fun setCategories(){
        val selectedCategoryIds = stateFlow.value.categoryItems.filter { it.selected }.map { it.id }

        viewModelScope.launch {
            when (val result = setCategoriesUseCase(selectedCategoryIds)){
                is Result.Error -> {
                    mutableSideEffect.send(SideEffect.FailedProvideCategories(
                        message = result.asText(context)
                    ))
                }
                is Result.Success -> {
                    mutableSideEffect.send(SideEffect.FinishSetUp)
                }
            }
        }
    }

    fun changeCategoryFilterActive(categoryId: String, value: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(
                categoryItems = currentState.categoryItems.map { category ->
                    if (category.id == categoryId) {
                        category.copy(selected = value)
                    } else {
                        category
                    }
                }
            )
        }
    }
}