package com.kumar.crackup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kumar.crackup.splash.SplashScreen
import com.kumar.crackup.templates.LoadingOverlay
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.screenBackground
import com.kumar.crackup.util.LoadingManager
import com.kumar.crackup.util.PreferenceUtil
import com.kumar.crackup.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val myViewModel: MyViewModel by viewModels()

    @Inject
    lateinit var loadingManager: LoadingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PreferenceUtil.init(this)
        setContent {
            BaseAppTheme {
                val isLoading by loadingManager.isLoading.collectAsStateWithLifecycle()
                var showSplash by remember { mutableStateOf(true) }

                if (showSplash) {
                    SplashScreen(onFinished = { showSplash = false })
                } else {

                    Box(modifier = Modifier.fillMaxSize().background(screenBackground)) {
                        MainCompose()
                        LoadingOverlay(isLoading = isLoading)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaseAppTheme {
        Greeting("Android")
    }
}