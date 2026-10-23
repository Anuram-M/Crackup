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
fun TamilUnitsComposable(navController: NavHostController, viewModel: MyViewModel) {
    val units by viewModel.units.collectAsState()

    ScreenScaffold(title = "Select Unit") {
        SelectionList(items = units.map { it.name to it }, modifier = Modifier.weight(1f)) { unit ->
            viewModel.onUnitSelected(unit) // no fetch — availableYears already embedded
            navController.navigate("tamilunitsSub")
        }
    }
}