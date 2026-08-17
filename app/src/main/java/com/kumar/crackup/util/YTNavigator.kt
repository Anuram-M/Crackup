package com.kumar.crackup.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun YTNavigator(context: Context, channelIdOrHandle: String) {
    // channelIdOrHandle can be either:
    //  - a channel ID starting with "UC..." e.g. "UCX6OQ3DkcsbYNE6H8uQQuVA"
    //  - a handle e.g. "@MrBeast" (newer YouTube handles)
    val url = when {
        channelIdOrHandle.startsWith("UC") ->
            "https://www.youtube.com/channel/$channelIdOrHandle"
        channelIdOrHandle.startsWith("@") ->
            "https://www.youtube.com/$channelIdOrHandle"
        else ->
            "https://www.youtube.com/@$channelIdOrHandle"
    }

    val ytAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        setPackage("com.google.android.youtube")
    }

    try {
        context.startActivity(ytAppIntent)
    } catch (e: ActivityNotFoundException) {
        // YouTube app not installed — open in browser instead
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}