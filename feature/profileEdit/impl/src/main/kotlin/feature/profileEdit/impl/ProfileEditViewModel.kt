package feature.profileEdit.impl

import androidx.lifecycle.viewModelScope
import core.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import data.models.UserChange
import domain.GetCategoriesWithUserSelection
import domain.account.ChangeUserUseCase
import domain.account.DeleteAccountUseCase
import domain.account.GetCurrentUserUseCase
import domain.account.SetUserCategoriesUseCase
import feature.profileEdit.impl.state.SideEffect
import feature.profileEdit.impl.state.UiState
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlin.collections.filter


@HiltViewModel
internal class ProfileEditViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCategoriesWithUserSelection: GetCategoriesWithUserSelection,
    private val changeUserUseCase: ChangeUserUseCase,
    private val setUserCategoriesUseCase: SetUserCategoriesUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
) : BaseViewModel() {

    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )


    private fun loadData() {
        launchCatching(
            catch = {
                _stateFlow.update { UiState.Error }
            }
        ) {
            val userData = getCurrentUserUseCase()
            val categories = getCategoriesWithUserSelection()

            _stateFlow.update {
                UiState.ShowProfileEdit(
                    firstName = userData.firstName,
                    lastName = userData.lastName,
                    email = userData.email,
                    telegramName = userData.telegramName,
                    categoryItems = categories,
                )
            }

        }
    }


    fun saveUser() {
        val validEmail = validateEmail() ?: return
        val validTelegram = validateTelegramName() ?: return

        val userData = (stateFlow.value as? UiState.ShowProfileEdit)?.let {
            UserChange(
                firstName = it.firstName,
                lastName = it.lastName,
                email = validEmail,
                telegramName = validTelegram
            )
        } ?: return

        val categoryIds = (stateFlow.value as? UiState.ShowProfileEdit)?.let {
            it.categoryItems.filter { categoryItem -> categoryItem.selected }.map { categoryItem -> categoryItem.id }
        } ?: return

        launchCatching(
            catch = {
                // TODO обработать ошибку
            }
        ) {
            changeUserUseCase(userData)
            setUserCategoriesUseCase(categoryIds = categoryIds)
            mutableSideEffect.send(SideEffect.SuccessUpdate)

        }
    }


    fun changeCategoryFilterActive(categoryId: String, value: Boolean) {
        _stateFlow.update { currentState ->
            (currentState as? UiState.ShowProfileEdit)?.copy(
                categoryItems = currentState.categoryItems.map { category ->
                    if (category.id == categoryId) {
                        category.copy(selected = value)
                    } else {
                        category
                    }
                }
            ) ?: currentState
        }
    }


    fun changeUserEmail(value: String) {
        _stateFlow.update { currentState ->
            (currentState as? UiState.ShowProfileEdit)?.copy(
                email = value
            ) ?: currentState
        }
    }


    private fun validateEmail(): String? {
        val currentState = (_stateFlow.value as? UiState.ShowProfileEdit) ?: return null

        return currentState.email

    }

        fun changeUserFirstName(value: String) {
            _stateFlow.update { currentState ->
                (currentState as? UiState.ShowProfileEdit)?.copy(
                    firstName = value
                ) ?: currentState
            }
        }

        fun changeUserLastName(value: String) {
            _stateFlow.update { currentState ->
                (currentState as? UiState.ShowProfileEdit)?.copy(
                    lastName = value
                ) ?: currentState
            }
        }

        fun changeUserTelegram(value: String) {
            _stateFlow.update { currentState ->
                (currentState as? UiState.ShowProfileEdit)?.copy(
                    telegramName = value
                ) ?: currentState
            }
        }

    private fun validateTelegramName(): String? {
        val currentState = (_stateFlow.value as? UiState.ShowProfileEdit) ?: return null

        return currentState.telegramName
    }

    fun deleteAccount() {
        launchCatching(
            catch = {
                mutableSideEffect.trySend(SideEffect.FailedDeleteAccount)
            }
        ) {
            deleteAccountUseCase()
            updateAuthStateToUnauthorized()
            mutableSideEffect.send(SideEffect.AccountDeleted)
        }
    }
}
