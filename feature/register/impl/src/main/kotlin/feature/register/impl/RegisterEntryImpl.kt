package feature.register.impl

import feature.register.api.RegisterEntry

class RegisterEntryImpl @Inject constructor() : RegisterEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        RegisterRoute(navController)
    }
}