package com.kumar.crackup.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.states.BottomItem
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.util.topShadow

@Composable
fun AppBottomNavigation(
    onNavigate: (String) -> Unit
) {
    Log.d("COMPOSE", "AppBottomNavigation: recomposed")
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

    NavigationBar(modifier = Modifier
        .fillMaxWidth()
        .topShadow(), tonalElevation = 10.dp, containerColor = Color.White) {
        screens.forEach {
            NavigationBarItem(
                selected = it.route == clickedRoute.value,

                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = lightBlue,
                    selectedTextColor = lightBlue
                ),
                label = {
//                    if(it.route == clickedRoute.value)
                    Text(
                        text = it.name,
                        style = if (it.route == clickedRoute.value) TextStyle(
                            color = lightBlue,
                            fontSize = bodyFontTinySize,
//                            lineHeight = 0.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = bodyFont
                        ) else TextStyle(
                            color = Color.Gray,
                            fontSize = bodyFontTinySize,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = bodyFont
                        )
                    )
//                    else null
                },

                alwaysShowLabel = true,
                onClick = {
                    if(it.route != clickedRoute.value) {
                        clickedRoute.value = it.route
                        onNavigate(it.route)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(it.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(2.dp),
                        tint = if (it.route == clickedRoute.value) Color.White else Color.Gray
                    )
                })
        }
    }
}