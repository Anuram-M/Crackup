package com.kumar.crackup.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kumar.crackup.templates.DropDownSelector
import com.kumar.crackup.templates.GradientButton
import com.kumar.crackup.templates.OutlinedInputField
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.linearGradient2
import com.kumar.crackup.ui.theme.textBlue
import com.kumar.crackup.util.CreateAccountFormInput
import com.kumar.crackup.util.AuthValidator
import com.kumar.crackup.util.PasswordStrength
import com.kumar.crackup.util.PreferenceUtil
import com.kumar.crackup.viewmodel.MyViewModel

@Composable
fun SignupComposable(navHostController: NavHostController, myViewModel: MyViewModel) {
    var fullName by rememberSaveable() {
        mutableStateOf("")
    }
    var userEmail by rememberSaveable() {
        mutableStateOf("")
    }
    var mobileNumber by rememberSaveable() {
        mutableStateOf("")
    }
    var exam by rememberSaveable() {
        mutableStateOf("")
    }
    var district by rememberSaveable() {
        mutableStateOf("")
    }
    var password by rememberSaveable() {
        mutableStateOf("")
    }
    var confirmPassword by rememberSaveable() {
        mutableStateOf("")
    }
    val contentModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)

    val showPassword = remember {

        mutableStateOf(false)
    }
    val showConfirmPassword = remember {

        mutableStateOf(false)
    }
    var charStrength = PasswordStrength.characterStrength.collectAsStateWithLifecycle()

    var fullNameError by remember { mutableStateOf<String?>(null) }
    var userEmailError by remember { mutableStateOf<String?>(null) }
    var mobileNumberError by remember { mutableStateOf<String?>(null) }
    var examError by remember { mutableStateOf<String?>(null) }
    var districtError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current.applicationContext
    fun attemptCreateAccount(context: Context, viewModel: MyViewModel, navHostController: NavHostController) {
        val input = CreateAccountFormInput(
            fullName = fullName,
            userEmail = userEmail,
            mobileNumber = mobileNumber,
            exam = exam,
            district = district,
            password = password,
            confirmPassword = confirmPassword
        )

        val errors = AuthValidator.validateSignUp(input)

        fullNameError = errors.fullName
        userEmailError = errors.userEmail
        mobileNumberError = errors.mobileNumber
        examError = errors.exam
        districtError = errors.district
        passwordError = errors.password
        confirmPasswordError = errors.confirmPassword


        // Only proceed if every field passed. The validator is the single
        // gatekeeper here — the screen never decides validity on its own.
        if (errors.isValid) {
            Toast.makeText(context, "All fields are valid", Toast.LENGTH_SHORT).show()
            viewModel.signup(
                input, onSuccess = {
                    PreferenceUtil.putBoolean("isLoggedIn", true)
                    navHostController.navigate("home") {
                        popUpTo(0)
                    }
                },
                onError = {

                })
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center) {
        Card (
            modifier = Modifier
                .padding(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
//                .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Create Account",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = lightBlue,
                        fontSize = bodyFontLargeSize,
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                OutlinedInputField(
                    keyboardType = KeyboardType.Email,
                    label = "Full Name",
                    placeHolder = "Full Name",
                    textValue = fullName,
                    errorField = fullNameError,
                    modifier = contentModifier
                ) { newText ->
                    fullName = newText
                    if (fullNameError != null) fullNameError = null // need to clear on edit
                }
                OutlinedInputField(
                    keyboardType = KeyboardType.Email,
                    label = "Email",
                    placeHolder = "Enter Email",
                    textValue = userEmail,
                    errorField = userEmailError,
                    modifier = contentModifier
                ) { newText ->
                    userEmail = newText
                    if (userEmailError != null) userEmailError = null // clear on edit

                }
                OutlinedInputField(
                    keyboardType = KeyboardType.Number,
                    label = "Mobile Number",
                    placeHolder = "Mobile Number",
                    textValue = mobileNumber,
                    maxCharCount = 10,
                    errorField = mobileNumberError,
                    modifier = contentModifier
                ) { newText ->
                    mobileNumber = newText
                    if (mobileNumberError != null) mobileNumberError = null // clear on edit

                }
                DropDownSelector(options = listOf(
                    "Group 1",
                    "Group 2",
                    "Group 4",
                ), label = "Select Target",
                    errorField = examError,
                    onOptionSelected = {
                      exam = it
                }, resetError = {
                    examError = null
                })

                DropDownSelector(options = listOf(
                    "Ariyalur",
                    "Chengalpattu",
                    "Chennai",
                    "Coimbatore",
                    "Cuddalore",
                    "Dharmapuri",
                    "Dindigul",
                    "Erode",
                    "Kallakurichi",
                    "Kancheepuram",
                    "Kanyakumari",
                    "Karur",
                    "Krishnagiri",
                    "Madurai",
                    "Mayiladuthurai",
                    "Nagapattinam",
                    "Namakal",
                    "Nilgiris",
                    "Perambalur",
                    "Pudukottai",
                    "Ramanathapuram",
                    "Ranipet",
                    "Salem",
                    "Sivagangai",
                    "Tenkasi",
                    "Thanjavur",
                    "Theni",
                    "Thiruchirappalli",
                    "Thirupathur",
                    "Thiruvarur",
                    "Thoothukkudi",
                    "Tirunelvali",
                    "Tiruppur",
                    "Tiruvallur",
                    "Tiruvannamalai",
                    "Vellore",
                    "Villuppuram",
                ), label = "Select District",
                    errorField = districtError,
                    onOptionSelected = {
                      district = it
                }, resetError = {
                    districtError = null
                })


                //passw
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = contentModifier,
                    shape = RoundedCornerShape(20.dp),
                    value = password,
                    onValueChange = {
                        password = it
                        PasswordStrength.checkCharacterStrength(it)
                        if (passwordError != null) passwordError = null // clear on edit

                    },

                    isError = passwordError != null,
                    supportingText = { passwordError?.let { Text(it) } },
                    label = {
                        Text(
                            text = "Password",
                            style = TextStyle(
                                color = Color.Black,
                                fontFamily = bodyFont,
                            )
                        )
                    },

                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(10.dp).background(charStrength.value, shape = CircleShape))
                            IconButton(
                                onClick = {
                                    showPassword.value = !showPassword.value
                                    Log.d("SHOWHOW", "SignupComposable: ${showPassword}")
                                }
                            ) {
                                if(showPassword.value) {
                                    Text(
                                        text = "👁️"
                                    )
                                } else {
                                    Text(
                                        text = "🙈"
                                    )
                                }
                            }
                        }

                    },
                    visualTransformation = if(!showPassword.value) PasswordVisualTransformation() else VisualTransformation.None,
                    textStyle = TextStyle(
                        fontSize = bodyFontSmallSize,
                        color = Color.Black,
                        fontFamily = bodyFont,
                    ),
                    placeholder = {
                        Text(
                            text = "Password",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontFamily = bodyFont,
                            )
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray,
                        errorContainerColor = Color.White,
                        errorIndicatorColor = Color.Red
                    ),

                )

                //confirmpag
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = contentModifier,
                    shape = RoundedCornerShape(20.dp),
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        if (confirmPasswordError != null) confirmPasswordError = null // clear on edit

                    },
                    label = {
                        Text(
                            text = "Confirm Password",
                            style = TextStyle(
                                color = Color.Black,
                                fontFamily = bodyFont,
                            )
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = bodyFontSmallSize,
                        color = Color.Black,
                        fontFamily = bodyFont,
                    ),
                    isError = confirmPasswordError != null,
                    supportingText = { confirmPasswordError?.let { Text(it) } },
                    placeholder = {
                        Text(
                            text = "Confirm Password",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontFamily = bodyFont,
                            )
                        )
                    },
                    trailingIcon =
                        {
                            IconButton(
                                onClick = {
                                    showConfirmPassword.value = !showConfirmPassword.value
                                    Log.d("SHOWHOW", "SignupComposable: ${showConfirmPassword}")
                                }
                            ) {
                                if (showConfirmPassword.value) {
                                    Text(
                                        text = "👁️"
                                    )
                                } else {
                                    Text(
                                        text = "🙈"
                                    )
                                }
                            }
                        },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.LightGray,
                        errorContainerColor = Color.White,
                        errorIndicatorColor = Color.Red
                    )
                )

                GradientButton(
                    buttonText = "Create Account",
                    gradientColors = linearGradient2,
                    textColor = Color.White,
                    onButtonClick = {
                        attemptCreateAccount(context, myViewModel, navHostController)
                    }
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "Already have an account? ",
                        textAlign = TextAlign.End,
                        style = TextStyle(
                            textDecoration = TextDecoration.None,
                            color = Color.Black,
                            fontSize = bodyFontSize,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = "Login",
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            color = textBlue,
                            fontSize = bodyFontSize,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable {
                                navHostController.navigate("login") {
                                    popUpTo(0)
                                }
                            },
                    )
                }
            }


        }
    }
}