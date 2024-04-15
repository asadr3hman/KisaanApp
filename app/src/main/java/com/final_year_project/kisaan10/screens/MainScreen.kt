package com.final_year_project.kisaan10.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.final_year_project.kisaan10.navigation.BottomNavigationBar
import com.final_year_project.kisaan10.navigation.SetUpNavGraph
import com.final_year_project.kisaan10.utils.bottomNavigationItemsList


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }
//    val topBarTitle by remember(currentRoute) {
//        derivedStateOf {
//            if (currentRoute != null) {
//                bottomNavigationItemsList[bottomNavigationItemsList.indexOfFirst {
//                    it.route == currentRoute
//                }].title
//            } else {
//                bottomNavigationItemsList[0].title
//            }
//        }
//    }
    Scaffold(
//        topBar = {
//            TopAppBar(title = {
//                Text(text = topBarTitle)},
//                colors = TopAppBarDefaults.topAppBarColors(
//                    MaterialTheme.colorScheme.onBackground),
//
//                )
//        },
        bottomBar = {
            BottomNavigationBar(items = bottomNavigationItemsList, currentRoute = currentRoute ){ currentNavigationItem->
                navController.navigate(currentNavigationItem.route){
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let { startDestinationRoute ->
                        // Pop up to the start destination, clearing the back stack
                        popUpTo(startDestinationRoute) {
                            // Save the state of popped destinations
                            saveState = true
                        }
                    }

                    // Configure navigation to avoid multiple instances of the same destination
                    launchSingleTop = true

                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            }
        }
    ) {innerPadding->

        SetUpNavGraph(navController = navController, innerPadding = innerPadding,)


    }
}


