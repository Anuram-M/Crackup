package com.kumar.crackup.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kumar.crackup.components.ComingSoonView
import com.kumar.crackup.model.DayStreak
import com.kumar.crackup.model.TopicProgress
import com.kumar.crackup.model.WeekStreak
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.HeightSpacer
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.templates.TinyHeightSpacer
import com.kumar.crackup.templates.WidthSpacer
import com.kumar.crackup.ui.theme.amber
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.cOrange
import com.kumar.crackup.ui.theme.dialogCardColor
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.util.AppContentUtil
import kotlin.math.roundToInt


@Composable
fun ProgressComposable(navHostController: NavHostController) {
    /*Box(modifier = Modifier.fillMaxSize()) { }*/

//    ComingSoonView()
    ProgressScreen(
        currentStreak = 2,
        longestStreak = 3,
        dailyStreak = AppContentUtil.dayStreaks,
        weeklyStreak = AppContentUtil.weekStreaks,
        overallCompletion = 0.45f,
        overallAccuracy = 0.8f,
        topics = AppContentUtil.topicsProgress,

        )
}

@Composable
fun ProgressScreen(
    currentStreak: Int,
    longestStreak: Int,
    dailyStreak: List<DayStreak>,
    weeklyStreak: List<WeekStreak>,
    overallCompletion: Float,
    overallAccuracy: Float,
    topics: List<TopicProgress>,
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
                weeklyStreak = weeklyStreak,
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
    dailyStreak: List<DayStreak>,
    weeklyStreak: List<WeekStreak>,
) {
    var view by remember { mutableStateOf(StreakView.DAILY) }

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
                    )
                    BodyText(
                        text ="Best: $longestStreak days",
                        textSize = bodyFontSmallSize,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Spacer(Modifier.weight(1f))
                StreakViewToggle(view = view, onChange = { view = it })
            }
            HeightSpacer()
            when (view) {
                StreakView.DAILY -> DailyStreakRow(dailyStreak)
                StreakView.WEEKLY -> WeeklyStreakRow(weeklyStreak)
            }
        }
    }
}

@Composable
private fun StreakViewToggle(view: StreakView, onChange: (StreakView) -> Unit) {
    val options = listOf(StreakView.DAILY to "Daily", StreakView.WEEKLY to "Weekly")
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
private fun DailyStreakRow(days: List<DayStreak>) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        days.forEach { day ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (day.completed) amber
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
                    text = day.label.subSequence(0, 3).toString(),
                    textColor = textBlack.copy(alpha = 0.5f),
                    textSize = bodyFontTinySize
                )
            }
        }
    }
}

@Composable
private fun WeeklyStreakRow(weeks: List<WeekStreak>) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        weeks.forEach { week ->
            val fraction = if (week.totalDays > 0) week.daysCompleted / week.totalDays.toFloat() else 0f
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .width(22.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(fraction.coerceIn(0f, 1f))
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFF2A65A)),
                    )
                }
                Spacer(Modifier.height(6.dp))
                BodyText(
                    text = week.label,
                    textSize = bodyFontTinySize,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                BodyText(
                    text = "${week.daysCompleted}/${week.totalDays}",
                    textSize = bodyFontTinySize,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
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
            text = "${(value * 100).roundToInt()}%",
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
                sweepAngle = 360f * outerValue.coerceIn(0f, 1f),
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
                sweepAngle = 360f * innerValue.coerceIn(0f, 1f),
                useCenter = false,
                topLeft = Offset(innerInset, innerInset),
                size = Size(size.width - innerInset * 2, size.height - innerInset * 2),
                style = Stroke(width = innerStroke, cap = StrokeCap.Round),
            )
        }
        Text(
            "${(outerValue * 100).roundToInt()}%",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun TopicProgressItem(topic: TopicProgress) {
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
                    text = topic.name,
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
                text = "${(value * 100).roundToInt()}%",
                textSize = bodyFontTinySize,
                fontWeight = FontWeight.Medium,
                textColor = textBlack,
            )
        }
        TinyHeightSpacer()
        LinearProgressIndicator(
            progress = { value.coerceIn(0f, 1f) },
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

@Preview(showBackground = true)
@Composable
private fun ProgressScreenPreview() {
    MaterialTheme {
        ProgressScreen(
            currentStreak = 12,
            longestStreak = 21,
            dailyStreak = listOf(
                DayStreak("M", true),
                DayStreak("T", true),
                DayStreak("W", true),
                DayStreak("T", false),
                DayStreak("F", true),
                DayStreak("S", true),
                DayStreak("S", false),
            ),
            weeklyStreak = listOf(
                WeekStreak("W1", 5),
                WeekStreak("W2", 7),
                WeekStreak("W3", 4),
                WeekStreak("W4", 6),
                WeekStreak("W5", 7),
                WeekStreak("W6", 3),
            ),
            overallCompletion = 0.62f,
            overallAccuracy = 0.81f,
            topics = listOf(
                TopicProgress("Algebra Basics", 0.9f, 0.85f, 45, 50),
                TopicProgress("Geometry", 0.55f, 0.7f, 22, 40),
                TopicProgress("Probability", 0.3f, 0.6f, 12, 40),
                TopicProgress("Trigonometry", 0.1f, 0.5f, 4, 40),
            ),
        )
    }
}