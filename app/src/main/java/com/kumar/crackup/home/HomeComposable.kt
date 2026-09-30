package com.kumar.crackup.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.dialogCardColor
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.screenBackground
import com.kumar.crackup.util.FirebaseUtil
import com.kumar.crackup.util.PrefConstants
import com.kumar.crackup.util.PreferenceUtil
import com.kumar.crackup.viewmodel.MyViewModel
import kotlinx.coroutines.launch

data class NavigationSection(
    val title: String,
    val pageIndex: Int // The target position index for the page inside the LazyColumn
)

@Composable
fun HomeComposable(navHostController: NavHostController, myViewModel: MyViewModel, targetSection: String? = null) {
    val user by myViewModel.currentUser.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    val pages = 3
    val pagerState = rememberPagerState(pageCount = { pages })

//    val scrollState = rememberScrollState()
//    LaunchedEffect(pagerState.currentPage) {
//        scrollState.scrollTo(0)
//    }
//
//    LaunchedEffect(Unit) {
//        coroutineScope.launch {
//            FirebaseUtil.exportQuestionsToLocalFile(context,
//                FirebaseFirestore.getInstance(),
//                subject = "aptitude",
//                subTopic = "simplification")
//        }
//    }

    //pages
    val navigationSections = remember {
        listOf(
            NavigationSection("TNPSC Syllabus", pageIndex = 0), // Index 1 because Index 0 is the optional intro text
            NavigationSection("Classes", pageIndex = 1),
            NavigationSection("Competitive Exams", pageIndex = 2),
        )
    }
    val lifecycleOwner = LocalLifecycleOwner.current



    val showContact = remember {
        mutableStateOf(PreferenceUtil.getBoolean(PrefConstants.SHOW_CONTACT))
    }

    val sections = remember {
        mapOf(
            "header" to 0,
            "stickyHeader" to 1,
        )
    }
    LaunchedEffect(targetSection) {
        Log.d("LITAG", "HomeComposable: target:  ${targetSection}")
        targetSection?.let { sectionKey ->
            val index = sections[sectionKey]
            Log.d("LITAG", "HomeComposable: ${index}")
            if(index != null) {
                Log.d("LITAG", "HomeComposable: scroll to: ${index}")
                listState.animateScrollToItem(index)
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(screenBackground)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {

            item(key = "header") {
                HeaderSection(
                    userName = user?.name.toString(),
                    streakDays = user?.currentStreak ?: 0,
                    onStartQuizClick = {
                       //need to add functionality
                    }
                )
            }

            stickyHeader(key = "stickyHeader") {
                Column(modifier = Modifier
                    .shadow(elevation = 4.dp)
                    .background(Color.White)) {
                    Text(
                        text = "Explore:",
                        style = TextStyle(
                            color = lightBlue,
                            fontSize = bodyFontLargeSize,
                            fontFamily = bodyFont,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 5.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White) // Prevents content below from showing through transparently
                            .horizontalScroll(rememberScrollState())
                            .padding(start = 16.dp, end = 16.dp, bottom = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        navigationSections.forEach { section ->
                            val isSelected = pagerState.currentPage == section.pageIndex
                            FilterChip(selected = isSelected,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(page = section.pageIndex)
                                    }
                                },
                                label = { Text(section.title, color = if(isSelected) lightBlue else Color.Gray) },
                                colors = FilterChipDefaults.filterChipColors(selectedContainerColor = dialogCardColor, containerColor = Color.Black.copy(alpha = 0.05f)),
                                border = FilterChipDefaults.filterChipBorder(selected = isSelected,
//                                    borderWidth = 1.dp,
                                    borderColor = Color.Transparent,
                                    selectedBorderWidth = 1.dp, selectedBorderColor =  lightBlue, enabled = true))
                        }
                    }
                }
            }

            item {
                HorizontalPager(
                    state = pagerState,

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 400.dp, max = 2000.dp),
                    verticalAlignment = Alignment.Top
                ) { page ->
                    when(page) {
                        0 -> SyllabusSection(navHostController, myViewModel)
                        1 -> VideoSection(lifecycleOwner)
                        2 -> ExamsInfoSection()
                    }

                }
            }
        }
        //needs to identify its usage later
//                if(showContact.value) {
//                    ContactUsDialog(onDismissRequest = {
//                        showContact.value = false
//                        PreferenceUtil.putBoolean(PrefConstants.SHOW_CONTACT, false)
//                    })
//                }
    }
}
