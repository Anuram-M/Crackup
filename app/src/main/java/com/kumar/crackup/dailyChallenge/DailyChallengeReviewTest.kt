package com.kumar.crackup.dailyChallenge

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumar.crackup.components.NativeMathText
import com.kumar.crackup.templates.BigHeightSpacer
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.HeightSpacer
import com.kumar.crackup.templates.SelectorTextRadioButton
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.templates.SmallWidthSpacer
import com.kumar.crackup.test.OptionRow
import com.kumar.crackup.test.ResultStat
import com.kumar.crackup.test.alphaIndex
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.cOrange
import com.kumar.crackup.ui.theme.forestGreen
import com.kumar.crackup.ui.theme.heroFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.util.QuestionAnswerState
import com.kumar.crackup.util.QuizResult

@Composable
fun DailyChallengeReviewTest(
    result: QuizResult,
    questions: List<DailyChallengeModel>,
    answers: SnapshotStateMap<Int, QuestionAnswerState>
) {

    var currentIndex by remember {
        mutableStateOf(0)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            DailyChallengeStat(result)
            LazyColumn() {
                itemsIndexed(
                    items = questions,
                    key = { _, item -> item.question }) { index, question ->
                    QuestionAnswer(question, index, answers)
                }
            }
        }
    }

}

@Composable
fun DailyChallengeStat(result: QuizResult) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        HeaderText(text = result.scoreLabel, textSize = heroFontLargeSize, textColor = lightBlue)
        BodyText(text = "Score", textSize = bodyFontSmallSize, textColor = Color.Gray)

        Spacer(Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ResultStat("Attempted", "${result.attempted}/${result.totalQuestions}")
            ResultStat("Correct", "${result.correct}")
            ResultStat("Incorrect", "${result.incorrect}")
            ResultStat("Skipped", "${result.unanswered}")
        }

        Spacer(Modifier.height(20.dp))
        Spacer(Modifier.height(20.dp))

        BodyText(
            text = "Accuracy (of attempted): ${"%.1f".format(result.accuracyAmongAttempted)}%",
            textSize = bodyFontSmallSize,
            textColor = Color.Gray
        )
        BigHeightSpacer()
    }
}

@Composable
fun QuestionAnswer(
    question: DailyChallengeModel,
    currentIndex: Int,
    answers: SnapshotStateMap<Int, QuestionAnswerState>,
) {

    val state by remember(currentIndex) {
        derivedStateOf { answers[currentIndex] ?: QuestionAnswerState() }
    }
    val correctOption by remember(currentIndex) {
        derivedStateOf { question.answer }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        BigHeightSpacer()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val questionText = convertTextDailyChallenge(question, true)
            NativeMathText(
                content = "${currentIndex + 1}. ${questionText}",
                fontWeight = FontWeight.SemiBold,
                color = textBlack
            )
            SmallHeightSpacer()
//            if(isEnglish) {
            question.options.forEachIndexed { optionIndex, optionText ->
                ReviewOptionRow(
                    isRight = optionIndex == question.answer,
                    text = optionText,
                    index = alphaIndex(optionIndex),
                    state = state,
                    isCorrectOption = question.answer,
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
            val explaination = convertExplainationDailyChallenge(question, isEnglish = true)

            BodyText(
                text = "Explaination:",
                textColor = textBlack,
                fontWeight = FontWeight.SemiBold,
                textSize = bodyFontLargeSize
            )
            NativeMathText(
                content = explaination,
                textSize = bodyFontSmallSize,
                fontWeight = FontWeight.Normal,
                color = textBlack
            )
        }
        BigHeightSpacer()
        HorizontalDivider()
    }
}

@Composable
fun ReviewOptionRow(
    isRight: Boolean,
    text: String,
    index: String,
    state: QuestionAnswerState,
    isCorrectOption: Int = 0,
) {
    val borderColor =
        if (isRight) forestGreen else if (state.selectedOptionIndex != null) Color.Red else Color.Gray
    val backgroundColor =
        if (isRight) forestGreen.copy(alpha = 0.08f) else if (state.selectedOptionIndex != null && state.selectedOptionIndex != isCorrectOption) Color.Red.copy(
            alpha = 0.08f
        ) else Color.Transparent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .padding(end = 10.dp)
    ) {
        SelectorTextRadioButton(
            isSelected = state.selectedOptionIndex != null,
            selectorText = index,
            selectedColor = borderColor,
            unselectedColor = Color.Gray,
            onSelected = {})
        SmallWidthSpacer()
        NativeMathText(content = text, fontWeight = FontWeight.Normal, color = textBlack)
    }
}