package com.kumar.crackup.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.kumar.crackup.components.NoContentDialog
import com.kumar.crackup.templates.ScreenScaffold
import com.kumar.crackup.templates.SelectionList
import com.kumar.crackup.util.FirebaseUtil
import com.kumar.crackup.viewmodel.MyViewModel
import kotlinx.coroutines.launch

@Composable
fun SubTopicsScreen(navController: NavHostController, viewModel: MyViewModel) {
    val subTopics by viewModel.subTopics.collectAsState()
    val isEnglish by viewModel.isEnglish.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    var emptyTopic by remember { mutableStateOf<String?>(null) }
    ScreenScaffold(title = "Select Sub-Topic") {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            if(isEnglish) {
                SelectionList(items = subTopics.map { it.name to it }) { unit ->
//            viewModel.onUnitSelected(unit) // no fetch — availableYears already embedded
//            navController.navigate("tamilunitsSub")
                    viewModel.onSubTopicSelected(unit.subtopicQuery, {
                        Log.d("QUERRYM", "SubTopicsScreen: ${viewModel.query.value}")
                        viewModel.getQuestions(viewModel.query.value, {
                            if(viewModel.questions.value.isNotEmpty()) {
                                navController.navigate("practice")
                            } else {
                                emptyTopic = unit.subtopicQuery
                            }
                        })
                    })
                }
            } else {
                SelectionList(items = subTopics.map { it.nameTamil to it }) { unit ->
                    viewModel.onSubTopicSelected(unit.subtopicQuery, {
                        Log.d("QUERRYM", "SubTopicsScreen: ${viewModel.query.value}")
                        viewModel.getQuestions(viewModel.query.value, {
                            if(viewModel.questions.value.isNotEmpty()) {
                                navController.navigate("practice")
                            } else {
                                emptyTopic = unit.subtopicQuery
                            }
                        })
                    })
//            viewModel.onUnitSelected(unit) // no fetch — availableYears already embedded
//            navController.navigate("tamilunitsSub")
                }
            }

            emptyTopic?.let {
                NoContentDialog(topicName = emptyTopic!!) {
                    emptyTopic = null
                }
            }
        }
    }
}