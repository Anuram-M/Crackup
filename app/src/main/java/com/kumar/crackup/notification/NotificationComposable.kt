package com.kumar.crackup.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.viewmodel.MyViewModel

@Composable
fun NotificationComposable(navHostController: NavHostController, myViewModel: MyViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BodyText(
            text = "No Notifications yet!",
            textColor = textBlack
        )
    }

}