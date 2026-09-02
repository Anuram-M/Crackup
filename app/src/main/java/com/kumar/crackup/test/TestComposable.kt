package com.kumar.crackup.test

//import com.kumar.crackup.components.KMathText
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kumar.crackup.R
import com.kumar.crackup.components.BottomSheet
import com.kumar.crackup.components.NativeMathText
import com.kumar.crackup.components.TimerComponent
import com.kumar.crackup.model.NewQuestion
import com.kumar.crackup.model.ServiceModel
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.HeightSpacer
import com.kumar.crackup.templates.OutlineContainer
import com.kumar.crackup.templates.SelectorTextRadioButton
import com.kumar.crackup.templates.SmallHeightSpacer
import com.kumar.crackup.templates.SmallWidthSpacer
import com.kumar.crackup.templates.TinyWidthSpacer
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.cOrange
import com.kumar.crackup.ui.theme.forestGreen
import com.kumar.crackup.ui.theme.heroFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.screenBackground
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.util.AppContentUtil
import com.kumar.crackup.util.QuestionAnswerState
import com.kumar.crackup.util.QuizResult
import com.kumar.crackup.util.TestEvaluator
import com.kumar.crackup.viewmodel.MyViewModel
import kotlinx.coroutines.launch

@Composable
fun Test( navHostController: NavHostController, myViewModel: MyViewModel) {

    val isEnglish by myViewModel.isEng.collectAsStateWithLifecycle()
    val questions by myViewModel.questions.collectAsStateWithLifecycle()

    Log.d("QUERRYM", "Test: ${questions.size}, ${questions[0]}")

    TestComposable(
        viewModel = myViewModel,
        isEnglish = isEnglish,
        questions = questions
    ) { }
}

@Composable
fun TestComposable(
    viewModel: MyViewModel,
    isEnglish: Boolean,
    questions: List<NewQuestion>,
    onExit: () -> Unit
) {
    // answers keyed by question INDEX (stable for this attempt), not id,
    // since navigation and the palette both work off index.
    val answers = remember { mutableStateMapOf<Int, QuestionAnswerState>() }
    var currentIndex by remember { mutableIntStateOf(0) }
    var result by remember { mutableStateOf<QuizResult?>(null) }
    val context = LocalContext.current.applicationContext
    val submittedResult = result
    var seeResult by remember { mutableStateOf(false) }
    if (submittedResult != null) {
        QuizResultScreen(
            result = submittedResult,
            onExit = onExit,
            onReview = {
                currentIndex = 0
                seeResult = true
                result = null } // go back to review answers if you want that flow
        )
    } else {
        QuizScreen(
            myViewModel = viewModel,
            showAnswer = seeResult,
            isEnglish = isEnglish,
            questions = questions,
            currentIndex = currentIndex,
            answers = answers,
            onIndexChange = { currentIndex = it },
            onPremiumClick = { Toast.makeText(context, "This is Premium, subscribe to access premium content!", Toast.LENGTH_SHORT ).show()},
            onSelectOption = { questionIndex, optionIndex ->
                val existing = answers[questionIndex] ?: QuestionAnswerState()
                // Changing the selection clears any previous "final" lock —
                // marking final should reflect confidence in the CURRENT choice.
                answers[questionIndex] = existing.copy(
                    selectedOptionIndex = optionIndex,
                    isMarkedForReview = if (existing.selectedOptionIndex == optionIndex) existing.isMarkedForReview else false
                )
            },
            onResetOption = { questionIndex ->
                val existing = answers[questionIndex] ?: QuestionAnswerState()
                answers[questionIndex] = existing.copy(
                    selectedOptionIndex = null,
                    isMarkedForReview =  false
                )

            },
            onToggleMarkFinal = { questionIndex ->
//                val existing = answers[questionIndex]
                val existing = answers[questionIndex] ?: QuestionAnswerState()
//                if (existing?.selectedOptionIndex != null) {
                existing?.let {
                    answers[questionIndex] = existing.copy(isMarkedForReview = !existing.isMarkedForReview)
                }
            },
            onSubmit = {
                result = TestEvaluator.evaluate(questions, answers)
            }
        )
    }
}

fun alphaIndex(index: Int) : String {
    return when(index) {
        0 -> "A"
        1 -> "B"
        2 -> "C"
        3 -> "D"
        else -> "O"
    }
}

fun convertText(question: NewQuestion, isEnglish: Boolean) : String {
    return if(isEnglish) AnnotatedString.fromHtml(question.question).toString() else AnnotatedString.fromHtml(question.questionTamil).toString()
}

fun convertExplaination(question: NewQuestion, isEnglish: Boolean) : String {
    return if(isEnglish) AnnotatedString.fromHtml(question.explanation).toString() else AnnotatedString.fromHtml(question.explanationTamil).toString()
}

fun currentTopic(current: String) : ServiceModel {
    var currentServiceModel: ServiceModel = AppContentUtil.servicesList[0]
    for(service in AppContentUtil.servicesList) {
        if(service.eligibleFor.contains(current)) {
            currentServiceModel = service
            break
        }
    }
    return currentServiceModel
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuizScreen(
    myViewModel: MyViewModel,
    showAnswer: Boolean,
    isEnglish: Boolean,
    questions: List<NewQuestion>,
    currentIndex: Int,
    answers: Map<Int, QuestionAnswerState>,
    onIndexChange: (Int) -> Unit,
    onPremiumClick: () -> Unit,
    onSelectOption: (questionIndex: Int, optionIndex: Int) -> Unit,
    onToggleMarkFinal: (questionIndex: Int) -> Unit,
    onSubmit: () -> Unit,
    onResetOption: (questionIndex: Int) -> Unit
) {
    val question = questions[currentIndex]
//    val state = answers[currentIndex] ?: QuestionAnswerState()
    val state by remember(currentIndex) {
        derivedStateOf { answers[currentIndex] ?: QuestionAnswerState() }
    }
    val isFirst = currentIndex == 0
    val isLast = currentIndex == questions.lastIndex

    var showSheet by remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var showPaySheet by remember {
        mutableStateOf(false)
    }
    val paySheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    val answeredCount = answers.values.count { it.selectedOptionIndex != null }
    val markedCount = answers.values.count { it.isMarkedForReview }
    val query by myViewModel.query.collectAsStateWithLifecycle()
    var currentServiceModel: ServiceModel by remember {
        mutableStateOf(AppContentUtil.servicesList[0])
    }

    LaunchedEffect(Unit) {
        currentServiceModel = currentTopic(query.mainTopic)
    }
    Column(modifier = Modifier.fillMaxSize()) {

        // ---- Header: progress + palette ----

        Column(modifier = Modifier.padding(20.dp, 16.dp, 20.dp, 8.dp)) {

            if(!showAnswer)
            TimerComponent(
                totalSeconds = 10800,
            ) {
                onSubmit()
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BodyText(text = "Question ${currentIndex + 1} / ${questions.size}", fontWeight = FontWeight.SemiBold, textSize = bodyFontSmallSize, modifier = Modifier)

                if(!showAnswer)
                Button(
                    enabled = answeredCount > 0,
                    colors = ButtonDefaults.buttonColors(containerColor = lightBlue, disabledContainerColor = Color.Gray),
                    onClick = { onSubmit() },
                    modifier = Modifier
                ) {
                    Text("Submit", color = Color.White)
                }
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier
                        .size(10.dp)
                        .background(color = forestGreen, shape = CircleShape))
                    TinyWidthSpacer()
                    BodyText(text = "$answeredCount Attempted", textColor = textBlack, textSize = bodyFontTinySize)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier
                        .size(10.dp)
                        .background(color = cOrange, shape = CircleShape))
                    TinyWidthSpacer()
                    BodyText(text = "$markedCount Unsure", textColor = textBlack, textSize = bodyFontTinySize)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        showSheet = true
                    }) {
                        BodyText(text = "View All >", textColor = lightBlue, textSize = bodyFontTinySize) }

                }

            }
        }
        HorizontalDivider()
        HeightSpacer()

        if(!showAnswer)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp), horizontalArrangement = Arrangement.End) {

            FilterChip(
                selected = state.isMarkedForReview,
                colors = FilterChipDefaults.filterChipColors(containerColor = screenBackground,  selectedContainerColor = screenBackground,),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = state.isMarkedForReview,
//                    borderColor = if (state.selectedOptionIndex != null) textBlack else Color.Gray,
                    borderColor = textBlack,
                    selectedBorderColor = cOrange,
                    borderWidth = 1.dp,
                    selectedBorderWidth = 1.dp
                ),
                onClick = { onToggleMarkFinal(currentIndex) },
                label = { Text(text = if (state.isMarkedForReview) "Unmark" else "Mark for review",
                    color = if (state.isMarkedForReview) cOrange
                    else if(state.selectedOptionIndex != null) textBlack
                    else textBlack) },
                leadingIcon = {
                    Icon(
                        painter = if (state.isMarkedForReview) painterResource(R.drawable.bookmark) else painterResource(R.drawable.bookmark_fill),
                        contentDescription = null,
                        tint = if (state.isMarkedForReview) cOrange
                        else if(state.selectedOptionIndex != null) textBlack
                        else textBlack,
                        modifier = Modifier.size(16.dp)
                    )
                }
            )
        }
        // ---- Question + options ----
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            val questionText = convertText(question, isEnglish)
//            KMathText(text = question.question, textColor = textBlack)
            NativeMathText(content = questionText, fontWeight = FontWeight.SemiBold, color = textBlack)
//            BodyText(isHtmlText = true, text = "Q. ${questionText}", textSize = bodyFontSize, fontWeight = FontWeight.SemiBold, textColor = textBlack, modifier = Modifier)
            HeightSpacer()
            if(isEnglish) {
                question.options.forEachIndexed { optionIndex, optionText ->
                    OptionRow(
                        showAnswer = showAnswer,
                        isRight = optionIndex == question.answer,
                        text = optionText,
                        index = alphaIndex(optionIndex),
                        isSelected = state.selectedOptionIndex == optionIndex,
                        isMarkedForReview = state.isMarkedForReview && state.selectedOptionIndex == optionIndex,
                        onClick = { if(!showAnswer) onSelectOption(currentIndex, optionIndex) }
                    )
                    HeightSpacer()
                }
            } else {
                question.optionsTamil.forEachIndexed { optionIndex, optionText ->
                    OptionRow(
                        showAnswer = showAnswer,
                        isRight = optionIndex == question.answer,
                        text = optionText,
                        index = alphaIndex(optionIndex),
                        isSelected = state.selectedOptionIndex == optionIndex,
                        isMarkedForReview = state.isMarkedForReview && state.selectedOptionIndex == optionIndex,
                        onClick = { onSelectOption(currentIndex, optionIndex) },
                    )
                    HeightSpacer()
                }
            }

            val explaination = convertExplaination(question, isEnglish)

            if(showAnswer)
            NativeMathText(content = "Explanation: \n ${explaination}", fontWeight = FontWeight.SemiBold, color = textBlack)
//            BodyText(isHtmlText = true, text = "Explanation: \n ${explaination}", fontWeight = FontWeight.SemiBold)


            SmallHeightSpacer()
        }

        Divider()

        // ---- Bottom nav: Prev / Next / Submit ----
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlineContainer(isEnabled = !isFirst,
                contentColor = lightBlue,
                backgroundColor = screenBackground,
                onclicK = {
                if (!isFirst) onIndexChange(currentIndex - 1)
            }) {
                BodyText(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                    text = "< Prev",
                    textColor = if (!isFirst) lightBlue else Color.Gray,
                )
            }

            if(!showAnswer)
            OutlineContainer(
                isEnabled = state.selectedOptionIndex != null,
                contentColor = lightBlue,
                backgroundColor = screenBackground,
                onclicK = {
                    onResetOption(currentIndex)
                }) {
                BodyText(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                    text = "Clear Selection",
                    textColor = if (state.selectedOptionIndex != null) lightBlue else Color.Gray,
                )
            }

            OutlineContainer(
                isEnabled = true,
                contentColor = lightBlue,
                backgroundColor = lightBlue,
                onclicK = {
                    if (!isLast && currentIndex < 9) onIndexChange(currentIndex + 1) else showPaySheet = true
                }) {
                BodyText(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                    text = "Next >",
                    textColor = Color.White,
                )
            }
        }
    }
    if(showSheet) {
        BottomSheet(sheetState = sheetState, onClose = {
            coroutineScope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    showSheet = false
                }
            }
        }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(150.dp)
                    .background(screenBackground)
                    .safeContentPadding()
            ) {
                Column() {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier
                                .size(20.dp)
                                .background(color = forestGreen, shape = CircleShape))
                            TinyWidthSpacer()
                            BodyText(text = "$answeredCount Attempted", textColor = textBlack, textSize = bodyFontSmallSize)
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier
                                .size(20.dp)
                                .background(color = cOrange, shape = CircleShape))
                            TinyWidthSpacer()
                            BodyText(text = "$markedCount Unsure", textColor = textBlack, textSize = bodyFontSmallSize)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier
                                .size(20.dp)
                                .background(color = Color.Gray, shape = CircleShape))
                            TinyWidthSpacer()
                            BodyText(text = "${questions.size - answeredCount} Left", textColor = textBlack, textSize = bodyFontSmallSize)
                        }

                    }
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        maxItemsInEachRow = 6
                    ) {
                        questions.forEachIndexed { index, item ->
                            QuestionPaletteChip(
                                number = index + 1,
                                isCurrent = index == currentIndex,
                                state = answers[index],
                                isPremium = item.premium,
                                onClick = { if(!item.premium) onIndexChange(index) else showPaySheet = true }
                            )
                        }
                    }
                }

            }
        }
    }
    if(showPaySheet) {
        BottomSheet(sheetState = paySheetState, onClose = {
            coroutineScope.launch {
                paySheetState.hide()
            }.invokeOnCompletion {
                if (!paySheetState.isVisible) {
                    showPaySheet = false
                }
            }
        }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(150.dp)
                    .background(screenBackground)
                    .safeContentPadding()
            ) {
                Column(modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)) {

                    PayPackageCard(currentPack =  currentServiceModel,
                        bestPack = AppContentUtil.servicesList[0] ,
                        onCurrentPackSelected = {

                        },
                        onBestPackSelected = {

                        })

                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            coroutineScope.launch {
                                paySheetState.hide()
                            }.invokeOnCompletion {
                                if (!paySheetState.isVisible) {
                                    showPaySheet = false
                                }
                            }
                        }) {
                        BodyText(
                            text = "Cancel",
                            textColor = Color.Red,
                            fontWeight = FontWeight.SemiBold,
                            textSize = bodyFontLargeSize
                        )
                    }

                }

            }
        }
    }
}

@Composable
private fun OptionRow(
    showAnswer: Boolean,
    isRight: Boolean,
    text: String,
    index: String,
    isSelected: Boolean,
    isMarkedForReview: Boolean,
    onClick: () -> Unit,

) {
    val borderColor = if(showAnswer) {
        if(isRight) forestGreen else if(isSelected && !isRight) Color.Red else  Color.Gray
    } else when {
        isMarkedForReview -> cOrange
        isSelected -> lightBlue
        else -> Color.Gray
    }

    val backgroundColor = if(showAnswer) {
        if(isRight) forestGreen.copy(alpha = 0.08f) else if(isSelected && !isRight) Color.Red.copy(alpha = 0.08f) else  Color.Transparent
    } else when {
        isMarkedForReview -> cOrange.copy(alpha = 0.08f)
        isSelected -> lightBlue.copy(alpha = 0.08f)
        else -> Color.Transparent
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .clickable { if(!showAnswer) onClick() }
            .padding(end = 10.dp)
    ) {
//        RadioButton(selected = isSelected, onClick = onClick, colors = RadioButtonDefaults.colors(selectedColor = borderColor, unselectedColor = Color.Gray))
        SelectorTextRadioButton(isSelected = isSelected, selectorText = index, selectedColor = borderColor, unselectedColor = Color.Gray, onSelected = onClick)
        SmallWidthSpacer()
        NativeMathText(content = text, fontWeight = FontWeight.Normal, color = textBlack)
//        BodyText(isHtmlText = true,text = text, textSize = bodyFontSize, modifier = Modifier.weight(1f))
        if (isMarkedForReview) {
            BodyText(text = "REVIEW LATER", textSize = bodyFontTinySize, fontWeight = FontWeight.Bold, textColor = borderColor, modifier = Modifier)
        }
    }
}

@Composable
private fun QuestionPaletteChip(
    number: Int,
    isCurrent: Boolean,
    isPremium: Boolean,
    state: QuestionAnswerState?,
    onClick: () -> Unit
) {
    // Color coding: gray = untouched, blue = selected, green = marked final,
    // with a ring for whichever question is currently open.
    val bg = when {
        state?.isMarkedForReview == true -> cOrange
        state?.selectedOptionIndex != null -> lightBlue
        else -> Color.LightGray
    }
    val textColor = if (state?.selectedOptionIndex != null) Color.White else textBlack

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(if (isCurrent) 36.dp else 40.dp)
//                .shadow(elevation = if (isCurrent) 4.dp else 0.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(bg)
                .then(
                    if (isCurrent) Modifier.border(1.dp, lightBlue, CircleShape)
                    else Modifier
                )
                .clickable { onClick() }
        ) {
            BodyText(
                text = if(isPremium) "🔒" else number.toString(),
                textSize = bodyFontSmallSize,
                fontWeight = FontWeight.SemiBold,
                textColor = textColor,
            )
        }

//        if(isCurrent) {
//            Box(modifier = Modifier.padding(top = 2.dp).size(3.dp).background(bg, shape = CircleShape)) { }
//        }
    }
}

@Composable
private fun QuizResultScreen(
    result: QuizResult,
    onExit: () -> Unit,
    onReview: () -> Unit
) {
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
            buttonText = "Review Answers",
            buttonColor = lightBlue,
            textColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) { onReview() }
        Spacer(Modifier.height(10.dp))
//        OutlinedButton(onClick = onExit, modifier = Modifier.fillMaxWidth()) {
//            Text("Back to Tests")
//        }
    }
}

@Composable
private fun ResultStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BodyText(text = value, textSize = bodyFontLargeSize, fontWeight = FontWeight.Bold)
        BodyText(label, textSize = bodyFontTinySize, textColor = Color.Gray)
    }
}
