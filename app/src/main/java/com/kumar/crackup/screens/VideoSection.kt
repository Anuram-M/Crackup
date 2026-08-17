package com.kumar.crackup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.kumar.crackup.dialog.YouTubePlayerDialog
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.linearGradient1e
import com.kumar.crackup.util.YTNavigator

@Composable
fun VideoSection(lifecycleOwner: LifecycleOwner) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogVideoId by remember { mutableStateOf("") }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Column() {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "📺 Live classes",
                        style = TextStyle(
                            color = lightBlue,
                            fontSize = bodyFontLargeSize,
                            fontFamily = bodyFont,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.linearGradient(colors = linearGradient1e),
                                        shape = RoundedCornerShape(10)
                                    )
                                    .padding(10.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    HeaderText(text = "🔥 Coming Soon", modifier = Modifier)

                                }

                            }
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "🔥 Latest TNPSC free classes",
                        style = TextStyle(
                            color = lightBlue,
                            fontSize = bodyFontLargeSize,
                            fontFamily = bodyFont,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(10.dp))
                        ) {

                            YouTubeVideoCard(
                                videoId = "S6HxjS0aej8",
                                modifier = Modifier.height(150.dp),
                                true,
                                lifecycleOwner,
                                onclick = {
                                    dialogVideoId = "S6HxjS0aej8"
                                    showDialog = true
                                }
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                    Box(modifier = Modifier.padding(10.dp)) {
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(10.dp))
                        ) {
                            YouTubeVideoCard(
                                videoId = "f6bk7f5kx6U",
                                modifier = Modifier.height(160.dp),
                                false,
                                lifecycleOwner,
                                onclick = {
                                    dialogVideoId = "f6bk7f5kx6U"
                                    showDialog = true
                                }
                            )
                        }
                    }
                    ColoredButton(
                        buttonText = "View all YouTube Videos",
                        buttonColor = lightBlue,
                        textColor = Color.White,
                    ) {
                        YTNavigator(
                            context, "@CrackUpofficial"
                        )
                    }
                }
            }

        }

        if (showDialog) {
            YouTubePlayerDialog(videoId = dialogVideoId, onDismiss = { showDialog = false })
        }
    }

}