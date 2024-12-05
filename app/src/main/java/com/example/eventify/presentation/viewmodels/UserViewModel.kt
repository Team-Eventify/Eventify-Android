package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.usecases.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.LogOutUseCase
import com.example.eventify.presentation.models.CategorySelectItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val usersRepository: UsersRepository,
    private val categoriesRepository: CategoryRepository
): ViewModel() {


}