package com.kumar.crackup.home

import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun YouTubePlayerDialog(videoId: String, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .background(Color.Black)
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    val container = FrameLayout(context)
                    val webView = WebView(context).apply {
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.mediaPlaybackRequiresUserGesture = false // autoplay allowed here only
                        webViewClient = buildYouTubeWebViewClient()
                    }
                    webView.webChromeClient = object : WebChromeClient() {
                        private var customView: View? = null
                        override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                            if (customView != null) { onHideCustomView(); return }
                            customView = view
                            webView.visibility = View.GONE
                            container.addView(view, FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
                        }
                        override fun onHideCustomView() {
                            container.removeView(customView)
                            customView = null
                            webView.visibility = View.VISIBLE
                        }
                    }
                    container.addView(webView, FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
                    webView.loadUrl(
                        "https://www.youtube.com/embed/$videoId?playsinline=1&autoplay=1",
                        mapOf("Referer" to "https://${context.packageName}")
                    )
                    container
                },
                onRelease = { view ->
                    (view as? FrameLayout)?.let { fl ->
                        for (i in 0 until fl.childCount) (fl.getChildAt(i) as? WebView)?.destroy()
                    }
                }
            )
        }
    }
}