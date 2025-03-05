package com.example.eventify.presentation.ui.auth.onboarding.state

interface OnBoardingListener {
    fun nextPage()
    fun previousPage()
    fun goToPage(pageIndex: Int)
    fun finish()
}