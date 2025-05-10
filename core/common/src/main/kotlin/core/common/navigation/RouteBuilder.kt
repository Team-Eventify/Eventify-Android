package core.common.navigation

@DslMarker
annotation class NavigationDsl



/**
* Used to generate path from given route destination.
* @param destination the schema of the target screen to generate path
* */
@NavigationDsl
class RouteBuilder(
    private val destination: BaseDestination,
) {
    private val pathParams = mutableMapOf<String, Any?>()
    private val queryParams = mutableMapOf<String, Any?>()

    /**
     * Include 'path' parameters to the target route by given lambda
     * @param block lambda to apply path params
     * */
    fun path(
        block: MutableMap<String, Any?>.() -> Unit
    ) {
        pathParams.apply(block)
    }

    /**
     * Include 'path' parameters to the target route by given list of pair
     * @param params list of path arguments
     * */
    fun path(
        vararg params: Pair<String, Any>
    ) {
        pathParams.putAll(params)
    }

    /**
     * Include 'query' parameters to the target route by given lambda
     * @param block lambda to apply query params
     * */
    fun query(
        block: MutableMap<String, Any?>.() -> Unit
    ) {
        queryParams.apply(block)
    }

    /**
     * Include 'query' parameters to the target route by given list of pair
     * @param params list of query arguments
     * */
    fun query(
        vararg params: Pair<String, Any>
    ) {
        queryParams.putAll(params)
    }

    /**
     * Build the target route path.
     * @throws IllegalArgumentException If unknown path or query parameters were passed */
    fun build(): String {
        var route = destination.route

        pathParams.forEach { (param, value) ->
            if (!route.contains("{$param}"))
                throw IllegalArgumentException("Destination '${destination.route}' has no path param '$param'")

            route = route.replace("{$param}", value.toString())
        }

        val arguments = StringBuilder()

        queryParams.forEach { (key, value) ->
            if (value != null) {
                if (!destination.queryParams.contains(key))
                    throw IllegalArgumentException("Destination '${destination.route}' has no query param '$key'")

                if (arguments.isBlank()) {
                    arguments.append("?$key=$value")
                } else {
                    arguments.append("&$key=$value")
                }
            }
        }

        return route + arguments.toString()
    }
}

