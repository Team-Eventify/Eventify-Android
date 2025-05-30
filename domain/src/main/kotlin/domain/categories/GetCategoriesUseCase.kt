package domain.categories

import data.repositories.category.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesApiRepository: CategoryRepository
) {
    suspend operator fun invoke() = categoriesApiRepository.getCategoriesList()
}