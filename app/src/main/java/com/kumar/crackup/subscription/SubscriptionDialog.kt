package com.kumar.crackup.subscription

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kumar.crackup.R
import com.kumar.crackup.model.ServiceModel
import com.kumar.crackup.templates.GradientButton
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.dialogCardColor
import com.kumar.crackup.ui.theme.heroFontSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.linearGradient2
import com.kumar.crackup.ui.theme.textBlue

@Composable
fun SubscriptionDialog(onDismissRequest: () -> Unit) {

    val config = LocalConfiguration.current
    val screenWidthDp = remember {
        config.screenWidthDp.dp
    }

    val servicesList = listOf(
        ServiceModel(
            title = "🔥 Full Package",
            category = "Complete TNPSC preparation bundle",
            includedFeatures = listOf(),
            price = "₹ 1,499"
        ),
        ServiceModel(
            title = "📘 General Tamil / English",
            category = "Grammar, vocabulary & practice tests",
            includedFeatures = listOf(),
            price = "₹ 499"
        ),
        ServiceModel(
            title = "🌍 General Studies",
            category = "History, Geography, Polity & Science",
            includedFeatures = listOf(),
            price = "₹ 499"
        ),
        ServiceModel(
            title = "🧠 Aptitude & Reasoning",
            category = "Mental ability, reasoning & shortcut methods",
            includedFeatures = listOf(),
            price = "₹ 499"
        ),
        ServiceModel(
            title = "📄 PYQ Package",
            category = "Previous year TNPSC questions with explanations",
            includedFeatures = listOf(),
            price = "₹ 299"
        ),

        )

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .safeContentPadding()
            .background(Color.Gray.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Box() {

            Card(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 44.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🔥 TNPSC Special Offers",
                        style = TextStyle(
                            color = textBlue,
                            fontSize = bodyFontLargeSize,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Text(
                        text = "Start your TNPSC preparation with premium practice packages",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = bodyFontSmallSize,
                            fontWeight = FontWeight.Normal
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        maxItemsInEachRow = 2,
                        horizontalArrangement = Arrangement.Center,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {//.padding(horizontal = 20.dp, vertical = 10.dp)
                        Box(modifier = Modifier.padding(horizontal = 5.dp, vertical = 4.dp)) {

                            Box(
                                modifier = Modifier
                                    .background(
                                        color = dialogCardColor,
                                        shape = RoundedCornerShape(8)
                                    )
                                    .border(
                                        color = lightBlue,
                                        width = 1.dp,
                                        shape = RoundedCornerShape(8)
                                    )
                                    .fillMaxWidth()
                                    .heightIn(min = 120.dp, max = 140.dp)
                            ) {
                                Column(
                                    modifier = Modifier.matchParentSize(),
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = servicesList[0].title,
                                        style = TextStyle(
                                            color = textBlue,
                                            fontFamily = bodyFont,
                                            fontSize = bodyFontLargeSize,
                                            fontWeight = FontWeight.Normal
                                        ),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Text(
                                        text = servicesList[0].category,
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontFamily = bodyFont,
                                            fontSize = bodyFontTinySize,
                                            fontWeight = FontWeight.Normal
                                        ),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
//                                Spacer(
//                                    modifier = Modifier.fillMaxWidth().height(20.dp)
//                                )
                                    Text(
                                        text = servicesList[0].price,
                                        style = TextStyle(
                                            color = textBlue,
                                            fontFamily = bodyFont,
                                            fontSize = heroFontSize,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                }
                            }
                        }
                        servicesList.subList(1, servicesList.size).forEach {
                            Box(modifier = Modifier.padding(2.dp)) {

                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = dialogCardColor,
                                            shape = RoundedCornerShape(8)
                                        )
                                        .border(
                                            color = lightBlue,
                                            width = 1.dp,
                                            shape = RoundedCornerShape(8)
                                        )
                                        .heightIn(min = 120.dp, max = 140.dp)
                                        .widthIn(min = (screenWidthDp / 2) - 34.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.matchParentSize(),
                                        verticalArrangement = Arrangement.SpaceBetween,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Text(
                                            text = it.title,
                                            style = TextStyle(
                                                fontFamily = bodyFont,
                                                color = textBlue,
                                                fontSize = bodyFontTinySize,
                                                fontWeight = FontWeight.Normal
                                            ),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth().padding(start = 5.dp, top = 5.dp, end = 5.dp)
                                        )
                                        Text(
                                            text = it.category,
                                            style = TextStyle(
                                                color = Color.Black,
                                                fontFamily = bodyFont,
                                                fontSize = bodyFontTinySize,
                                                fontWeight = FontWeight.Normal
                                            ),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
                                        )
                                        Text(
                                            text = it.price,
                                            style = TextStyle(
                                                fontFamily = bodyFont,
                                                color = textBlue,
                                                fontSize = bodyFontLargeSize,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth().padding(start = 5.dp, bottom = 5.dp, end = 5.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    GradientButton(
                        buttonText = "Start Preparation",
                        textColor = Color.White,
                        gradientColors = linearGradient2,
                        onButtonClick = {}
                    )
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 44.dp, horizontal = 18.dp)
            ) {
                IconButton(onClick = {
                    onDismissRequest()
                },
                    modifier = Modifier.align(Alignment.TopEnd)) {

                    Icon(
                        painter = painterResource(R.drawable.close2),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp).padding(8.dp)

                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun previewDialog() {
    BaseAppTheme {
        SubscriptionDialog({})
    }
}