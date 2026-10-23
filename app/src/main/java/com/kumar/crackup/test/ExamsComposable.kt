package com.kumar.crackup.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.ScreenScaffold
import com.kumar.crackup.templates.SelectionList
import com.kumar.crackup.viewmodel.MyViewModel

@Composable
fun ExamsComposable(navController: NavHostController, viewModel: MyViewModel) {
    val exams by viewModel.exams.collectAsState()

    ScreenScaffold(title = "Select Exam") {
        SelectionList(items = exams.map { it.name to it }, modifier = Modifier.weight(1f)) { exam ->
            viewModel.onExamSelected(exam) // no fetch — availableYears already embedded
            navController.navigate("Years")
        }
    }
}