package com.kumar.crackup.dailyChallenge

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.test.ResultStat
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.heroFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.util.QuestionAnswerState
import com.kumar.crackup.util.QuizResult

@Composable
fun DailyChallengeResult(
    result: QuizResult,
    goBack: () -> Unit,
    updateRoom: (DailyChallengeUiState) -> Unit,
    questions: List<DailyChallengeModel>,
    answers: SnapshotStateMap<Int, QuestionAnswerState>,
    seconds: Long
) {

    LaunchedEffect(Unit) {
        val model = DailyChallengeUiState(
            questions = questions,
            answers = answers,
            attempted = result.attempted,
            isCompleted = true,
            isPaused = false,
            elapsedTime = seconds
        )
        Log.d("ROOMD", "Result: ${model.attempted}")
        updateRoom(model)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))

        BodyText("Test Complete", textSize = bodyFontLargeSize, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))

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

        Spacer(Modifier.weight(1f))

        ColoredButton(
            buttonText = "Back To Home",
            buttonColor = lightBlue,
            textColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) { goBack() }
        Spacer(Modifier.height(10.dp))
    }
}