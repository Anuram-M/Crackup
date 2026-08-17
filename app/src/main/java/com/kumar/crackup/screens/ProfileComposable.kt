package com.kumar.crackup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ColoredButton
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.components.*
import com.kumar.crackup.model.StatItem
import com.kumar.crackup.templates.HeaderText
import com.kumar.crackup.templates.ProfileStatsCard
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.heroFont
import com.kumar.crackup.ui.theme.heroFontLargeSize
import com.kumar.crackup.viewmodel.MyViewModel
import com.kumar.crackup.UserModel
import com.kumar.crackup.templates.GradientButton
import com.kumar.crackup.templates.Selector
import com.kumar.crackup.templates.SubHeaderText
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.lightPink
import com.kumar.crackup.ui.theme.linearGradient1e
import com.kumar.crackup.util.PrefConstants
import com.kumar.crackup.util.PreferenceUtil

fun String.toInitials(): String {
    // Split by whitespace and ignore extra spaces
    val words = this.trim().split("\\s+".toRegex()).filter { it.isNotEmpty() }

    return when {
        words.isEmpty() -> ""
        // Case 1: First and Last name exist -> First char of each
        words.size >= 2 -> {
            "${words.first().first()}${words.last().first()}".uppercase()
        }
        // Case 2: Only 1 name exists -> Take first 2 characters
        else -> {
            words.first().take(2).uppercase()
        }
    }
}

@Composable
fun ProfileComposable(navHostController: NavHostController, myViewModel: MyViewModel) {

    val user by myViewModel.currentUser.collectAsStateWithLifecycle()

    ProfileContent(navHostController, user, onLogout = {
        myViewModel.logout(onSuccess = {
            PreferenceUtil.putBoolean(PrefConstants.IS_LOGGED_IN, false)
            PreferenceUtil.putBoolean(PrefConstants.SHOW_CONTACT, false)
            navHostController.navigate("login") {
                popUpTo(0)
            }
        }, onError = {})
    })
}

@Composable
fun ProfileContent(navHostController: NavHostController, user: UserModel?, onLogout: () -> Unit) {
    val stats = listOf(
        StatItem(
            stat = "-",
            description = "Tests Taken"
        ),
        StatItem(
            stat = "-",
            description = "Accuracy"
        ),
        StatItem(
            stat = "-",
            description = "Streak"
        ),

        )
    val emojiBoxSize = 40.dp
    val profileText = user?.name?.toInitials()
    user?.let {

        Box(
            modifier = Modifier
                .fillMaxSize()
//            .background(Color(0xffDCE3FA))
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp))
                            .background(shape = RoundedCornerShape(20.dp), color = lightBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = profileText!!,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = heroFontLargeSize,
                                fontFamily = heroFont,
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                    }
                }


                BodyText(
                    text = user?.name!!,
                    modifier = Modifier,
                    textColor = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textSize = bodyFontLargeSize
                )
                BodyText(text = user?.email.toString(), modifier = Modifier, textColor = Color.Gray)

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                ProfileStatsCard(
                    stats = stats, modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        HeaderText(
                            text = "Account",
                            modifier = Modifier.padding(10.dp),
                            textColor = Color.Black
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(emojiBoxSize)
                                    .background(
                                        color = lightBlue.copy(alpha = 0.3f),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                BodyText(
                                    text = "🎯", modifier = Modifier, textColor = Color.Gray,
                                    textSize = bodyFontLargeSize
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                SubHeaderText(text = "Target Exam", modifier = Modifier)
                                Selector(
                                    options = listOf(
                                        "Group 1",
                                        "Group 2",
                                        "Group 4"
                                    ),
                                    default = user?.exam.toString(), onOptionSelected = {

                                    })
                            }

                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(emojiBoxSize)
                                    .background(
                                        color = lightBlue.copy(alpha = 0.3f),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                BodyText(
                                    text = "💳", modifier = Modifier, textColor = Color.Gray,
                                    textSize = bodyFontLargeSize
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 10.dp)
                            ) {
                                SubHeaderText(text = "Subscription", modifier = Modifier)
                                BodyText(
                                    text = "Free plan", modifier = Modifier, textColor = Color.Gray,
                                    textSize = bodyFontTinySize
                                )

                            }
                            GradientButton(
                                onButtonClick = { navHostController.navigate("subscription") },
                                buttonText = "Upgrade",
                                textColor = Color.White,
                                gradientColors = linearGradient1e,
                                modifier = Modifier.weight(1f)
                            )
                        }

                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        HeaderText(
                            text = "Preferences",
                            modifier = Modifier.padding(10.dp),
                            textColor = Color.Black
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(emojiBoxSize)
                                    .background(
                                        color = Color.Blue.copy(alpha = 0.3f),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                BodyText(
                                    text = "🌐", modifier = Modifier, textColor = Color.Gray,
                                    textSize = bodyFontLargeSize
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                SubHeaderText(text = "Language", modifier = Modifier)
                                Selector(
                                    options = listOf(
                                        "English",
                                        "Tamil"
                                    ), default = "Language", onOptionSelected = {

                                    })
                            }
                        }

                    }
                }
                ColoredButton(
                    roundness = 10.dp,
                    onButtonClick = {
                        onLogout()
                    },
                    buttonText = "Logout",
                    textColor = Color.Red,
                    buttonColor = lightPink,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun ProfilePreview() {
    BaseAppTheme() {
//        ProfileContent(rememberNavController(), user)
    }
}