package com.example.eventify.presentation.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


/**
 * This class provides text values
 * such as a string resource value or flat string
 */
sealed class UiText {

    /**
     * Inner class provides flat text value
     * @param value flat text string that will be show
     */
    data class DynamicString(val value: String) : UiText()

    /** Inner class provides string resource text value
     * @param id identification of string resource
     * @param args arguments of string resource which will be pulled into string.
     * By default is empty
     */
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : UiText()

    /**
     * Method to get text value inside a composable function.
     * Collect string resources by **LocalContext.current**.
     * Works for DynamicString and StringResource.
     */
    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> LocalContext.current.getString(id, *args)
        }
    }

    /**
     * Method to get text value from the other place. Required pass context explicitly.
     * Works for DynamicString and StringResource.
     * @param context Context of the Activity / App / composable.
     */
    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}