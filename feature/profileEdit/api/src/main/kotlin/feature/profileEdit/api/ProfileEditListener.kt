package feature.profileEdit.api

interface ProfileEditListener {
    fun onSubmit(): Unit
    fun onChangeCategoryFilterActive(categoryId: String, value: Boolean): Unit
    fun onChangeEmail(email: String): Unit
    fun onChangeFirstName(firstName: String): Unit
    fun onChangeLastName(lastName: String): Unit
    fun onChangeTelegram(telegram: String): Unit
    fun onDeleteAccount(): Unit
    fun onBackClick(): Unit
}