package com.kumar.crackup.home

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

fun buildYouTubeWebViewClient(): WebViewClient {
    return object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView,
            request: WebResourceRequest
        ): Boolean {
            val url = request.url.toString()

            // Let the embed itself, and everything the IFrame player needs internally, load normally.
            val isPlayerInternal = url.contains("youtube.com/embed/") ||
                    url.contains("youtube.com/s/player") ||
                    url.contains("googlevideo.com") ||
                    url.contains("ytimg.com") ||
                    url.contains("doubleclick.net")

            if (isPlayerInternal) return false

            // Anything else pointing at YouTube (channel page, "watch on YouTube", related
            // video clicks, comments, etc.) — send it out of the app instead.
            if (request.isForMainFrame && (url.contains("youtube.com") || url.contains("youtu.be"))) {
                openInYouTube(view.context, url)
                return true // tell the WebView: don't load this yourself
            }

            return false
        }
    }
}

private fun openInYouTube(context: Context, url: String) {
    val ytAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        setPackage("com.google.android.youtube")
    }
    try {
        context.startActivity(ytAppIntent)
    } catch (e: ActivityNotFoundException) {
        // YouTube app not installed — fall back to whatever can handle it (browser, etc.)
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}