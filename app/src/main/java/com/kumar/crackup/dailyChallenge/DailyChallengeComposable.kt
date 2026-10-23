package com.kumar.crackup.dailyChallenge

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kumar.crackup.util.QuestionAnswerState
import com.kumar.crackup.util.QuizResult
import com.kumar.crackup.util.TestEvaluator
import com.kumar.crackup.viewmodel.MyViewModel
import kotlin.collections.set

@Composable
fun DailyChallengeComposable(navHostController: NavHostController, myViewModel: MyViewModel) {

    val challengeState by myViewModel.dailyChallenge.collectAsStateWithLifecycle()

    val showAnswer = remember { mutableStateOf(challengeState.isCompleted) }
    var currentIndex by remember { mutableStateOf(challengeState.currentQuestionIndex) }
    val answers = remember(challengeState.answers) { mutableStateMapOf(*challengeState.answers.map { it.key to it.value }.toTypedArray()) }

    LaunchedEffect(Unit) { currentIndex = challengeState.currentQuestionIndex }

    val scrollState = rememberScrollState()
    LaunchedEffect(currentIndex) { scrollState.scrollTo(0) }
    var result by remember { mutableStateOf<QuizResult?>(null) }
    val testResult = result
    val elapsedTime = remember {
        mutableStateOf(0L)
    }
    if (showAnswer.value) {
            DailyChallengeReviewTest(result = null, challengeState.questions,
                answers
            )
    } else {
        if(testResult != null) {
            DailyChallengeResult(
                questions = challengeState.questions,
                answers = answers,
                seconds = elapsedTime.value,
                updateRoom = { dailyChallengeuistate -> myViewModel.updateRoom(dailyChallengeuistate) },
                result = testResult,
                goBack = { navHostController.popBackStack() }
            )
        } else {
            DailyChallengeTestContent(
                scrollState = scrollState,
                questions = challengeState.questions,
                answers = answers,
                currentIndex = currentIndex,
                onIndexChange = { currentIndex = it },
                onSubmitChallengeClicked = { seconds ->
                    elapsedTime.value = seconds
                    result = TestEvaluator.evaluateDailyChallenge(
                        questions = challengeState.questions,
                        answers
                    )
                },
                onPauseClicked = { seconds ->
                    val testResult =
                        TestEvaluator.evaluateDailyChallenge(challengeState.questions, answers)
                    val model = DailyChallengeUiState(
                        questions = challengeState.questions,
                        isPaused = true,
                        isCompleted = true,
                        elapsedTime = seconds,
                        attempted = testResult.attempted,
                        currentQuestionIndex = currentIndex,
                    )
                    myViewModel.updateRoom(model)
                    navHostController.popBackStack()
                },
                onResetOption = { questionIndex ->
                    val existing = answers[questionIndex] ?: QuestionAnswerState()
                    answers[questionIndex] = existing.copy(
                        selectedOptionIndex = null,
                        isMarkedForReview = false
                    )
                },
                onSelectOption = { questionIndex, optionIndex ->
                    val existing = answers[questionIndex] ?: QuestionAnswerState()

                    // Changing the selection clears any previous "final" lock —
                    // marking final should reflect confidence in the CURRENT choice.
                    answers[questionIndex] = existing.copy(
                        selectedOptionIndex = optionIndex,
                        isMarkedForReview = if (existing.selectedOptionIndex == optionIndex) existing.isMarkedForReview else false
                    )
                },
            )
        }
    }
}

fun convertTextDailyChallenge(question: DailyChallengeModel, isEnglish: Boolean): String {
    return if (isEnglish) AnnotatedString.fromHtml(question.question)
        .toString() else AnnotatedString.fromHtml(question.questionTamil).toString()
}

fun convertExplainationDailyChallenge(question: DailyChallengeModel, isEnglish: Boolean): String {
    return if (isEnglish) AnnotatedString.fromHtml(question.explanation)
        .toString() else AnnotatedString.fromHtml(question.explanationTamil).toString()
}