package com.example.eventify.presentation.ui.common.bottomBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.eventify.R
import com.example.eventify.presentation.navigation.entries.account.ProfilePath
import com.example.eventify.presentation.navigation.entries.events.EventFeedPath
import com.example.eventify.presentation.navigation.entries.events.MyEventsPath
import com.example.eventify.presentation.navigation.entries.events.SearchPath

@Composable
fun BottomNavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = remember {
        listOf(
            BottomBarItem(
                titleResId = R.string.home,
                iconResId = R.drawable.ic_house,
                route = EventFeedPath
            ),
            BottomBarItem(
                titleResId = R.string.search,
                iconResId = R.drawable.ic_magnifying_glass,
                route = SearchPath
            ),
            BottomBarItem(
                titleResId = R.string.my_events,
                iconResId = R.drawable.ic_bookmark,
                route = MyEventsPath
            ),
            BottomBarItem(
                titleResId = R.string.profile,
                iconResId = R.drawable.ic_person,
                route = ProfilePath
            ),
        )
    }
    val routes = items.map { it.route }

    AnimatedVisibility(
        visible = currentRoute in routes
    ) {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.surface,
            contentPadding = PaddingValues(0.dp),
            tonalElevation = 0.dp,
            modifier = Modifier
                .then(modifier)
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(item.iconResId),
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(item.titleResId),
                            fontSize = 12.sp
                        )
                    },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.surface,
                        disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                )
            }
        }
    }
}

@Preview(name = "BottomNavBar")
@Composable
private fun PreviewBottomNavBar() {
//    BottomNavBar()
}