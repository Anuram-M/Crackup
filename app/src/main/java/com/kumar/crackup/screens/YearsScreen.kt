package com.kumar.crackup.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.ScreenScaffold
import com.kumar.crackup.templates.SelectionList
import com.kumar.crackup.util.FirebaseUtil
import com.kumar.crackup.viewmodel.MyViewModel
import kotlinx.coroutines.launch

@Composable
fun YearsScreen(navController: NavHostController, viewModel: MyViewModel) {
    val exam by viewModel.selectedExam.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    ScreenScaffold(title = "Select Year") {
        val years = exam?.availableYears.orEmpty()
        SelectionList(items = years.map { it.toString() to it }) { exam ->
//            viewModel.onExamSelected(exam) // no fetch — availableYears already embedded
            viewModel.onYearSelected(exam.toString(), {
                Log.d("QUERRYM", "SubTopicsScreen: ${viewModel.query.value}")
                viewModel.getQuestions(viewModel.query.value, {
                    if(viewModel.questions.value.isNotEmpty()) {
                        navController.navigate("practice")
                    } else {
                        Toast.makeText(context, "No questions found, try it later", Toast.LENGTH_SHORT).show()
                    }
                })
            })
//            navController.navigate("practice")
        }
    }
}