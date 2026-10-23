package com.kumar.crackup.privacyPolicy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.kumar.crackup.R
import com.kumar.crackup.ui.theme.bodyFont

@Composable
fun PrivacyPolicyComposable() {
    Box(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        PrivacyPolicyContent()
    }
}

@Composable
fun PrivacyPolicyContent() {
    val context = LocalContext.current
    val htmlText = stringResource(id = R.string.privacy_policy_content)

    // Parses HTML tags like <b> and <br> into Spanned text
    val spannedText = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)

    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 10.dp)) {
        Text(
            text = annotatedStringResource(id = R.string.privacy_policy_content),
            fontFamily = bodyFont
        )
    }

}

@Composable
fun annotatedStringResource(id: Int): AnnotatedString {
    val context = LocalContext.current
    val rawText = stringResource(id)
    val spanned = HtmlCompat.fromHtml(rawText, HtmlCompat.FROM_HTML_MODE_LEGACY)

    return remember(rawText) {
        buildAnnotatedString {
            append(spanned.toString())
            // Applies bold styling from spanned tags
            spanned.getSpans(0, spanned.length, Any::class.java).forEach { span ->
                val start = spanned.getSpanStart(span)
                val end = spanned.getSpanEnd(span)
                if (span is android.text.style.StyleSpan && span.style == android.graphics.Typeface.BOLD) {
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                }
            }
        }
    }
}