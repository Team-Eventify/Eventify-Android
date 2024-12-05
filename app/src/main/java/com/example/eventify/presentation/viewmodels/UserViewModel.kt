package com.example.eventify.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.eventify.data.repositories.category.CategoryRepository
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.account.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val usersRepository: UsersRepository,
    private val categoriesRepository: CategoryRepository
): ViewModel() {


}