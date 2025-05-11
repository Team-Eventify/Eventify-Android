package data.remote .models.category


data class CategoryInfoResponse(
    val id: String,
    val title: String,
    val color: String,
    val cover: String
){
    fun toCategoryInfo() = Category(
        id = id,
        title = title,
        color = color,
        cover = cover
    )
}