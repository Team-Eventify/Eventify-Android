package com.example.eventify.domain.usecases.events

import com.example.eventify.data.models.EventInfo
import com.example.eventify.data.repositories.users.UsersRepository
import javax.inject.Inject

class GetSubscribedEventsUseCase @Inject constructor(
   private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(userId: String): List<EventInfo> = usersRepository.getUserSubscribedEvents(userId = userId)
}