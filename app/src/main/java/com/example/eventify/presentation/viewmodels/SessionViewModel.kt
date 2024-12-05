package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.account.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {
    var isLoggedIn by mutableStateOf(false)
        private set

    fun checkLoggedIn() {
        runBlocking {
            isLoggedIn = try {
                getCurrentUserUseCase()
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