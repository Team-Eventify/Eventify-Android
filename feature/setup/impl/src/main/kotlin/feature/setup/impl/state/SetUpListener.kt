package feature.setup.impl.state

interface SetUpListener {
    fun selectCategory(categoryId: String, selected: Boolean)
    fun onChangeFirstName(value: String)
    fun onChangeLastName(value: String)
    fun flowNext()
    fun flowBack()
}