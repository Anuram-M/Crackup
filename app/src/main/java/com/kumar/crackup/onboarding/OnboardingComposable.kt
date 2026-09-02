package com.kumar.crackup.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.util.PreferenceUtil
import com.kumar.crackup.viewmodel.MyViewModel

@Composable
fun OnboardingComposable(navHostController: NavHostController, myViewModel: MyViewModel) {

    val items = listOf("first", "second")
    val pagerState = rememberPagerState(pageCount = { items.size })
    Box(modifier = Modifier.fillMaxWidth()) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp),
        ) { page ->
            PageContent(items[page])
        }

        TextButton(modifier = Modifier.align(Alignment.TopEnd), onClick = {
            PreferenceUtil.putBoolean("isOnBoardingComplete", true)
            navHostController.navigate("login") {
                popUpTo(0)
            }
        }) {
            BodyText(text = "Skip", textColor = lightBlue, modifier = Modifier.padding(10.dp))
        }

        Row(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(items.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .size(if (isSelected) 10.dp else 6.dp) // Makes the active dot slightly larger
                        .background(
                            color = if (isSelected) Color(0xFF007AFF) else Color.LightGray,
                            shape = RoundedCornerShape(50)
                        )
                )
            }
        }
    }
}

@Composable
fun PageContent(pageValue: String) {
    if (pageValue.equals("first")) {
        FirstSlide()
    } else {
        SecondSlide()
    }
}