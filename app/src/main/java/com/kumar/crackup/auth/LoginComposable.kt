package com.kumar.crackup.auth

//import com.kumar.crackup.util.backupQuestions
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kumar.crackup.components.BottomSheet
import com.kumar.crackup.templates.GradientButton
import com.kumar.crackup.templates.OutlinedInputField
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontLargeSize
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.bodyFontSmallSize
import com.kumar.crackup.ui.theme.heroFont
import com.kumar.crackup.ui.theme.heroFontLargeSize
import com.kumar.crackup.ui.theme.heroFontSize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.linearGradient1
import com.kumar.crackup.ui.theme.linearGradient2
import com.kumar.crackup.ui.theme.textBlue
import com.kumar.crackup.util.PrefConstants
import com.kumar.crackup.util.PreferenceUtil
import com.kumar.crackup.viewmodel.MyViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginComposable(navHostController: NavHostController, myViewModel: MyViewModel) {
    var userEmail by rememberSaveable() {
        mutableStateOf("")
    }
    var password by rememberSaveable() {
        mutableStateOf("")
    }
    val config = LocalConfiguration.current
    val screenWidthDp = remember {
        config.screenWidthDp.dp
    }
    val screenHeightDp = remember {
        config.screenHeightDp.dp
    }

    val contentModifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)

//    val auth by myViewModel.authState.collectAsStateWithLifecycle()
    val context = LocalContext.current.applicationContext

    var userEmailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    fun attemptSignIn(context: Context, viewModel: MyViewModel, navHostController: NavHostController) {
        val input = SignInFormInput(
            userEmail = userEmail,
            password = password,
        )

        val errors = AuthValidator.validateSignIn(input)

        userEmailError = errors.userEmail
        passwordError = errors.password


        // Only proceed if every field passed. The validator is the single
        // gatekeeper here — the screen never decides validity on its own.
        if (errors.isValid) {
            Toast.makeText(context, "All fields are valid", Toast.LENGTH_SHORT).show()
            viewModel.signin(
                input, onSuccess = {
                    PreferenceUtil.putBoolean(PrefConstants.IS_LOGGED_IN, true)
                    PreferenceUtil.putBoolean(PrefConstants.SHOW_CONTACT, true)
                    navHostController.navigate("home") {
                        popUpTo(0)
                    }
                },
                onError = {

                })
        }
    }

    var sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var showSheet by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var exportedPath by remember { mutableStateOf<String?>(null) }
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
//        when(auth) {
//            is AuthState.Loading -> {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    CircularProgressIndicator()
//                }
//            }
//            is AuthState.Error ->  {
//                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
//            }
//            is AuthState.Idle -> {
            Card (
                modifier = Modifier
//                .fillMaxSize()
                    .padding(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                if(screenHeightDp > screenWidthDp)
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .verticalScroll(rememberScrollState())) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    shape = RoundedCornerShape(
                                        bottomStart = 0.dp, bottomEnd = 0.dp,
                                    ), brush = Brush.linearGradient(colors = linearGradient1,)
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 40.dp)
                            ) {
                                Text(
                                    text = "CrackUp",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = heroFontLargeSize,
                                        fontFamily = heroFont,
                                        letterSpacing = 8.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                                Spacer(
                                    modifier = Modifier.height(5.dp)
                                )
                                Text(
                                    text = "Smart TNPSC Preparation Platform📘",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = bodyFontSmallSize,
                                        fontWeight = FontWeight.Normal,
                                    )
                                )
                                Spacer(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(24.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 40.dp),
                                ) {
                                    Text(
                                        text = "✅ 10,000+ MCQs",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = bodyFontSmallSize,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                    Spacer(
                                        modifier = Modifier.height(5.dp)
                                    )
                                    Text(
                                        text = "✅ Daily Current Affairs",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = bodyFontSmallSize,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                    Spacer(
                                        modifier = Modifier.height(5.dp)
                                    )
                                    Text(
                                        text = "✅ Previous Year Questions",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = bodyFontSmallSize,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                    Spacer(
                                        modifier = Modifier.height(5.dp)
                                    )
                                    Text(
                                        text = "✅ Full Mock Tests",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = bodyFontSmallSize,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                }

                            }
                        }
                        Text(
                            text = "Student Login",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = lightBlue,
                                fontSize = bodyFontLargeSize,
                                letterSpacing = 2.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        )
                        OutlinedInputField(
                            keyboardType = KeyboardType.Email,
                            label = "Email",
                            placeHolder = "Enter Email",
                            textValue = userEmail,
                            errorField = userEmailError,
                            modifier = contentModifier
                        ) { newText ->
                            userEmail = newText
                            if(userEmailError != null) userEmailError = null
                        }
                        OutlinedInputField(
                            keyboardType = KeyboardType.Password,
                            label = "Password",
                            isPassword = true,
                            placeHolder = "Enter Password",
                            textValue = password,
                            errorField = passwordError,
                            modifier = contentModifier
                        ) { newText ->
                            password = newText
                            if(passwordError != null) passwordError = null
                        }

                        GradientButton(
                            buttonText = "Login",
                            textColor = Color.White,
                            gradientColors = linearGradient2,
                            onButtonClick = {
                                Log.d("CRACK", "LoginComposable: check")
//                                if(userEmail.isNotEmpty() && password.isNotEmpty()) {
////                                showSheet = true
//                                FirestoreContent.subTopics.forEach {
//                                    FirebaseUtil.putSubTopics(it)
//                                }
//                                FirebaseUtil.putExams(
//                                    Exam()
//                                )
                                    attemptSignIn(context, myViewModel, navHostController)
//                                }
                            }
                        )

//                        Button(onClick = {
//                            coroutineScope.launch {
////                                val file = FirebaseUtil.exportQuestionsToLocalFile(
////                                    context,
////                                    FirebaseFirestore.getInstance()
////                                )
//////                                exportedPath = file.absolutePath
////                                val file = File(
////                                    context.getExternalFilesDir(null),
////                                    "exports/questions_export.json"
////                                )
//// val questions: List<Question> = FirebaseUtil.readQuestionsFromJsonFile(file)
////                                Log.d("FIRELET", "LoginComposable: ${questions}")
////                                val file3 = File(context.getExternalFilesDir(null), "exports/newquestions_readable.kt")
////                                val list = JSONToKotlinConverter.convertToNewModel()
////                                Log.d("FIRELET", "LoginComposable: ${list}")
////                                file3.writeText(list.toString())
//
////                                FirestoreContent.tamilUnits.forEach {
////                                    FirebaseUtil.putTamil(it)
////                                }
////
////                                // 1. Read JSON file directly into your model list
////                                val jsonString = File(context.getExternalFilesDir(null), "exports/questions_export.json").readText()
////                                val questions: List<Question> = Gson().fromJson(jsonString, object : TypeToken<List<Question>>() {}.type)
////
////                                questions.forEach {
//////                                    Log.d("FIRELET", "LoginComposable: ${it.toNewQuestion()}")
////                                    FirebaseUtil.putNewQuestion(it.toNewQuestion())
////                                }
////// 2. Upload to Firestore in one quick loop
////                                val collection = FirebaseFirestore.getInstance().collection("aptitude_questions")
//                                subTopics.forEach {
//                                    FirebaseUtil.putSubTopics(it)
//                                }
////                                     val jsonFile = File(context.getExternalFilesDir(null), "exports/questions_export.json")
////                                     val kotlinFile = JSONToKotlinConverter.convertJsonFileToKotlinFile(context, jsonFile, outputFileName = "NewQuestionsEditable.kt")
//
////                                val list = backupQuestions
////                                list.forEach {
////                                    FirebaseUtil.putNewQuestion(it)
////                                }
//     //
//                            }
//                        }) {
//                            Text("Export Questions")
//                        }
//                        exportedPath?.let { Text("Saved to: $it") }

                        Text(
                            text = "Forgot Password?",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                textDecoration = TextDecoration.Underline,
                                color = textBlue,
                                fontSize = bodyFontSize,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Row() {
                            Text(
                                text = "New Student? ",
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    textDecoration = TextDecoration.None,
                                    color = Color.Black,
                                    fontSize = bodyFontSize,
                                    fontWeight = FontWeight.Normal
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp)
                            )
                            Text(
                                text = "Create Account",
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = textBlue,
                                    fontSize = bodyFontSize,
                                    fontWeight = FontWeight.Normal
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 10.dp)
                                    .clickable {
                                        navHostController.navigate("signup")
                                    },
                            )
                        }
                    } else
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(
                                    shape = RoundedCornerShape(
//                            topStart = 20.dp, topEnd = 20.dp,
                                        bottomStart = 0.dp, bottomEnd = 0.dp,
                                    ), brush = Brush.linearGradient(colors = linearGradient1,)
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                        top = 20.dp,
                                        bottom = 20.dp
                                    )
                            ) {
                                Text(
                                    text = "Crackup Academy",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = heroFontSize,
                                        fontFamily = heroFont,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                                Spacer(
                                    modifier = Modifier.height(5.dp)
                                )
                                Text(
                                    text = "Smart TNPSC Preparation Platform📘",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = bodyFontSmallSize,
                                        fontFamily = bodyFont,
                                        fontWeight = FontWeight.Normal,
                                    )
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                Column(
                                    modifier = Modifier.padding(start = 20.dp)
                                ) {
                                    Text(
                                        text = "✅ 10,000+ MCQs",
//                                textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = bodyFontSmallSize,
                                            fontFamily = bodyFont,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                    Spacer(
                                        modifier = Modifier.height(5.dp)
                                    )
                                    Text(
                                        text = "✅ Daily Current Affairs",
//                                textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = bodyFontSmallSize,
                                            fontFamily = bodyFont,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                    Spacer(
                                        modifier = Modifier.height(5.dp)
                                    )
                                    Text(
                                        text = "✅ Previous Year Questions",
//                                textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = bodyFontSmallSize,
                                            fontFamily = bodyFont,
                                            fontWeight = FontWeight.Bold
                                        ),
//                                modifier = Modifier.fillMaxSize()
                                    )
                                    Spacer(
                                        modifier = Modifier.height(5.dp)
                                    )
                                    Text(
                                        text = "✅ Full Mock Tests",
//                                textAlign = TextAlign.Center,
                                        style = TextStyle(
                                            color = Color.White,
                                            fontFamily = bodyFont,
                                            fontSize = bodyFontSmallSize,
                                            fontWeight = FontWeight.Bold
                                        ),
//                                modifier = Modifier.fillMaxSize()
                                    )
                                }

                            }
                        }
                        Column(modifier = Modifier
                            .fillMaxHeight()
                            .padding(bottom = 5.dp)
                            .verticalScroll(rememberScrollState())) {
                            Text(
                                text = "Student Login",
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    color = lightBlue,
                                    fontFamily = bodyFont,
                                    fontSize = bodyFontSize,
                                    letterSpacing = 2.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            )
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
                                label = "Password",
                                placeHolder = "Enter Password",
                                textValue = password
                            ) { newText ->
                                password = newText
                            }

                            GradientButton(
                                buttonText = "Login",
                                textColor = Color.White,
                                gradientColors = linearGradient2,
                                onButtonClick = {
                                    Log.d("CRACK", "LoginComposable: check")
                                    if(userEmail.isNotEmpty() && password.isNotEmpty()) {
                                        attemptSignIn(context, myViewModel, navHostController)
                                    }
                                }
                            )

                            Text(
                                text = "Forgot Password?",
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = textBlue,
                                    fontFamily = bodyFont,
                                    fontSize = bodyFontSize,
                                    fontWeight = FontWeight.Normal
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Row() {
                                Text(
                                    text = "New Student? ",
                                    textAlign = TextAlign.End,
                                    style = TextStyle(
                                        textDecoration = TextDecoration.None,
                                        color = Color.Black,
                                        fontSize = bodyFontSize,
                                        fontFamily = bodyFont,
                                        fontWeight = FontWeight.Normal
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 10.dp)
                                )
                                Text(
                                    text = "Create Account",
                                    textAlign = TextAlign.Start,
                                    style = TextStyle(
                                        textDecoration = TextDecoration.Underline,
                                        color = textBlue,
                                        fontSize = bodyFontSize,
                                        fontFamily = bodyFont,
                                        fontWeight = FontWeight.Normal
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 10.dp)
                                        .clickable {
                                            navHostController.navigate("signup")
                                        },
                                )
                            }
                        }
                    }

//            }
//            }
//            AuthState.Success -> navHostController.navigate("home")


                if(showSheet) {
                    BottomSheet(sheetState = sheetState, onClose = {}) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .background(lightBlue)
                        ) {
                            Button(onClick = {
                                coroutineScope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showSheet = false
                                    }
                                }
                            }) { }
                        }
                    }
                }
        }



    }
}