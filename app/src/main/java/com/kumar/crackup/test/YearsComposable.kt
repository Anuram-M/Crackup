package com.kumar.crackup.test

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.kumar.crackup.components.NoContentDialog
import com.kumar.crackup.templates.ScreenScaffold
import com.kumar.crackup.templates.SelectionList
import com.kumar.crackup.viewmodel.MyViewModel

@Composable
fun YearsComposable(navController: NavHostController, viewModel: MyViewModel) {
    val exam by viewModel.selectedExam.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    var emptyTopic by remember { mutableStateOf<String?>(null) }
    ScreenScaffold(title = "Select Year") {
        val years = exam?.availableYears.orEmpty()
        SelectionList(items = years.map { it.toString() to it }) { exam ->
//            viewModel.onExamSelected(exam) // no fetch — availableYears already embedded
            viewModel.onYearSelected(exam.toString(), {
                Log.d("QUERRYM", "SubTopicsScreen: ${viewModel.query.value}")
                viewModel.getQuestions(viewModel.query.value, {
                    if(viewModel.questions.value.isNotEmpty()) {
//                        val dailyChallenge = mutableListOf<DailyChallengeModel>()
//                        viewModel.questions.value.forEach {
//                            dailyChallenge.add(it.toDailyChallenge())
//                            Log.d("QUERRYM", "YearsComposable: ${it}")
//                        }
//                        if(dailyChallenge.isNotEmpty()) {
//                            coroutineScope.launch {
//
//                                FirebaseUtil.putDailyChallenge(dailyChallenge)
//                            }
//                        }
//                        Log.d("QUERRYM", "YearsComposable: ${dailyChallenge}")
                        navController.navigate("practice")
                    } else {
                        emptyTopic = "${viewModel.query.value.mainTopic}-${viewModel.query.value.subTopic}-${viewModel.query.value.subCollection}"
//                        Toast.makeText(context, "No questions found, try it later", Toast.LENGTH_SHORT).show()
                    }
                })
            })
//            navController.navigate("practice")
        }
        emptyTopic?.let {
            NoContentDialog(topicName = emptyTopic!!) {
                emptyTopic = null
            }
        }
    }
}