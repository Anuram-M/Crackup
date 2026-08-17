package com.kumar.crackup.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.*
import com.kumar.crackup.ui.theme.*
import com.kumar.crackup.viewmodel.MyViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kumar.crackup.R
import com.kumar.crackup.components.Background
import com.kumar.crackup.components.BottomSheet
import com.kumar.crackup.model.FAQItem
import com.kumar.crackup.model.LocalizedInstruction
import com.kumar.crackup.util.AppContentUtil
import kotlinx.coroutines.launch

data class PracticeItem(
    val id: String,
    val name: String,
    val subText: String
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeIntroSection( navHostController: NavHostController, myViewModel: MyViewModel) {
    val practiceTopic by myViewModel.query.collectAsState()
    val textVersions = listOf("pyq")
    val contents = listOf(
        "🎯 Topic-wise Smart Practice",
        "📊 Live Performance Analytics",
        "🏆 Real Exam Experience",
        "⚡ Instant Result & Rank"
    )
    val practiceItems = listOf(
        PracticeItem(
            id = "1",
            name = "📘 Practice Questions",
            subText = "Topic wise TNPSC preparation"
        ),
        PracticeItem(
            id = "2",
            name = "📰 Previous Year Questions",
            subText = "Previous year TNPSC exam questions"
        ),
    )
    var selectedItemId by remember {
        mutableStateOf("")
    }
    var userName by remember {
        mutableStateOf("")
    }

    var showSheet by remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val coroutineScope = rememberCoroutineScope()
    val isEngSelected by myViewModel.isEng.collectAsStateWithLifecycle()
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), contentAlignment = Alignment.BottomCenter) {
//        Image(painter = painterResource(R.drawable.background), modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillHeight, contentDescription = null)
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                HeaderText(text = "Start your practice", modifier = Modifier)
                IconButton(
                    onClick = {
                       showSheet = true
                    }
                ) {
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = null, tint = lightBlue)
                }
            }

            if (!textVersions.contains(practiceTopic.mainTopic))
                Column() {
                    BodyText(
                        text = "Choose your practice type and begin",
                        modifier = Modifier
                    )
                    practiceItems.forEach { item ->
                        PracticeSelectorCard(item, selectedItemId == item.id) {
                            selectedItemId = item.id
                        }
                    }
                }

            HeightSpacer()
            BodyText(text = "Language", textSize = bodyFontSize, textColor = textBlack, fontWeight = FontWeight.SemiBold)
            SmallHeightSpacer()
            Selector(
                options = listOf(
                    "English",
                    "தமிழ்"
                ),
                default = if (isEngSelected) "English" else "தமிழ்",
                modifier = Modifier.fillMaxWidth(),
                onOptionSelected = {
                    myViewModel.changeLanguage(it.equals("English"))
                },
            )
            HeightSpacer()
            ExpandableCard(headingText = "📌 Exam Instructions", AppContentUtil.examInstructions, isEngSelected)
        }

        ColoredButton(
            roundness = 10.dp,
            buttonColor = lightBlue,
            buttonText = "Start Exam 🚀",
            textColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)) {
            navHostController.navigate("test")
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    HeaderText(text = "Exam Practice Portal", modifier = Modifier)
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                    )
                    BodyText(
                        text = "Master TNPSC with topic-wise practice, previous year questions, smart analysis, and real exam experience.",
                        modifier = Modifier,
                        textColor = textBlack
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                    )
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.Start) {

                            contents.forEach {
                                BodyText(
                                    text = it, modifier = Modifier, textColor = textBlack
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                    ColoredButton(
                        roundness = 10.dp,
                        buttonColor = lightBlue,
                        buttonText = "Close",
                        textColor = dialogCardColor,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coroutineScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showSheet = false
                            }
                        }
                    }

                }
            }
        }

    }
}

@Composable
fun ExpandableCard(
    headingText: String,
    content: List<LocalizedInstruction>,
    isEnglishSelected: Boolean,
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxWidth()) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            colors = CardDefaults.cardColors(containerColor = screenBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HeaderText(
                        text = headingText,
                        textColor = lightBlue,
                        textSize = bodyFontSize,

                    )

                    Image(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp).padding(4.dp)
                            .rotate( if(expanded) 90.0f else -90f)
                    )
                }


                AnimatedVisibility(
                    visible = expanded
                ) {
                    Column() {
                        content.forEach {
                            BodyText(
                                text =
                                    if (isEnglishSelected) "• ${it.englishRule}"
                                    else "• ${it.tamilRule}",
                                textColor = textBlack.copy(alpha = 0.75f),
                                textSize = bodyFontTinySize,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}