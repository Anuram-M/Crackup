package com.kumar.crackup.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kumar.crackup.components.FreeTestGuidanceCard
import com.kumar.crackup.model.TopicType
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.SubHeaderText
import com.kumar.crackup.templates.SyllabusCard
import com.kumar.crackup.viewmodel.MyViewModel

@Composable
fun SyllabusSection(navHostController: NavHostController, viewModel: MyViewModel) {

    val topics by viewModel.topic.collectAsStateWithLifecycle()
    val isEnglish by viewModel.isEng.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            HeaderText(text = "TNPSC Syllabus & Practice", modifier = Modifier)
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                        SubHeaderText(text = "Practice MCQs", modifier = Modifier)
                         LanguageSwitch(
     isEnglish = isEnglish,
     onToggle = { viewModel.changeLanguage(it) }
 )
                    }
                    topics.forEach {
                        SyllabusCard(syllabusTopic = it, isEnglish) {
                            viewModel.topicSelected(it, onNavToExam = {
                                Log.d("NAVCt", "SyllabusSection: ${TopicType.EXAM_YEAR}")
                                navHostController.navigate("EXAM_YEAR")
                            },
                                onNavToSubTopic = {
                                 navHostController.navigate("subTopics")
                                },
                                onNavTamilUnits = {
                                    navHostController.navigate("Units")
                                })
//                            navHostController.navigate(it.type)
                        }
                    }
                }
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                ) {
                    HeaderText(text = "🏆 MOCK TESTS (⭐ Premium)", modifier = Modifier)
                }
            }

            FreeTestGuidanceCard()
        }
    }
}