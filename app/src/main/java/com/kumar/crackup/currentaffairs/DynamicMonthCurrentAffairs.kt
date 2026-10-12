package com.kumar.crackup.currentaffairs

//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowForward
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.kumar.crackup.ui.theme.BaseAppTheme
//import java.time.LocalDate
//import java.time.YearMonth
//import java.time.format.TextStyle
//import java.util.Locale
//
//data class DynamicMonthItem(
//    val monthName: String,
//    val year: Int,
//    val monthValue: Int,
//    val isAvailable: Boolean,
//    val isCurrentMonth: Boolean
//)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DynamicMonthCurrentAffairsComposable() {
//    // 1. Get system date dynamically
//    val currentDate = remember { LocalDate.now() }
//    val currentYear = currentDate.year
//    val currentMonthValue = currentDate.monthValue
//
//    // 2. Generate selectable year chips (Current year down to 3 previous years)
//    val availableYears = remember(currentYear) {
//        (currentYear downTo currentYear - 3).map { it.toString() }
//    }
//    var selectedYearStr by remember { mutableStateOf(currentYear.toString()) }
//    val selectedYear = selectedYearStr.toInt()
//
//    // 3. Dynamically compute the 12 months in reverse order (December to January / Latest to Earliest)
//    val monthItems = remember(selectedYear, currentYear, currentMonthValue) {
//        (12 downTo 1).map { month ->
//            val monthDate = YearMonth.of(selectedYear, month)
//            val monthDisplayName = "${monthDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)} $selectedYear"
//
//            val isCurrentMonth = (selectedYear == currentYear && month == currentMonthValue)
//            // Month is available if it's in a past year, or an elapsed/current month in the active year
//            val isAvailable = (selectedYear < currentYear) || (selectedYear == currentYear && month <= currentMonthValue)
//
//            DynamicMonthItem(
//                monthName = monthDisplayName,
//                year = selectedYear,
//                monthValue = month,
//                isAvailable = isAvailable,
//                isCurrentMonth = isCurrentMonth
//            )
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Monthly Current Affairs",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 20.sp
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFFF8FAFC)
//                )
//            )
//        },
//        containerColor = Color(0xFFF8FAFC)
//    ) { paddingValues ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(horizontal = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            item {
//                Text(
//                    text = "Select a month to explore topic-wise current affairs and exam-oriented content.",
//                    fontSize = 14.sp,
//                    color = Color.Gray,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//            }
//
//            // Year selector
//            item {
//                LazyRow(
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    items(availableYears) { year ->
//                        val isSelected = year == selectedYearStr
//                        FilterChip(
//                            selected = isSelected,
//                            onClick = { selectedYearStr = year },
//                            label = { Text(year) },
//                            colors = FilterChipDefaults.filterChipColors(
//                                selectedContainerColor = Color(0xFF1D4ED8),
//                                selectedLabelColor = Color.White,
//                                containerColor = Color.White,
//                                labelColor = Color.Black
//                            ),
//                            shape = RoundedCornerShape(8.dp)
//                        )
//                    }
//                }
//            }
//
//            // Render all 12 dynamic months in reverse order
//            items(
//                items = monthItems,
//                key = { "${it.year}-${it.monthValue}" }
//            ) { item ->
//                DynamicMonthCard(item = item, onClick = { /* Navigate to month content */ })
//            }
//
//            item {
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun DynamicMonthCard(
//    item: DynamicMonthItem,
//    onClick: () -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(enabled = item.isAvailable, onClick = onClick),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(40.dp)
//                    .clip(RoundedCornerShape(8.dp))
//                    .background(Color(0xFFEFF6FF)),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Default.DateRange,
//                    contentDescription = null,
//                    tint = Color(0xFF2563EB)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Text(
//                text = item.monthName,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF1E293B)
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Text(
//                text = if (item.isAvailable) "Explore Monthly Affairs" else "Monthly current affairs are not published yet.",
//                fontSize = 13.sp,
//                color = if (item.isAvailable) Color(0xFF64748B) else Color(0xFF94A3B8)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                when {
//                    item.isCurrentMonth -> {
//                        Box(
//                            modifier = Modifier
//                                .clip(CircleShape)
//                                .background(Color(0xFFEFF6FF))
//                                .padding(horizontal = 12.dp, vertical = 4.dp)
//                        ) {
//                            Text(
//                                text = "Current Month",
//                                color = Color(0xFF2563EB),
//                                fontSize = 11.sp,
//                                fontWeight = FontWeight.Medium
//                            )
//                        }
//                    }
//                    !item.isAvailable -> {
//                        Box(
//                            modifier = Modifier
//                                .clip(CircleShape)
//                                .background(Color(0xFFFFF7ED))
//                                .padding(horizontal = 12.dp, vertical = 4.dp)
//                        ) {
//                            Text(
//                                text = "Upcoming",
//                                color = Color(0xFFEA580C),
//                                fontSize = 11.sp,
//                                fontWeight = FontWeight.Medium
//                            )
//                        }
//                    }
//                    else -> {
//                        Spacer(modifier = Modifier.width(1.dp))
//                    }
//                }
//
//                if (item.isAvailable) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(4.dp)
//                    ) {
//                        Text(
//                            text = "Explore Monthly Affairs",
//                            color = Color(0xFF2563EB),
//                            fontSize = 13.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
//                            contentDescription = null,
//                            tint = Color(0xFF2563EB),
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }
//                } else {
//                    Text(
//                        text = "Coming Soon",
//                        color = Color(0xFF2563EB),
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                }
//            }
//        }
//    }
//}
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.ui.theme.BaseAppTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

data class DynamicMonthItem(
    val monthName: String,
    val year: Int,
    val monthValue: Int,
    val isAvailable: Boolean,
    val isCurrentMonth: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicMonthCurrentAffairsComposable() {
    val currentDate = remember { LocalDate.now() }
    val currentYear = currentDate.year
    val currentMonthValue = currentDate.monthValue

    val availableYears = remember(currentYear) {
        (currentYear downTo currentYear - 3).map { it.toString() }
    }
    var selectedYearStr by remember { mutableStateOf(currentYear.toString()) }
    val selectedYear = selectedYearStr.toInt()

    // Generate list dynamically based on selected year
    val monthItems = remember(selectedYear, currentYear, currentMonthValue) {
        if (selectedYear == currentYear) {
            // CURRENT YEAR: Start from current month and go down to January (1)
            (currentMonthValue downTo 1).map { month ->
                val monthDate = YearMonth.of(selectedYear, month)
                val monthDisplayName = "${monthDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)} $selectedYear"
                val isCurrentMonth = (month == currentMonthValue)

                DynamicMonthItem(
                    monthName = monthDisplayName,
                    year = selectedYear,
                    monthValue = month,
                    isAvailable = true,
                    isCurrentMonth = isCurrentMonth
                )
            }
        } else {
            // PAST YEARS: Start from December (12) down to January (1)
            (12 downTo 1).map { month ->
                val monthDate = YearMonth.of(selectedYear, month)
                val monthDisplayName = "${monthDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)} $selectedYear"

                DynamicMonthItem(
                    monthName = monthDisplayName,
                    year = selectedYear,
                    monthValue = month,
                    isAvailable = true,
                    isCurrentMonth = false
                )
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Monthly Current Affairs",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF8FAFC)
                )
            )
        },
        containerColor = Color(0xFFF8FAFC)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Select a month to explore topic-wise current affairs and exam-oriented content.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Year selector
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(availableYears) { year ->
                        val isSelected = year == selectedYearStr
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedYearStr = year },
                            label = { Text(year) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFF1D4ED8),
                                selectedLabelColor = Color.White,
                                containerColor = Color.White,
                                labelColor = Color.Black
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }
                }
            }

            // Render dynamic list starting from active/latest month
            items(
                items = monthItems,
                key = { "${it.year}-${it.monthValue}" }
            ) { item ->
                DynamicMonthCard(item = item, onClick = { /* Navigate to month content */ })
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun DynamicMonthCard(
    item: DynamicMonthItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = item.isAvailable, onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFEFF6FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color(0xFF2563EB)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.monthName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Explore Monthly Affairs",
                fontSize = 13.sp,
                color = Color(0xFF64748B)
            )

            Spacer(modifier = Modifier.height(16.dp))

                        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                when {
                    item.isCurrentMonth -> {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(0xFFEFF6FF))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Current Month",
                                color = Color(0xFF2563EB),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    !item.isAvailable -> {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(0xFFFFF7ED))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Upcoming",
                                color = Color(0xFFEA580C),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    else -> {
                        Spacer(modifier = Modifier.width(1.dp))
                    }
                }

                if (item.isAvailable) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Explore Monthly Affairs",
                            color = Color(0xFF2563EB),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color(0xFF2563EB),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else {
                    Text(
                        text = "Coming Soon",
                        color = Color(0xFF2563EB),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                if (item.isCurrentMonth) {
//                    Box(
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .background(Color(0xFFEFF6FF))
//                            .padding(horizontal = 12.dp, vertical = 4.dp)
//                    ) {
//                        Text(
//                            text = "Current Month",
//                            color = Color(0xFF2563EB),
//                            fontSize = 11.sp,
//                            fontWeight = FontWeight.Medium
//                        )
//                    }
//                } else {
//                    Spacer(modifier = Modifier.width(1.dp))
//                }
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(4.dp)
//                ) {
//                    Text(
//                        text = "Explore Monthly Affairs",
//                        color = Color(0xFF2563EB),
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
//                        contentDescription = null,
//                        tint = Color(0xFF2563EB),
//                        modifier = Modifier.size(16.dp)
//                    )
//                }
//            }
        }
    }
}

@Preview
@Composable
fun previewDynamicMonthCurrentAffairsComposable() {
    BaseAppTheme {
        DynamicMonthCurrentAffairsComposable()
    }
}