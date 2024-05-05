package com.final_year_project.kisaan10.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.final_year_project.kisaan10.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<NavigationItem>,
    currentRoute: String?,
    onClick: (NavigationItem) -> Unit,
) {
    NavigationBar(
        containerColor= Color.White
    ) {
        items.forEachIndexed { index, navigationItem ->
            NavigationBarItem(

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Black,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = Color.LightGray,
                ),
                selected = currentRoute == navigationItem.route,
                onClick = { onClick(navigationItem) },
                icon = {
                    BadgedBox(badge = {
                        if (navigationItem.badgeCount != null) {
                            Badge {
                                Text(text = navigationItem.badgeCount.toString(),
                                    style = androidx.compose.ui.text.TextStyle(
                                        fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                                        fontSize = 12.sp,
                                    )
                                )
                            }
                        } else if (navigationItem.hasBadgeDot) {
                            Badge()
                        }
                    }) {
                        Icon(
                            imageVector = if (currentRoute == navigationItem.route) {
                                navigationItem.selectedIcon
                            } else {
                                navigationItem.unSelectedIcon
                            }, contentDescription = navigationItem.title,
                            modifier = Modifier.width(26.dp).height(26.dp)
                        )
                    }
                }, label = {
                    Text(text = navigationItem.title,
                        style = androidx.compose.ui.text.TextStyle(
                            fontFamily = FontFamily(Font(R.font.roboto_medium, FontWeight.Medium)),
                            fontSize = 13.sp,
                        ))
                },
                modifier = Modifier.background(Color.White),
                alwaysShowLabel = false)
        }
    }
}
