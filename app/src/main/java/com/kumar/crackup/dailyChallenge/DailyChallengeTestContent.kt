package com.kumar.crackup.dailyChallenge

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumar.crackup.components.NativeMathText
import com.kumar.crackup.components.TimerComponent
import com.kumar.crackup.templates.BigHeightSpacer
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.HeightSpacer
import com.kumar.crackup.templates.OutlineContainer
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.test.OptionRow
import com.kumar.crackup.test.alphaIndex
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.forestGreen
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.screenBackground
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.util.QuestionAnswerState

@Composable
fun DailyChallengeTestContent(
    elapsedTime: Long = 0L,
    challengeTopic: String = "Aptitude",
    scrollState: ScrollState,
    questions: List<DailyChallengeModel>,
    answers: Map<Int, QuestionAnswerState>,
    currentIndex: Int,
    onIndexChange: (Int) -> Unit,
    onSelectOption: (questionIndex: Int, optionIndex: Int) -> Unit,
    onResetOption: (Int) -> Unit,
    onPauseClicked: (Long) -> Unit,
    onSubmitChallengeClicked: () -> Unit,
) {
    if (questions.isEmpty()) {
        return
    }
    val question = questions[currentIndex]
    val isFirst = currentIndex == 0
    val isLast = currentIndex == questions.lastIndex
    val state by remember(currentIndex) {
        derivedStateOf { answers[currentIndex] ?: QuestionAnswerState() }
    }
    var elapsedSeconds by remember {
        mutableStateOf(0L)
    }
    val answeredCount = answers.values.count { it.selectedOptionIndex != null }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                TimerComponent(
                    iconSize = 24.dp,
                    fontSize = bodyFontLargeSize,
                    totalSeconds = 1200 - elapsedTime,
                    elapsedSeconds = { seconds ->

                        elapsedSeconds = seconds
                    },
                    onFinished = onSubmitChallengeClicked,
                )

                SmallHeightSpacer()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = textBlack,
                            disabledContainerColor = Color.Gray
                        ),
                        onClick = { onPauseClicked(elapsedSeconds) },
                        modifier = Modifier
                    ) {
                        Text("Continue Later", color = Color.White)
                    }

                    Button(
                        enabled = answeredCount > 0,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = lightBlue,
                            disabledContainerColor = Color.Gray
                        ),
                        onClick = { onSubmitChallengeClicked() },
                        modifier = Modifier
                    ) {
                        Text("Submit", color = Color.White)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BodyText(
                        text = "Question ${currentIndex + 1} / ${questions.size}",
                        fontWeight = FontWeight.SemiBold,
                        textSize = bodyFontSmallSize,
                        modifier = Modifier
                    )
                    BodyText(
                        text = "Answered ${answeredCount} / ${questions.size}",
                        fontWeight = FontWeight.SemiBold,
                        textSize = bodyFontSmallSize,
                        modifier = Modifier
                    )
                }
                SmallHeightSpacer()
                LinearProgressIndicator(
                    color = forestGreen,
                    trackColor = lightBlue,
                    progress = { (currentIndex + 1) / questions.size.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                )
            }
            HorizontalDivider()
            BigHeightSpacer()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(20.dp)
            ) {
                val questionText = convertTextDailyChallenge(question, true)
                NativeMathText(
                    content = questionText,
                    fontWeight = FontWeight.SemiBold,
                    color = textBlack
                )
                SmallHeightSpacer()
//            if(isEnglish) {
                question.options.forEachIndexed { optionIndex, optionText ->
                    OptionRow(
                        isRight = optionIndex == question.answer,
                        text = optionText,
                        index = alphaIndex(optionIndex),
                        isSelected = state.selectedOptionIndex == optionIndex,
                        onClick = {
                            onSelectOption(currentIndex, optionIndex)
                        }
                    )
                    HeightSpacer()
                }
//            } else {
//                question.optionsTamil.forEachIndexed { optionIndex, optionText ->
//                    OptionRow(
//                        showAnswer = showAnswer,
//                        isRight = optionIndex == question.answer,
//                        text = optionText,
//                        index = alphaIndex(optionIndex),
//                        isSelected = state.selectedOptionIndex == optionIndex,
//                        isMarkedForReview = state.isMarkedForReview && state.selectedOptionIndex == optionIndex,
//                        onClick = { onSelectOption(currentIndex, optionIndex) },
//                    )
//                    HeightSpacer()
//                }
//            }
                SmallHeightSpacer()
            }

            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlineContainer(
                    isEnabled = !isFirst,
                    contentColor = lightBlue,
                    backgroundColor = screenBackground,
                    modifier = Modifier.padding(top = 4.dp),
                    onclicK = {
                        if (!isFirst) onIndexChange(currentIndex - 1)
                    }) {
                    BodyText(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                        text = "< Prev",
                        textColor = if (!isFirst) lightBlue else Color.Gray,
                    )
                }
                OutlineContainer(
                    isEnabled = state.selectedOptionIndex != null,
                    contentColor = lightBlue,
                    backgroundColor = screenBackground,
                    modifier = Modifier.padding(top = 4.dp),
                    onclicK = {
                        onResetOption(currentIndex)
                    }
                ) {
                    BodyText(
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                        text = "Clear Selection",
                        textColor = if (state.selectedOptionIndex != null) lightBlue else Color.Gray,
                    )
                }

                Button(
                    modifier = Modifier.padding(bottom = 10.dp),
                    enabled = currentIndex < 9,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightBlue,
                        disabledContainerColor = Color.Gray
                    ),
                    onClick = {
                        if (!isLast && currentIndex < 9) onIndexChange(currentIndex + 1)
                    }
                ) {
                    Text("Next >", color = Color.White, fontFamily = bodyFont)
                }
            }
        }
    }
}