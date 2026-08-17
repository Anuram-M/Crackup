package com.kumar.crackup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kumar.crackup.states.BottomItem
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.util.topShadow

@Composable
fun CustomBottomNavigation(
    onNavigate: (String) -> Unit
) {

    val clickedRoute = remember {
        mutableStateOf("home")
    }
    val screens = listOf(
        BottomItem.Home,
        BottomItem.CurrentAffairs,
        BottomItem.Progress,
        BottomItem.Profile,
//        BottomItem.About,
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .navigationBarsPadding()
        .topShadow()
        .padding(horizontal = 10.dp)
//        .height(80.dp)
        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            screens.forEach {
                NavItem(
                    it,
                    clickedRoute.value
                ) { route ->
                    clickedRoute.value = route
                    onNavigate(route)
                }
            }
        }
    }
}

@Composable
fun NavItem(
    item: BottomItem,
    clickedRoute: String,
    onNavigate: (String) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    if (item.route == clickedRoute) lightBlue else Color.White.copy(alpha = 0.8f),
                    shape = CircleShape
                )
                .clickable {
                    onNavigate(item.route)
                }, contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (item.route == clickedRoute) Color.White else textBlack
            )
        }
        BodyText(text = item.name,
            textSize = bodyFontTinySize,
            textColor = if (item.route == clickedRoute) lightBlue
            else textBlack)
    }
}