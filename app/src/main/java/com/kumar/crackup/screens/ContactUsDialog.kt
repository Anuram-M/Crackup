package com.kumar.crackup.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.R
import com.kumar.crackup.templates.GradientButton
import com.kumar.crackup.templates.OutlinedInputField
import com.kumar.crackup.ui.theme.*

@Composable
fun ContactUsDialog(onDismissRequest: () -> Unit) {
    var yourName by rememberSaveable() {
        mutableStateOf("")
    }
    var userEmail by rememberSaveable() {
        mutableStateOf("")
    }
    var mobileNumber by rememberSaveable() {
        mutableStateOf("")
    }
    var message by rememberSaveable() {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                        text = "Contact Us",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = lightBlue,
                            fontSize = bodyFontLargeSize,
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )
                    Spacer(modifier = Modifier.fillMaxWidth().height(14.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp), horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = "Phone",
                            style = TextStyle(
                                color = lightBlue,
                                fontSize = bodyFontSize,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Normal
                            ),
                            textAlign = TextAlign.Center,

                        )
                        Text(
                            text = " / ",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = bodyFontSize,
                                fontWeight = FontWeight.Normal
                            ),
                            textAlign = TextAlign.Center,

                        )
                        Text(
                            text = "WhatsApp",
                            style = TextStyle(
                                color = lightBlue,
                                fontSize = bodyFontSize,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Normal
                            ),
                            textAlign = TextAlign.Center,

                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "+91 93617 76015",
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
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Email: support@crackupacademy.in",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = lightBlue,
                            textDecoration = TextDecoration.Underline,
                            fontSize = bodyFontSize,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )
                    Spacer(modifier = Modifier.fillMaxWidth().height(14.dp))
                    OutlinedInputField(
                        keyboardType = KeyboardType.Email,
                        label = "Full Name",
                        placeHolder = "Full Name",
                        textValue = yourName
                    ) { newText ->
                        yourName = newText
                    }
                    OutlinedInputField(
                        keyboardType = KeyboardType.Email,
                        label = "Email",
                        placeHolder = "Enter Email",
                        textValue = userEmail
                    ) { newText ->
                        userEmail = newText
                    }
                    OutlinedInputField(
                        keyboardType = KeyboardType.Email,
                        label = "Mobile Number",
                        placeHolder = "Mobile Number",
                        textValue = mobileNumber
                    ) { newText ->
                        mobileNumber = newText
                    }
                    OutlinedInputField(
                        keyboardType = KeyboardType.Email,
                        label = "Message",
                        placeHolder = "Message here",
                        modifier = Modifier.height(120.dp),
                        textValue = message
                    ) { newText ->
                        message = newText
                    }
                    GradientButton(
                        buttonText = "Send via WhatsApp",
                        textColor = Color.White,
                        gradientColors = linearGradient2,
                        onButtonClick = {
//                            myViewModel.signIn(userEmail, password)
                        }
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