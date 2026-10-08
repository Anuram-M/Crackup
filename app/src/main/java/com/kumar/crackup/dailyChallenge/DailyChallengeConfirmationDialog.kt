package com.kumar.crackup.dailyChallenge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kumar.crackup.templates.BigHeightSpacer
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.textBlack

@Composable
fun DailyChallengeConfirmationDialog(
    isPaused: Boolean,
    progress: Int,
    onFreshStart: () -> Unit,
    onContinue: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize().background(color = textBlack.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BodyText(
                    text = "You have some progress (${progress}% completed), do you wish to continue",
                    fontWeight = FontWeight.Bold,
                    textColor = textBlack,
                    textAlign = TextAlign.Center
                )
                BigHeightSpacer()
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    OutlinedButton(
                        border = BorderStroke(1.dp, color = lightBlue),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        onClick = {
                          onFreshStart()
                        }
                    ) {
                        BodyText(
                            text = "Start Fresh",
                            textSize = bodyFontSize,
                            textColor = lightBlue,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                        onClick = {
                           onContinue()
                        }
                    ) {
                        BodyText(
                            text = "Continue",
                            textSize = bodyFontSize,
                            textColor = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    }
//                    ColoredButton(
//                        buttonText = "Start Fresh",
//                        buttonColor = lightBlue,
//                        textColor = Color.White,
//                        textSize = bodyFontSize,
//                        fontWeight = FontWeight.Normal
//                    ) { }

//                    ColoredButton(
//                        buttonText = "Continue",
//                        buttonColor = lightBlue,
//                        textColor = Color.White,
//                        textSize = bodyFontSize,
//                        fontWeight = FontWeight.Normal
//                    ) { }
                }
            }
        }
    }

}