package com.example.eventify.presentation.navigation.entries

open class BaseDestination(
    private val route: String,
    vararg argumentsKeys: String
) {

    /**
     * Полный путь для composable функции с optional аргументами
     */
    val baseRoute: String = route + argumentsKeys.toStringArgs()

    /**
     * Добавление дочернего пути
     */
    fun updateAndGetPath(path: String): String {
        return "$route/$path"
    }

    /**
     * Метод для получения полного пути для навигации
     */
    fun toRoute(vararg arguments: Pair<String, Any?>): String {
        return route + arguments.generatePath()
    }
}

private fun Array<out String>.toStringArgs(): String {
    val arguments = StringBuilder()

    this.forEachIndexed { index, value ->
        if (index == 0) {
            arguments.append("?$value={$value}")
        } else {
            arguments.append("&$value={$value}")
        }
    }

    return arguments.toString()
}

private fun Array<out Pair<String, Any?>>.generatePath(): String {
    val arguments = StringBuilder()

    this.forEach { (key, value) ->
        if (value != null) {
            if (arguments.isBlank()) {
                arguments.append("?$key=$value")
            } else {
                arguments.append("&$key=$value")
            }
        }
    }

    return arguments.toString()
}