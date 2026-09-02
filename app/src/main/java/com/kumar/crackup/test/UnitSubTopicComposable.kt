package com.kumar.crackup.test


import android.util.Log
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
import com.kumar.crackup.viewmodel.MyViewModel

@Composable
fun UnitSubTopicComposable(navController: NavHostController, viewModel: MyViewModel) {
    val unit by viewModel.selectedUnit.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    var emptyTopic by remember { mutableStateOf<String?>(null) }
    ScreenScaffold(title = "Select Sub-Topic") {
        val unitSubTopics = unit?.unitTopics.orEmpty()
        val unitSubTopicsEnglish = unit?.unitTopicsEnglish.orEmpty()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            SelectionList(items = unitSubTopics.map { it.toString() to it }) { name ->
                val selectedSub = unitSubTopicsEnglish[unitSubTopics.indexOf(name)]
                viewModel.onUnitSubTopicSelected(selectedSub, {
                    Log.d("QUERRYM", "SubTopicsScreen: ${viewModel.query.value}")
                    viewModel.getQuestions(viewModel.query.value, {
                        if(viewModel.questions.value.isNotEmpty()) {
                            navController.navigate("practice")
                        } else {
                            emptyTopic = name
                        }
                    })
                }) // no fetch — availableYears already embedded
            }

            emptyTopic?.let {
                NoContentDialog(topicName = emptyTopic!!) { }
            }
        }
    }
}