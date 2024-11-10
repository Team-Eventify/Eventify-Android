package com.example.eventify.presentation.ui.shared



import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventify.R
import com.example.eventify.presentation.ui.navgraphs.HomeRouter


data class BottomNavigationBarItem<T: HomeRouter>(
    val title: String,
    val icon: Painter,
    val route: T
)


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    val items = listOf(
        BottomNavigationBarItem(
            title = "Главная",
            icon = painterResource(R.drawable.ic_house),
            route = HomeRouter.EventFeed
        ),
        BottomNavigationBarItem(
            title = "Поиск",
            icon = painterResource(R.drawable.ic_magnifying_glass),
            route = HomeRouter.Search
        ),
        BottomNavigationBarItem(
            title = "Мои ивенты",
            icon = painterResource(R.drawable.ic_bookmark),
            route = HomeRouter.EventFeed
        ),
        BottomNavigationBarItem(
            title = "Профиль",
            icon = painterResource(R.drawable.ic_person),
            route = HomeRouter.Profile
        ),
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
//            NavigationBarItem(
//                selected = index == selectedItem,
//                icon = {
//                    Icon(
//                        painter = item.icon,
//                        contentDescription = "painter",
//                    )
//                },
//                label = { Text(text = item.title, fontSize = 12.sp) },
//                onClick = {
//                    selectedItem = index
//                    navController.navigate(item.route)
//                }
//
//            )
            AddItem(
                item = item,
                navController = navController,
                currentDestination = currentDestination
            )
        }


    }
}


@Composable
fun <T: HomeRouter> RowScope.AddItem(
    item: BottomNavigationBarItem<T>,
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    NavigationBarItem(
        icon = {
            Icon(
                painter = item.icon,
                contentDescription = "painter"
            )
        },
        label = { Text(text = item.title, fontSize = 12.sp) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            indicatorColor = MaterialTheme.colorScheme.surface
        ),
        selected = currentDestination?.hierarchy?.any {
            it.route?.endsWith(item.route.toString()) ?: false
        } == true,
        onClick = {
            navController.navigate(item.route)
        }
    )
}

@Preview(name = "BottomNavigationBar")
@Composable
private fun PreviewBottomNavigationBar() {
    BottomNavigationBar(navController = rememberNavController())
}