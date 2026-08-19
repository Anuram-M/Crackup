package com.kumar.crackup.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kumar.crackup.viewmodel.MyViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.ui.theme.lightBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import com.kumar.crackup.R
import com.kumar.crackup.components.BottomSheet
import com.kumar.crackup.model.CurrentAffairsPDFItem
import com.kumar.crackup.model.DailyBrief
import com.kumar.crackup.templates.OutlineContainer
import com.kumar.crackup.templates.SmallWidthSpacer
import com.kumar.crackup.templates.TinyWidthSpacer
import com.kumar.crackup.ui.theme.amber
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.cOrange
import com.kumar.crackup.ui.theme.textBlack
import retrofit2.http.Body

@Composable
fun CurrentAffairsScreen(navHostController: NavHostController, myViewModel: MyViewModel) {
//    ComingSoonView()
//    Box(modifier = Modifier.fillMaxSize().background(screenBackground)) { }
    CurrentAffairsScreen(
        briefsByDate = mapOf(
            LocalDate.now() to DailyBrief(
                date = LocalDate.now(),
                importantNewsCount = 2,
                summaryPoints = listOf("Fire at the mangroove", "Tuna haul increased")
            )
        ),
    )
}

enum class DownloadState { NOT_DOWNLOADED, DOWNLOADING, DOWNLOADED }

enum class DownloadScope(val label: String) {
    DATE("Today's digest"),
    WEEK("This week"),
    MONTH("This month"),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentAffairsScreen(
    briefsByDate: Map<LocalDate, DailyBrief>,
    initialDate: LocalDate = LocalDate.now(),
    modifier: Modifier = Modifier,
) {
    var selectedDate by remember { mutableStateOf(initialDate) }
    var showMonthPicker by remember { mutableStateOf(false) }
    var showDownloadSheet by remember { mutableStateOf(false) }

    val pdfItems = remember {
        mutableStateListOf(
            CurrentAffairsPDFItem(
                id = "monthly_jul_2026",
                title = "Monthly Current Affairs",
                subtitle = "July 2026",
                sizeLabel = "12.4 MB"
            ),
            CurrentAffairsPDFItem(
                id = "weekly_20_26_jul",
                title = "Weekly CA (20 - 26 Jul 2026)",
                subtitle = "",
                sizeLabel = "4.8 MB"
            ),
        )
    }

    val scope = rememberCoroutineScope()

    fun startDownload(item: CurrentAffairsPDFItem) {
        val index = pdfItems.indexOfFirst { it.id == item.id }
        if (index == -1) return
        scope.launch {
            pdfItems[index] =
                pdfItems[index].copy(downloadState = DownloadState.DOWNLOADING, progress = 0f)
            for (p in 1..10) {
                delay(120)
                pdfItems[index] = pdfItems[index].copy(progress = p / 10f)
            }
            pdfItems[index] =
                pdfItems[index].copy(downloadState = DownloadState.DOWNLOADED, progress = 1f)
        }
    }

    fun viewPdf(item: CurrentAffairsPDFItem) {
        // Hook up to your PDF viewer / file opener here.
        // e.g. openFileWithIntent(context, localFilePathFor(item.id))
    }

    var sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    Column(modifier = modifier.fillMaxSize()) {
        DateStrip(
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it },
            onOpenMonthPicker = { showMonthPicker = true },
        )

        LazyColumnBody(
            selectedDate = selectedDate,
            brief = briefsByDate[selectedDate],
            pdfItems = pdfItems,
            onDownloadClick = { item ->
                if (item.downloadState == DownloadState.NOT_DOWNLOADED) startDownload(item)
                else if (item.downloadState == DownloadState.DOWNLOADED) viewPdf(item)
            },
            onDownloadSummaryClick = { showDownloadSheet = true },
        )
    }

    if (showMonthPicker) {
        MonthCalendarDialog(
            initialMonth = YearMonth.from(selectedDate),
            selectedDate = selectedDate,
            onDateSelected = {
                selectedDate = it
                showMonthPicker = false
            },
            onDismiss = { showMonthPicker = false },
        )
    }

    if (showDownloadSheet) {
        DownloadScopeSheet(
            state = sheetState,
            selectedDate = selectedDate,
            onScopeChosen = { scopeChoice ->
                showDownloadSheet = false
                val id = when (scopeChoice) {
                    DownloadScope.DATE -> "daily_${selectedDate}"
                    DownloadScope.WEEK -> "weekly_20_26_jul"
                    DownloadScope.MONTH -> "monthly_jul_2026"
                }
                val existing = pdfItems.find { it.id == id }
                if (existing != null) {
                    startDownload(existing)
                } else {
                    val newItem = CurrentAffairsPDFItem(
                        id = id,
                        title = "Current Affairs — ${scopeChoice.label}",
                        subtitle = selectedDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                        sizeLabel = "— MB",
                    )
                    pdfItems.add(0, newItem)
                    startDownload(newItem)
                }
            },
            onDismiss = { showDownloadSheet = false },
        )
    }
}

@Composable
private fun LazyColumnBody(
    selectedDate: LocalDate,
    brief: DailyBrief?,
    pdfItems: List<CurrentAffairsPDFItem>,
    onDownloadClick: (CurrentAffairsPDFItem) -> Unit,
    onDownloadSummaryClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item { SummaryCard(selectedDate, brief, onDownloadClick = onDownloadSummaryClick) }
        item { QuickActionsGrid() }
        item { RecentPdfsHeader() }
        items(pdfItems) { item ->
            PdfRow(item = item, onClick = { onDownloadClick(item) })
        }
    }
}

@Composable
private fun DateStrip(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onOpenMonthPicker: () -> Unit,
) {
    val today = LocalDate.now()
    val weekDates = remember(today) { (0..6).map { today.minusDays(it.toLong()) } }
    var showAll by remember { mutableStateOf(false) }

    Surface(color = lightBlue, tonalElevation = 1.dp) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LazyRow(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    DateChip(
                        topLabel = "All",
                        bottomLabel = "",
                        selected = showAll,
                        onClick = { showAll = true },
                    )
                }
                items(weekDates) { date ->
                    DateChip(
                        topLabel = date.dayOfMonth.toString(),
                        bottomLabel = date.month.getDisplayName(
                            TextStyle.SHORT,
                            Locale.getDefault()
                        ),
                        selected = !showAll && date == selectedDate,
                        onClick = {
                            showAll = false
                            onDateSelected(date)
                        },
                    )
                }
            }

            IconButton(onClick = onOpenMonthPicker) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "View month",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun DateChip(
    topLabel: String,
    bottomLabel: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(52.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color.White else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            topLabel,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        )
        if (bottomLabel.isNotEmpty()) {
            Text(
                bottomLabel,
                style = MaterialTheme.typography.labelSmall,
                color = if (selected) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                else MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Month calendar dialog — "view more dates" entry point
// ---------------------------------------------------------------------------

@Composable
private fun MonthCalendarDialog(
    initialMonth: YearMonth,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {
    var month by remember { mutableStateOf(initialMonth) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(16.dp), tonalElevation = 3.dp) {
            Column(Modifier.padding(16.dp)) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = { month = month.minusMonths(1) }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Previous month")
                    }
                    Text(
                        month.month.getDisplayName(
                            TextStyle.FULL,
                            Locale.getDefault()
                        ) + " ${month.year}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    IconButton(onClick = { month = month.plusMonths(1) }) {
                        Icon(Icons.Filled.ArrowForward, contentDescription = "Next month")
                    }
                }

                Spacer(Modifier.height(8.dp))

                val dayLabels = listOf("S", "M", "T", "W", "T", "F", "S")
                Row(Modifier.fillMaxWidth()) {
                    dayLabels.forEach { d ->
                        Text(
                            d,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                Spacer(Modifier.height(4.dp))

                val firstOfMonth = month.atDay(1)
                val leadingBlanks = firstOfMonth.dayOfWeek.value % 7 // Sunday-first grid
                val totalDays = month.lengthOfMonth()
                val cells = List(leadingBlanks) { null } + (1..totalDays).map { month.atDay(it) }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier.height(240.dp),
                ) {
                    items(cells) { date ->
                        if (date == null) {
                            Box(Modifier.aspectRatio(1f))
                        } else {
                            val isSelected = date == selectedDate
                            val isToday = date == LocalDate.now()
                            Box(
                                Modifier
                                    .aspectRatio(1f)
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
                                    .clickable { onDateSelected(date) },
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    date.dayOfMonth.toString(),
                                    color = when {
                                        isSelected -> MaterialTheme.colorScheme.onPrimary
                                        isToday -> MaterialTheme.colorScheme.primary
                                        else -> MaterialTheme.colorScheme.onSurface
                                    },
                                    fontWeight = if (isToday || isSelected) FontWeight.Bold else FontWeight.Normal,
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
private fun SummaryCard(
    date: LocalDate,
    brief: DailyBrief?,
    onDownloadClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Column {
                    BodyText(
                        text = date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")),
                        textSize = bodyFontLargeSize,
                        textColor = lightBlue,
                        fontWeight = FontWeight.Bold,
                    )
                    BodyText(
                        text = "${brief?.importantNewsCount ?: 0} Important News",
                        textSize = bodyFontSmallSize,
                        textColor = textBlack,
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.affairs),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = textBlack,
                )
            }

            if (!brief?.summaryPoints.isNullOrEmpty()) {
                Spacer(Modifier.height(14.dp))
                brief!!.summaryPoints.take(3).forEach { point ->
                    Row(Modifier.padding(vertical = 3.dp)) {
                        BodyText(
                            "•  ${point}",
                            textSize = bodyFontTinySize,
                            textColor = Color.Gray,
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))
            OutlinedButton(
                enabled = !brief?.summaryPoints.isNullOrEmpty(),
                border = BorderStroke(width = 1.dp, color = amber),
                onClick = {
                    onDownloadClick()
                }) {
                Row(
//                    modifier = Modifier
//                    .padding(horizontal = 8.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = textBlack,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    BodyText(
                        text = "Download",
                        textColor = textBlack
                    )
                }
            }
        }
    }
}

private data class QuickAction(val label: String, val icon: Int)

@Composable
private fun QuickActionsGrid() {
    val actions = listOf(
        QuickAction("Practice MCQs", R.drawable.practice),
        QuickAction("Revision Notes", R.drawable.revision),
        QuickAction("Daily Quiz", R.drawable.ideas),
        QuickAction("Mind Map", R.drawable.mind_map),
    )
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        actions.forEach { action ->
            Column(
                modifier = Modifier
                    .width(72.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { /* navigate */ }
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(lightBlue),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        painter = painterResource(action.icon),
                        contentDescription = action.label,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(Modifier.height(6.dp))
                BodyText(
                    text = action.label,
                    textSize = bodyFontTinySize,
                    textColor = textBlack
                )
            }
        }
    }
}

@Composable
private fun RecentPdfsHeader() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BodyText(
            text = "Recent CA PDFs",
            textSize = bodyFontSize,
            textColor = textBlack,
            fontWeight = FontWeight.SemiBold
        )
        TextButton(onClick = { /* navigate to full list */ }) {
            BodyText(text = "View All", textColor = lightBlue, fontWeight = FontWeight.SemiBold)
            TinyWidthSpacer()
            Icon(
                painter = painterResource(R.drawable.arrow),
                contentDescription = null,
                tint = lightBlue,
                modifier = Modifier
                    .rotate(180f)
                    .size(12.dp)
            )
        }
    }
}

@Composable
private fun PdfRow(item: CurrentAffairsPDFItem, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.errorContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                BodyText(
                    text = item.title,
                    textSize = bodyFontTinySize,
                    fontWeight = FontWeight.Medium,
                    textColor = textBlack
                )
                if (item.subtitle.isNotEmpty()) {
                    BodyText(
                        text = item.subtitle,
                        textSize = bodyFontTinySize,
                        textColor = textBlack
                    )
                }
                BodyText(
                    text = "PDF • ${item.sizeLabel}",
                    textSize = bodyFontTinySize,
                    textColor = Color.Gray,
                )
            }

            Spacer(Modifier.width(8.dp))

            Box(
                Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(lightBlue)
                    .clickable(onClick = onClick),
                contentAlignment = Alignment.Center,
            ) {
                when (item.downloadState) {
                    DownloadState.NOT_DOWNLOADED -> Icon(
                        painter = painterResource(R.drawable.downloads),
                        contentDescription = "Download",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp),
                    )

                    DownloadState.DOWNLOADING -> CircularProgressIndicator(
                        progress = { item.progress },
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                    )

                    DownloadState.DOWNLOADED -> Icon(
                        Icons.Filled.Check,
                        contentDescription = "View",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DownloadScopeSheet(
    state: SheetState,
    selectedDate: LocalDate,
    onScopeChosen: (DownloadScope) -> Unit,
    onDismiss: () -> Unit,
) {
    BottomSheet(sheetState = state, onClose = {
        onDismiss()
    }) {
        Column(Modifier.padding(20.dp)) {
            Text(
                "Download current affairs",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                selectedDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(Modifier.height(16.dp))

            DownloadScope.entries.forEach { scope ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onScopeChosen(scope) }
                        .padding(vertical = 12.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(scope.label, style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(Modifier.height(8.dp))
        }
    }
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(Modifier.padding(20.dp)) {
            Text(
                "Download current affairs",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                selectedDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(Modifier.height(16.dp))

            DownloadScope.entries.forEach { scope ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onScopeChosen(scope) }
                        .padding(vertical = 12.dp, horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(scope.label, style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}