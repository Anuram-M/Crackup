package com.kumar.crackup.templates

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kumar.crackup.R
import com.kumar.crackup.components.LetterLoader

@Composable
fun LoadingOverlay(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    // Custom indicator or animation here
    content: @Composable () -> Unit = { CircularProgressIndicator() }
) {
    if (isLoading) {
        Log.d("CRACK", "LoadingOverlay: hellow")
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.55f))
                // Intercept clicks so user can't tap buttons underneath while loading
                .pointerInput(Unit) {},
            contentAlignment = Alignment.Center
        ) {
            LetterLoader(
                letter = {
                    Image(painter = painterResource(R.drawable.hat), modifier = Modifier.size(56.dp), contentDescription = null)
                },

            )
//            content()
        }
    }
}