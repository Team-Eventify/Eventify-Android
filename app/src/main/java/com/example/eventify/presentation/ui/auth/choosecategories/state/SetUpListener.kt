package com.example.eventify.presentation.ui.auth.choosecategories.state

interface SetUpListener {
    fun selectCategory(categoryId: String, selected: Boolean)
    fun skip()
    fun nextStep()
}