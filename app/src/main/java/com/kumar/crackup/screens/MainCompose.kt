package com.kumar.crackup.screens

import android.app.Activity
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kumar.crackup.R
import com.kumar.crackup.components.AppBottomNavigation
import com.kumar.crackup.components.CustomBottomNavigation
import com.kumar.crackup.model.SubTopic
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.SmallWidthSpacer
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.bodyFontTinySize
import com.kumar.crackup.ui.theme.lightBlue
import com.kumar.crackup.ui.theme.screenBackground
import com.kumar.crackup.ui.theme.textBlack
import com.kumar.crackup.util.NetworkStatus
import com.kumar.crackup.util.PreferenceUtil
import com.kumar.crackup.viewmodel.MyViewModel
import kotlinx.coroutines.launch
import kotlin.collections.contains

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCompose(myViewModel: MyViewModel = hiltViewModel()) {
    val view = LocalView.current
    val preferredColor = Color(0xFFF4F6F9) // Replace with your custom brand color

    SideEffect {
        val window = (view.context as? Activity)?.window
        if (window != null) {
            val insetsController = WindowCompat.getInsetsController(window, view)

            // 1. Convert your Compose Color to an Android ARGB Int and apply it
            window.navigationBarColor = preferredColor.toArgb()

            // 2. Clear out enforced overlays (Crucial for Android 10+ / API 29+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }

            // 3. Keep your existing logic to ensure the buttons stay dark/visible
            insetsController.isAppearanceLightNavigationBars = true
            insetsController.isAppearanceLightStatusBars = true
        }
    }

    val starting = "home"
    val navController = rememberNavController()
    val navState by navController.currentBackStackEntryAsState()
    val context = LocalContext.current.applicationContext
    val currentRoute = navState?.destination?.route
    val coroutineScope = rememberCoroutineScope()
    val startDestination = if(PreferenceUtil.getBoolean("isLoggedIn") == true)  "home"
    else if(!PreferenceUtil.getBoolean("isOnBoardingComplete")) "onboard"
    else "login"
    val impScreens = listOf("home", "currentaffairs", "progress", "profile")
    val authScreens = listOf("login", "signup", "onboard")
    val shouldShowBottomBar = currentRoute in impScreens
    val shouldHideTopBar = currentRoute in authScreens
    val screenTitle = when (currentRoute) {
        "home" -> "Home"
        "profile" -> "User Profile"
        "faq" -> "FAQs"
        "subscription" -> "Paid Services"
        "currentaffairs" -> "Current Affairs"
        "about" -> "About Us"
        "progress" -> "Progress"
        "practice" -> "Practice"
        "test" -> "Quiz"
        "EXAM_YEAR" , "Years" , "Units", "tamilunitsSub", "subTopics" -> "Select"
        else -> "Dummy"
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val networkStatus by myViewModel.networkStatus.collectAsStateWithLifecycle()
    val isOffline = networkStatus == NetworkStatus.Unavailable
    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.background(Color.Red),
        gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .widthIn(min = 200.dp, max = 250.dp)
                    .systemBarsPadding()
                    .background(Color.Transparent),
                drawerContainerColor = screenBackground
            ) {
//                Text("Learning Platform Utilities", modifier = Modifier.padding(unifyPadding))
                Box() {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            Image(
                                painter = painterResource(R.drawable.app_logo_color),
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .shadow(elevation = 5.dp)
//                        .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .background(color = Color.White)
                                    .height(180.dp)
                                    .padding(10.dp),
                                contentDescription = null
                            )
                            NavigationDrawerItem(icon = {
                                Icon(
                                    painter = painterResource(R.drawable.subscription),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Black
                                )
                            }, label = {
                                Text(
                                    "Subscriptions",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = bodyFontSize,
                                        fontWeight = FontWeight.Medium
                                    ),
                                )
                            }, selected = false, onClick = {
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                navController.navigate("subscription")
                            })
                            NavigationDrawerItem(icon = {
                                Icon(
                                    painter = painterResource(R.drawable.faq),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Black
                                )
                            },label = {
                                Text(
                                    "FAQs",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = bodyFontSize,
                                        fontWeight = FontWeight.Medium
                                    ),
                                )
                            }, selected = false, onClick = {
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                navController.navigate("faq")
                            })
                            NavigationDrawerItem(icon = {
                                Icon(
                                    painter = painterResource(R.drawable.caontact_us),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Black
                                )
                            },label = {
                                Text(
                                    "Contact Us",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = bodyFontSize,
                                        fontWeight = FontWeight.Medium
                                    ),
                                )
                            }, selected = false, onClick = {})
                            NavigationDrawerItem(icon = {
                                Icon(
                                    painter = painterResource(R.drawable.about_us),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Black
                                )
                            },label = {
                                Text(
                                    "About Us",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = bodyFontSize,
                                        fontFamily = bodyFont,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                )
                            }, selected = false, onClick = {
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                                navController.navigate("about") {
                                    popUpTo("home")
                                }
                            })
                        }

                        Text(
                            text = "Version - 1.0",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = bodyFontSize,
                                fontWeight = FontWeight.Medium
                            ),
                        )
                    }

                    Icon(painter = painterResource(R.drawable.close2), contentDescription = null, modifier = Modifier
                        .align(
                            Alignment.TopEnd
                        )
                        .size(32.dp)
                        .padding(4.dp)
                        .background(Color.Black, shape = CircleShape)
                        .padding(4.dp)
                        .clickable {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }, tint = Color.White)
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                Column() {
                    if (!shouldHideTopBar)
                        TopAppBar(
                            modifier = Modifier.shadow(elevation = 4.dp),
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = lightBlue,
                                titleContentColor = Color.White
                            ),
                            navigationIcon = {
                                if (shouldShowBottomBar)
                                    IconButton(
                                        onClick = {
                                            coroutineScope.launch {
                                                drawerState.open()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.menu),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(40.dp)
                                                .padding(6.dp),
                                            tint = Color.White
                                        )
                                    }
                                else
                                    IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.back_arrow),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(40.dp)
                                                .padding(8.dp),
                                            tint = Color.White
                                        )
                                    }

                            },
                            title = {
                                Image(painter = painterResource(R.drawable.logo),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxWidth().height(TopAppBarDefaults.MediumAppBarCollapsedHeight).padding(10.dp))
//                                Text(
//                                    text = screenTitle,
//                                    style = TextStyle(
//                                        fontFamily = bodyFont,
//                                        fontSize = 24.sp,
//                                        fontWeight = FontWeight.SemiBold
//                                    )
//                                )
                            },

                            actions = {
                                IconButton(onClick = {

                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Notifications,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
//
                        )

                    AnimatedVisibility(
                        visible = isOffline,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Red.copy(alpha = 0.7f))
                                .padding(5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(R.drawable.offline),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                SmallWidthSpacer()
                                BodyText(
                                    text = "Offline",
                                    textSize = bodyFontTinySize,
                                    textColor = textBlack
                                )
                            }
                        }
                    }
                }

            },

            containerColor = screenBackground,
            bottomBar = {
                if (shouldShowBottomBar)
                    CustomBottomNavigation { newRoute -> navController.navigate(newRoute) }
//                    AppBottomNavigation { newRoute -> navController.navigate(newRoute) }
            },
            floatingActionButton = {}
        ) { innerPadding ->

            NavHost(
                navController = navController, startDestination = startDestination,
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(durationMillis = 400)
                    )
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth -> -fullWidth },
                        animationSpec = tween(durationMillis = 400)
                    )
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth -> -fullWidth },
                        animationSpec = tween(durationMillis = 400)
                    )
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(durationMillis = 400)
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {  ->
                composable("onboard") { OnboardingScreen(navController, myViewModel) }
                composable("home") { HomeComposable(navController, myViewModel) }
                composable("login") { LoginComposable(navController, myViewModel) }
                composable("signup") { SignupComposable(navController, myViewModel) }
                composable("subscription") { SubscriptionComposable() }
                composable("profile") { ProfileComposable(navController, myViewModel) } //need to be reset once the practice screen is completed
                composable("about") { AboutComposable() }
//                composable("sample") { DummyComposable() }
                composable("faq") { FaqComposable() }
                composable("practice") { PracticeIntroSection(navController, myViewModel) }
//                composable("video") { VideoSection(lifecycleOwner) }
                composable("progress") { ProgressComposable(navHostController = navController) }
                composable("test") { Test(navController, myViewModel) }
                composable("EXAM_YEAR") { ExamsScreen(navController, myViewModel) }
                composable("Years") { YearsScreen(navController, myViewModel) }
                composable("Units") { TamilUnitsScreen(navController, myViewModel) }
                composable("tamilunitsSub") { UnitSubTopicScreen(navController, myViewModel) }
                composable("subTopics") { SubTopicsScreen(navController, myViewModel) }
                composable("currentaffairs") { CurrentAffairsScreen(navController, myViewModel) }
            }
        }
    }
}