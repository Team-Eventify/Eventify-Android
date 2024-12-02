package com.example.eventify.domain.usecases.categories

import com.example.eventify.data.repositories.category.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesApiRepository: CategoryRepository
) {
    suspend operator fun invoke() = categoriesApiRepository.getCategoriesList()
}