package com.example.eventify.presentation.ui.events.feedback


/**
 * UI State that represents FeedbackScreen
 **/
data class FeedbackState(
    val eventMark: Int? = null,
    val positiveFeedbackText: String = "",
    val isPositiveFeedbackTextError: Boolean = false,
    val negativeFeedbackText: String = "",
    val isNegativeFeedbackTextError: Boolean = false,
    val additionalFeedbackText: String = "",
    val isAdditionalFeedbackTextError: Boolean = false
)

/**
 * Feedback Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class FeedbackActions(
    val onChangePositiveFeedbackText: (String) -> Unit = {},
    val onChangeNegativeFeedbackText: (String) -> Unit = {},
    val onChangeAdditionalFeedbackText: (String) -> Unit = {},
    val onSendForm: () -> Unit = {}
)


