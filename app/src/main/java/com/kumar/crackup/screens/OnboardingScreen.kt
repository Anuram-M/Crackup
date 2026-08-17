package com.kumar.crackup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.templates.FeatureCarouselCard
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.OutlineContainer
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.templates.StatsCard
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.dialogCardColor
import com.kumar.crackup.ui.theme.heroFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.lightPink
import com.kumar.crackup.ui.theme.titanFont
import com.kumar.crackup.util.AppContentUtil
import com.kumar.crackup.util.AppContentUtil.stats
import com.kumar.crackup.util.PreferenceUtil
import com.kumar.crackup.viewmodel.MyViewModel
import kotlinx.coroutines.delay

@Composable
fun OnboardingScreen(navHostController: NavHostController, myViewModel: MyViewModel) {

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
             navHostController.navigate("login"){
                 popUpTo(0)
             }
        }) {
            BodyText(text = "Skip", textColor = lightBlue, modifier = Modifier.padding(10.dp))
        }

        Row(
            modifier = Modifier.padding(bottom = 30.dp).align(Alignment.BottomCenter),
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
    if(pageValue.equals("first")) {
        FirstSlide()
    } else {
        SecondSlide()

    }
}

@Composable
fun FirstSlide() {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(
                text = "Welcome to ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = bodyFontSize,
                    fontFamily = bodyFont,
                    fontWeight = FontWeight.Medium,
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 3.dp)
            )
            Text(
                text = "CrackUp",
                style = TextStyle(
                    color = lightBlue,
                    fontSize = heroFontLargeSize,
                    fontFamily = titanFont,
                    letterSpacing = 3.sp,
                    lineHeight = heroFontLargeSize,
                    fontWeight = FontWeight.Bold,
                ),
            )
            SmallHeightSpacer()
            Text(
                text = "where you can crack Government Exams with Confidence 🚀",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = bodyFontSize,
                    fontFamily = bodyFont,
                    fontWeight = FontWeight.Medium,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(24.dp))
        Text(
            text = "Why choose CrackUp?",
            style = TextStyle(
                color = lightBlue,
                fontSize = bodyFontLargeSize,
                fontFamily = bodyFont,
                fontWeight = FontWeight.Bold,
            ),
        )
        StatsCard(stats = stats,
            modifier = Modifier
                .fillMaxWidth()
                .padding( vertical = 10.dp)
        )
    }
}

@Composable
fun SecondSlide() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        HeaderText(text = "What you get from Us?", textSize = bodyFontLargeSize)
        SmallHeightSpacer()
        FlowRow(
            modifier = Modifier
                .padding(10.dp),
        ) {
            AppContentUtil.features.forEach {
                FeatureCarouselCard(it)
            }
        }
    }
}

@Preview
@Composable
fun PreviewSecondSlide() {
    BaseAppTheme {
        SecondSlide()
    }
}

@Preview
@Composable
fun PreviewFirstSlide() {
    BaseAppTheme {
        FirstSlide()
    }
}