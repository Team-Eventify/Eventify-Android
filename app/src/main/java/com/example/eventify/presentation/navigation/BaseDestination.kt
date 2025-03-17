package com.example.eventify.presentation.navigation




open class BaseDestination(
    val route: String,
    vararg argumentsKeys: String
) {

    val queryParams = argumentsKeys.toSet()
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
     * @return Actual route builder for current destination
     * */
    fun toRouteBuilder(): RouteBuilder {
        return RouteBuilder(this)
    }

    /**
     * @param childRoute new route path
     * @param argumentsKeys arguments for new child destination
     * @return new destination with given route and root path as current destination */
    fun child(childRoute: String, vararg argumentsKeys: String): BaseDestination {
        return BaseDestination(
            route = "$route/$childRoute",
            *argumentsKeys
        )
    }

    /**
     * Create and apply given builder block to the route
     * @param builder configuration builder block
     * */
    fun applyBuilder(builder: RouteBuilder.() -> Unit): String {
        return RouteBuilder(this)
            .apply(builder)
            .build()
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
