package com.example.eventify.presentation.ui.shared


import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventify.R
import com.example.eventify.presentation.ui.navgraphs.HomeRouter


data class BottomNavigationBarItem(
    val title: String,
    val icon: Painter,
    val route: String
)


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        BottomNavigationBarItem(
            title = "Главная",
            icon = painterResource(R.drawable.ic_house),
            route = HomeRouter.EventFeedRoute.route
        ),
        BottomNavigationBarItem(
            title = "Поиск",
            icon = painterResource(R.drawable.ic_magnifying_glass),
            route = ""
        ),
        BottomNavigationBarItem(
            title = "Мои ивенты",
            icon = painterResource(R.drawable.ic_bookmark),
            route = ""
        ),
        BottomNavigationBarItem(
            title = "Избранное",
            icon = painterResource(R.drawable.ic_heart),
            route = HomeRouter.FavoritesRoute.route
        ),
        BottomNavigationBarItem(
            title = "Профиль",
            icon = painterResource(R.drawable.ic_person),
            route = HomeRouter.ProfileRoute.route
        ),
    )
    NavigationBar {
        items.forEach { item ->
            AddItem(
                item = item,
                navController = navController,
                currentDestination = currentDestination
            )
        }


    }
}


@Composable
fun RowScope.AddItem(
    item: BottomNavigationBarItem,
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    NavigationBarItem(
        icon = {
            Icon(
                item.icon,
                contentDescription = item.route
            )
        },
        label = { Text(text = item.title, fontSize = 10.sp) },
        selected = currentDestination?.hierarchy?.any {
            it.route == item.route
        } == true,
        onClick = {
            if (item.route.isNotEmpty()){
                navController.navigate(item.route)
            }
        }
    )
}

@Preview(name = "BottomNavigationBar")
@Composable
private fun PreviewBottomNavigationBar() {
    BottomNavigationBar(navController = rememberNavController())
}