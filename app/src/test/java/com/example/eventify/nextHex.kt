package com.example.eventify

import kotlin.random.Random

private val rndHex: String get() = Random.nextInt(255).toString(16).uppercase()

fun Random.nextHex(): String {
    return "#FF$rndHex$rndHex$rndHex"
}