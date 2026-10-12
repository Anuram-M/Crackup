package com.kumar.crackup.progress

//import androidx.compose.material.icons.filled.LocalFireDepartment
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.wear.compose.foundation.pager.HorizontalPager
import androidx.wear.compose.foundation.pager.rememberPagerState
import com.google.firebase.firestore.model.DocumentSet.emptySet
import com.kumar.crackup.model.NewTopicProgress
import com.kumar.crackup.model.WeekStreak
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.HeightSpacer
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.templates.TinyHeightSpacer
import com.kumar.crackup.templates.WidthSpacer
import com.kumar.crackup.ui.theme.amber
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.cOrange
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.viewmodel.MyViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.collections.emptySet

fun formatStringToDisplay(value: Float) : String {
    return String.format(Locale.US, "%.1f", value)
}

@Composable
fun ProgressComposable(navHostController: NavHostController, myViewModel: MyViewModel) {
    /*Box(modifier = Modifier.fillMaxSize()) { }*/

//    ComingSoonView()

    val topicProgress by myViewModel.topicProgress.collectAsStateWithLifecycle()
    val globalProgress by myViewModel.globalProgress.collectAsStateWithLifecycle()
    val user by myViewModel.currentUser.collectAsStateWithLifecycle()
    val montlyActivity by myViewModel.monthlyActivity.collectAsStateWithLifecycle()
    Log.d("ERROR", "ProgressComposable: activedates:  ${user?.activeDates}")
    LaunchedEffect(Unit) {
        Log.d("ERROR", "ProgressComposable: ${topicProgress}")
//        myViewModel.getDailyActivity()
//        if(topicProgress.isEmpty()) {
//            myViewModel.getTopicProgress()
//            myViewModel.getGlobalProgress()
//        }
    }
    ProgressScreen(
        currentStreak = user?.currentStreak ?: 0,
        longestStreak = user?.longestStreak ?: 0,
        dailyStreak = user?.activeDates ?: emptyList(),
        monthlyStreak = montlyActivity,
        overallCompletion = globalProgress?.completedPercent ?: 0f,
        overallAccuracy = globalProgress?.accuracyPercent ?: 0f,
        topics = topicProgress,

        )
}

@Composable
fun ProgressScreen(
    currentStreak: Int,
    longestStreak: Int,
    dailyStreak: List<String>,
    monthlyStreak: List<MonthlyActivityModel>,
    overallCompletion: Float,
    overallAccuracy: Float,
    topics: List<NewTopicProgress>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {

        item {
            StreakCard(
                currentStreak = currentStreak,
                longestStreak = longestStreak,
                dailyStreak = dailyStreak,
                monthlyStreak = monthlyStreak
            )
        }

        item {
            OverallProgressCard(
                completion = overallCompletion,
                accuracy = overallAccuracy,
            )
        }

        item {
            BodyText(
                text = "Topics",
                textColor = textBlack,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        items(topics) { topic ->
            if(topic.parentName != "Overall Summary")
            TopicProgressItem(topic)
        }

        item { Spacer(Modifier.height(8.dp)) }
    }
}

private enum class StreakView { DAILY, WEEKLY }

@Composable
private fun StreakCard(
    currentStreak: Int,
    longestStreak: Int,
    dailyStreak: List<String>,
    monthlyStreak: List<MonthlyActivityModel>,
) {
    var view by remember { mutableStateOf(StreakView.DAILY) }

    var isMonthView by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color(0xFFF2A65A),
                    modifier = Modifier.size(28.dp),
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    BodyText(
                        text = "$currentStreak day streak",
                        textSize = bodyFontSmallSize,
                        fontWeight = FontWeight.Bold,
                        textColor = textBlack
                    )
                    BodyText(
                        text ="Best: $longestStreak days",
                        textSize = bodyFontSmallSize,
                        textColor = textBlack.copy(alpha = 0.8f),
                    )
                }
                Spacer(Modifier.weight(1f))
                StreakViewToggle(view = view, onChange = {
                    view = it
                    isMonthView = !isMonthView
                })
            }
            HeightSpacer()
            AnimatedVisibility(visible = !isMonthView) {
                DailyStreakRow(dailyStreak)
            }
            AnimatedVisibility(visible = isMonthView) {
                MonthlyActivityPagerSection(monthlyStreak)
            }

//            when (view) {
//                StreakView.DAILY -> DailyStreakRow(dailyStreak)
//                StreakView.WEEKLY -> WeeklyStreakRow(weeklyStreak)
//            }
        }
    }
}

@Composable
private fun StreakViewToggle(view: StreakView, onChange: (StreakView) -> Unit) {
    val options = listOf(StreakView.DAILY to "Days", StreakView.WEEKLY to "Monthly")
    Row(
        Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(2.dp),
    ) {
        options.forEach { (value, label) ->
            val selected = value == view
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(if (selected) lightBlue else Color.Transparent)
                    .clickable { onChange(value) }
                    .padding(horizontal = 12.dp, vertical = 6.dp),
            ) {
                BodyText(
                    text = label,
                    textSize = bodyFontSmallSize,
                    fontWeight = FontWeight.Medium,
                    textColor = if (selected) Color.White
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun DailyStreakRow(days: List<String>) {
    val activeDays = remember(days) { days.toSet() }
    val calendarDays = remember {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dayFormat = SimpleDateFormat("EEE", Locale.US)
        val dateFormat = SimpleDateFormat("dd", Locale.US)

        val cal = Calendar.getInstance()
        (0 until 7).map { offset->
            val date = cal.time
            val dateStr = sdf.format(date)
            val dayStr = dayFormat.format(date)
            val dateNumberStr = dateFormat.format(date)
            cal.add(Calendar.DAY_OF_YEAR, -1)

            Triple(dateStr, dayStr, dateNumberStr)

        }.reversed()
    }
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        calendarDays.forEach { day ->
            val isActive = activeDays.contains(day.first)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isActive) amber
                            else Color.Gray
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Completed",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp),
                    )
                }
                SmallHeightSpacer()
                BodyText(
                    text = day.second,
                    textColor = textBlack.copy(alpha = 0.5f),
                    textSize = bodyFontTinySize
                )
                BodyText(
                    text = day.third,
                    textColor = textBlack.copy(alpha = 0.5f),
                    textSize = bodyFontTinySize
                )
            }
        }
    }
}

@Composable
fun MonthlyActivityPagerSection(
    monthlyActivityList: List<MonthlyActivityModel>
) {
    val coroutineScope = rememberCoroutineScope()
//    val todayCal = remember { Calendar.getInstance() }
//
//    // 1. Convert the raw list into a fast lookup map: "YYYY-MM" -> Set("YYYY-MM-DD")
//    val activityMap = remember(monthlyActivityList) {
//        monthlyActivityList.associate { model ->
//            // Assuming your model has `yearMonth` ("2026-09") and `activeDates` (List<String>)
//            model.month to model.activeDates.toSet()
//        }
//    }
//
//    // 2. Determine the earliest "YYYY-MM" string from your fetched data
//    val earliestYearMonthStr = remember(monthlyActivityList) {
//        monthlyActivityList.map { it.month }.minOrNull()
//            ?: SimpleDateFormat("MMM,yyyy", Locale.US).format(todayCal.time)
//    }
//
//    // 3. Create a Calendar object representing the 1st day of the earliest month
//    val startCal = remember(earliestYearMonthStr) {
//        Calendar.getInstance().apply {
//            try {
//                val sdf = SimpleDateFormat("MMM,yyyy", Locale.US)
//                time = sdf.parse(earliestYearMonthStr) ?: Date()
//            } catch (e: Exception) { }
//            set(Calendar.DAY_OF_MONTH, 1)
//        }
//    }
//
//    // 4. Calculate total number of months (pages) between start month and current month
//    val totalPages = remember(startCal, todayCal) {
//        val yearsDiff = todayCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)
//        val monthsDiff = todayCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH)
//        (yearsDiff * 12 + monthsDiff + 1).coerceAtLeast(1)
//    }
//
//    // 5. Initialize PagerState anchored to the CURRENT MONTH (last page)
//    val pagerState = rememberPagerState(
//        initialPage = (totalPages - 1).coerceAtLeast(0),
//        pageCount = { totalPages }
//    )
//
//    Column(modifier = Modifier.fillMaxWidth()) {
//
//        // Month Navigation Header
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = {
//                    if (pagerState.currentPage > 0) {
//                        coroutineScope.launch {
//                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
//                        }
//                    }
//                },
//                enabled = pagerState.currentPage > 0
//            ) {
//                BodyText("<")
////                Icon(Icons.Default.ChevronLeft, contentDescription = "Previous Month")
//            }
//
//            // Compute Month & Year title dynamically for the active pager page
//            val currentDisplayedCal = remember(pagerState.currentPage, startCal) {
//                (startCal.clone() as Calendar).apply {
//                    add(Calendar.MONTH, pagerState.currentPage)
//                }
//            }
//
//            Text(
//                text = SimpleDateFormat("MMMM yyyy", Locale.US).format(currentDisplayedCal.time),
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp
//            )
//
//            IconButton(
//                onClick = {
//                    if (pagerState.currentPage < totalPages - 1) {
//                        coroutineScope.launch {
//                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                        }
//                    }
//                },
//                enabled = pagerState.currentPage < totalPages - 1
//            ) {
//                BodyText(">")
////                Icon(Icons.Default.ArrowBack, contentDescription = "Next Month")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Fixed-Height Pager View
//        HorizontalPager(
//            state = pagerState,
//            key = { page -> page }, // Prevents page state leakage when swiping
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(250.dp)
//        ) { page ->
//            // Compute current page's Calendar date
//            val pageCal = remember(page, startCal) {
//                (startCal.clone() as Calendar).apply {
//                    add(Calendar.MONTH, page)
//                }
//            }
//
//            val yearMonthKey = SimpleDateFormat("yyyy-MM", Locale.US).format(pageCal.time)
//
//            // Look up active dates for this month (returns empty set if inactive/missing)
//            val activeDatesForThisMonth = activityMap[yearMonthKey] ?: emptySet<String>()
//
//            // Render fixed 6-row calendar grid for this page
//            FixedMonthGrid(
//                year = pageCal.get(Calendar.YEAR),
//                month = pageCal.get(Calendar.MONTH),
//                activeDates = activeDatesForThisMonth
//            )
//        }
//    }


//    val todayCal = remember { Calendar.getInstance() }
//
//    // Reusable formatter matching your Firestore model date format ("Sep,2026")
//    val monthDocFormatter = remember { SimpleDateFormat("MMM,yyyy", Locale.US) }
//
//    // 1. Build lookup map using exact format from your model/Firestore
//    val activityMap = remember(monthlyActivityList) {
//        monthlyActivityList.associate { model ->
//            // Trim to prevent trailing spaces causing key misses ("Sep,2026 ")
//            model.month.trim() to model.activeDates.toSet()
//        }
//    }
//
//    // 2. Earliest month calculation (parse MMM,yyyy dates reliably)
//    val startCal = remember(monthlyActivityList) {
//        val earliestModel = monthlyActivityList.minByOrNull { model ->
//            try {
//                monthDocFormatter.parse(model.month.trim())?.time ?: Long.MAX_VALUE
//            } catch (e: Exception) {
//                Long.MAX_VALUE
//            }
//        }
//
//        Calendar.getInstance().apply {
//            if (earliestModel != null) {
//                try {
//                    time = monthDocFormatter.parse(earliestModel.month.trim()) ?: Date()
//                } catch (e: Exception) { }
//            }
//            set(Calendar.DAY_OF_MONTH, 1)
//        }
//    }
//
//    // 3. Calculate total pages
//    val totalPages = remember(startCal, todayCal) {
//        val yearsDiff = todayCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)
//        val monthsDiff = todayCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH)
//        (yearsDiff * 12 + monthsDiff + 1).coerceAtLeast(1)
//    }
//
//    val pagerState = rememberPagerState(
//        initialPage = (totalPages - 1).coerceAtLeast(0),
//        pageCount = { totalPages }
//    )
//
//    HorizontalPager(
//        state = pagerState,
//        key = { page -> page },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(250.dp)
//    ) { page ->
//        val pageCal = remember(page, startCal) {
//            (startCal.clone() as Calendar).apply {
//                add(Calendar.MONTH, page)
//            }
//        }
//
//        // Format page date to match "MMM,yyyy" (e.g., "Sep,2026")
//        val yearMonthKey = monthDocFormatter.format(pageCal.time)
//
//        // Lookup active dates using the matched "MMM,yyyy" key
//        val activeDatesForThisMonth = remember(activityMap, yearMonthKey) {
//            activityMap[yearMonthKey] ?: emptySet()
//        }
//
//        FixedMonthGrid(
//            year = pageCal.get(Calendar.YEAR),
//            month = pageCal.get(Calendar.MONTH),
//            activeDates = activeDatesForThisMonth
//        )
//    }
    val todayCal = remember { Calendar.getInstance() }

    // Formatter matching "Sep, 2026" (MMM, yyyy with space)
    val docIdFormatter = remember { SimpleDateFormat("MMM,yyyy", Locale.US) }

    // 1. Build lookup map keyed by normalized doc ID
    val activityMap = remember(monthlyActivityList) {
        monthlyActivityList.associate { model ->
            // Replaces multiple spaces/trimming to ensure exact key match
            model.month.trim() to model.activeDates.toSet()
        }
    }

    // 2. Compute start month Calendar by parsing the earliest doc ID
    val startCal = remember(monthlyActivityList) {
        val earliestModel = monthlyActivityList.minByOrNull { model ->
            try {
                docIdFormatter.parse(model.month.trim())?.time ?: Long.MAX_VALUE
            } catch (e: Exception) {
                Long.MAX_VALUE
            }
        }

        Calendar.getInstance().apply {
            if (earliestModel != null) {
                try {
                    time = docIdFormatter.parse(earliestModel.month.trim()) ?: Date()
                } catch (e: Exception) { }
            }
            set(Calendar.DAY_OF_MONTH, 1)
        }
    }

    // 3. Compute total page count
    val totalPages = remember(startCal, todayCal) {
        val yearsDiff = todayCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)
        val monthsDiff = todayCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH)
        (yearsDiff * 12 + monthsDiff + 1).coerceAtLeast(1)
    }

    val pagerState = rememberPagerState(
        initialPage = (totalPages - 1).coerceAtLeast(0),
        pageCount = { totalPages }
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
                Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                enabled = pagerState.currentPage > 0
            ) {
                BodyText("<")
//                Icon(Icons.Default.ChevronLeft, contentDescription = "Previous Month")
            }

            // Compute Month & Year title dynamically for the active pager page
            val currentDisplayedCal = remember(pagerState.currentPage, startCal) {
                (startCal.clone() as Calendar).apply {
                    add(Calendar.MONTH, pagerState.currentPage)
                }
            }

            Text(
                text = SimpleDateFormat("MMMM yyyy", Locale.US).format(currentDisplayedCal.time),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            IconButton(
                onClick = {
                    if (pagerState.currentPage < totalPages - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                enabled = pagerState.currentPage < totalPages - 1
            ) {
                BodyText(">")
//                Icon(Icons.Default.ArrowBack, contentDescription = "Next Month")
            }
        }

        HorizontalPager(
            state = pagerState,
            key = { page -> page },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->
            val pageCal = remember(page, startCal) {
                (startCal.clone() as Calendar).apply {
                    add(Calendar.MONTH, page)
                }
            }

            // Generates "Sep, 2026" for September 2026
            val targetDocId = docIdFormatter.format(pageCal.time)

            // Read active dates for this specific page key
            val activeDatesForThisMonth = remember(activityMap, targetDocId) {
                activityMap[targetDocId] ?: emptySet()
            }

            FixedMonthGrid(
                year = pageCal.get(Calendar.YEAR),
                month = pageCal.get(Calendar.MONTH),
                activeDates = activeDatesForThisMonth
            )
        }
    }

}
// --- FIXED 6-ROW MONTH GRID ---
@Composable
private fun FixedMonthGrid(
    year: Int,
    month: Int,
    activeDates: Set<String>
) {
    // Generate exactly 42 slots (6 weeks * 7 days) to ensure strict constant height
    val monthSlots = remember(year, month) {
        val cal = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, 1)
        }

        val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1 // 0-indexed for Sun
        val maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        val slots = mutableListOf<Date?>()

        repeat(firstDayOfWeek) { slots.add(null) }

        for (day in 1..maxDays) {
            cal.set(Calendar.DAY_OF_MONTH, day)
            slots.add(cal.time)
        }

        // Fill remaining spaces up to 42 so all months render identical heights
        while (slots.size < 42) {
            slots.add(null)
        }

        slots
    }

    val rows = monthSlots.chunked(7)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        rows.forEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                week.forEach { date ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Log.d("ERROR", "FixedMonthGrid: date: ${activeDates} / ${date}")
                        if (date != null) {
                            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date)
                            val isActive = activeDates.contains(dateStr)
                            val dayNum = SimpleDateFormat("d", Locale.US).format(date)

                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(if (isActive) Color(0xFFFF9800) else Color.Transparent),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = dayNum,
                                    fontSize = 12.sp,
                                    fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isActive) Color.White else Color.Unspecified
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun MonthlyStreak(activityModel: List<MonthlyActivityModel>) {

    val pagerState = rememberPagerState(pageCount = {activityModel.size})
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            BodyText(text = "Month")
            HorizontalPager(
                state = pagerState,

            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp)
                    .background(Color.Gray)) { }
            }
            Row(

            ) { }
        }
    }

//    Row(
//        Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//    ) {
//        weeks.forEach { week ->
//            val fraction = if (week.totalDays > 0) week.daysCompleted / week.totalDays.toFloat() else 0f
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Box(
//                    modifier = Modifier
//                        .width(22.dp)
//                        .height(56.dp)
//                        .clip(RoundedCornerShape(6.dp))
//                        .background(MaterialTheme.colorScheme.surfaceContainerHigh),
//                    contentAlignment = Alignment.BottomCenter,
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(fraction.coerceIn(0f, 1f))
//                            .clip(RoundedCornerShape(6.dp))
//                            .background(Color(0xFFF2A65A)),
//                    )
//                }
//                Spacer(Modifier.height(6.dp))
//                BodyText(
//                    text = week.label,
//                    textSize = bodyFontTinySize,
//                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
//                )
//                BodyText(
//                    text = "${week.daysCompleted}/${week.totalDays}",
//                    textSize = bodyFontTinySize,
//                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
//                )
//            }
//        }
//    }
}

@Composable
private fun OverallProgressCard(completion: Float, accuracy: Float) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DualRingGauge(
                outerValue = completion,
                innerValue = accuracy,
                modifier = Modifier.size(96.dp),
            )
            WidthSpacer()
            Column(Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                BodyText(
                    "Overall Progress",
                    textSize = bodyFontSmallSize,
                    fontWeight = FontWeight.SemiBold,
                )
                SmallHeightSpacer()
                LegendRow(
                    color = cOrange,
                    label = "Content completed",
                    value = completion,
                )
                TinyHeightSpacer()
                LegendRow(
                    color = lightBlue,
                    label = "Overall accuracy",
                    value = accuracy,
                )
            }
        }
    }
}

@Composable
private fun LegendRow(color: Color, label: String, value: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(8.dp)
                .clip(RoundedCornerShape(50))
                .background(color),
        )
        Spacer(Modifier.width(8.dp))
        BodyText(
            text = label,
            textSize = bodyFontTinySize,
            fontWeight = FontWeight.SemiBold,
            textColor = textBlack,
            modifier = Modifier.weight(1f),
        )
        BodyText(
            text = "${(formatStringToDisplay(value))}%",
            textSize = bodyFontTinySize,
            fontWeight = FontWeight.Bold,
            textColor = textBlack,
        )
    }
}

@Composable
private fun DualRingGauge(
    outerValue: Float,
    innerValue: Float,
    modifier: Modifier = Modifier,
) {
    val outerColor = cOrange
    val innerColor = lightBlue
    val trackColor = Color.LightGray

    Box(modifier, contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val strokeWidth = 10.dp.toPx()
            val gap = 4.dp.toPx()

            // Outer ring — completion
            val outerInset = strokeWidth / 2
            drawArc(
                color = trackColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(outerInset, outerInset),
                size = Size(size.width - strokeWidth, size.height - strokeWidth),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            )
            drawArc(
                color = outerColor,
                startAngle = -90f,
                sweepAngle = 360f * (outerValue / 100f).coerceIn(0f, 1f),
                useCenter = false,
                topLeft = Offset(outerInset, outerInset),
                size = Size(size.width - strokeWidth, size.height - strokeWidth),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            )

            // Inner ring — accuracy
            val innerStroke = strokeWidth * 0.75f
            val innerInset = strokeWidth + gap + innerStroke / 2
            drawArc(
                color = trackColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(innerInset, innerInset),
                size = Size(size.width - innerInset * 2, size.height - innerInset * 2),
                style = Stroke(width = innerStroke, cap = StrokeCap.Round),
            )
            drawArc(
                color = innerColor,
                startAngle = -90f,
                sweepAngle = 360f * (innerValue/100f).coerceIn(0f, 1f),
                useCenter = false,
                topLeft = Offset(innerInset, innerInset),
                size = Size(size.width - innerInset * 2, size.height - innerInset * 2),
                style = Stroke(width = innerStroke, cap = StrokeCap.Round),
            )
        }
        Text(
            "${(formatStringToDisplay(outerValue))}%",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun TopicProgressItem(topic: NewTopicProgress) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(10.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                BodyText(
                    text = topic.parentName,
                    textSize = bodyFontSmallSize,
                    fontWeight = FontWeight.SemiBold,
                    textColor = textBlack
                )
                BodyText(
                    text = "${topic.questionsDone}/${topic.questionsTotal} done",
                    textSize = bodyFontTinySize,
                    fontWeight = FontWeight.Normal,
                    textColor = textBlack,
                )
            }

            Spacer(Modifier.height(12.dp))

            DualProgressBar(
                label = "Completed",
                value = topic.completedPercent,
                color = cOrange,
            )
            SmallHeightSpacer()
            DualProgressBar(
                label = "Accuracy",
                value = topic.accuracyPercent,
                color = lightBlue,
            )
        }
    }
}

@Composable
private fun DualProgressBar(label: String, value: Float, color: Color) {
    Column {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BodyText(
                text = label,
                textSize = bodyFontTinySize,
                fontWeight = FontWeight.Normal,
                textColor = textBlack,
            )
            BodyText(
                text = "${formatStringToDisplay(value)}%",
                textSize = bodyFontTinySize,
                fontWeight = FontWeight.Medium,
                textColor = textBlack,
            )
        }
        TinyHeightSpacer()
        LinearProgressIndicator(
            progress = { (value/100).coerceIn(0f, 1f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(50)),
            color = color,
            trackColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            strokeCap = StrokeCap.Round,
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun ProgressScreenPreview() {
//    MaterialTheme {
//        ProgressScreen(
//            currentStreak = 12,
//            longestStreak = 21,
//            dailyStreak = listOf(
//                DayStreak("M", true),
//                DayStreak("T", true),
//                DayStreak("W", true),
//                DayStreak("T", false),
//                DayStreak("F", true),
//                DayStreak("S", true),
//                DayStreak("S", false),
//            ),
//            weeklyStreak = listOf(
//                WeekStreak("W1", 5),
//                WeekStreak("W2", 7),
//                WeekStreak("W3", 4),
//                WeekStreak("W4", 6),
//                WeekStreak("W5", 7),
//                WeekStreak("W6", 3),
//            ),
//            overallCompletion = 0.62f,
//            overallAccuracy = 0.81f,
//            topics = listOf(
//                TopicProgress("Algebra Basics", 0.9f, 0.85f, 45, 50),
//                TopicProgress("Geometry", 0.55f, 0.7f, 22, 40),
//                TopicProgress("Probability", 0.3f, 0.6f, 12, 40),
//                TopicProgress("Trigonometry", 0.1f, 0.5f, 4, 40),
//            ),
//        )
//    }
//}