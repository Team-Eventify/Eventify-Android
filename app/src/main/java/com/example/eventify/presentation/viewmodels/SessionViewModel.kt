package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.usecases.CurrentUserUseCase
import com.example.eventify.domain.usecases.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val currentUserUseCase: CurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {
    var isLoggedIn by mutableStateOf(false)
        private set

    fun checkLoggedIn() {
        runBlocking {
            isLoggedIn = try {
                currentUserUseCase.getCurrentUser()
                true
            } catch (e: Exception){
                false
            }
        }
    }

    fun logOut(){
        logOutUseCase()
    }
}