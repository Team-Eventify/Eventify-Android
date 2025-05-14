package domain.models

import data.models.Category
import data.models.EventDetail
import data.models.Organization

data class FullEventDetail(
    val eventInfo: EventDetail,
    val categories: List<Category>,
    val organization: Organization,
)
