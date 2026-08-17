package com.kumar.crackup.components

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView

//@SuppressLint("SetJavaScriptEnabled")
//@Composable
//fun KMathText(
//    text: String,
//    modifier: Modifier = Modifier,
//    textColor: Color = Color.Unspecified,
//    textSizePx: Int = 16
//) {
////    val hexColor = if (textColor != Color.Unspecified) {
////        String.format("#%06X", 0xFFFFFF and textColor.toArgb())
////    } else {
////        "#000000"
////    }
////
////    val htmlContent = """
////        <!DOCTYPE html>
////        <html>
////        <head>
////            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
////            <!-- Local Asset Paths -->
////            <link rel="stylesheet" href="file:///android_asset/katex/katex.min.css">
////            <script src="file:///android_asset/katex/katex.min.js"></script>
////            <script src="file:///android_asset/katex/contrib/auto-render.min.js"></script>
////            <style>
////                body {
////                    color: $hexColor;
////                    font-size: ${textSizePx}px;
////                    margin: 0;
////                    padding: 0;
////                    background-color: transparent;
////                    font-family: sans-serif;
////                }
////            </style>
////        </head>
////        <body>
////            <div id="math">$text</div>
////            <script>
////                document.addEventListener("DOMContentLoaded", function() {
////                    renderMathInElement(document.getElementById("math"), {
////                        delimiters: [
////                            {left: "$$", right: "$$", display: true},
////                            {left: "$", right: "$", display: inline}
////                        ]
////                    });
////                });
////            </script>
////        </body>
////        </html>
////    """.trimIndent()
////
////    AndroidView(
////        modifier = modifier.fillMaxWidth(),
////        factory = { context ->
////            WebView(context).apply {
////                settings.javaScriptEnabled = true
////                settings.allowFileAccess = true // 👈 Required to access local assets
////                settings.cacheMode = WebSettings.LOAD_DEFAULT
////                setBackgroundColor(0) // Transparent background
////            }
////        },
////        update = { webView ->
////            webView.loadDataWithBaseURL("file:///android_asset/katex/", htmlContent, "text/html", "UTF-8", null)
////        }
////    )
//
//    val hexColor = if (textColor != Color.Unspecified) {
//        String.format("#%06X", 0xFFFFFF and textColor.toArgb())
//    } else {
//        "#000000"
//    }
//
//    // Escape backslashes for JS context
//    val safeText = text
//        .replace("\\", "\\\\")
//        .replace("`", "\\`")
//
//    val htmlContent = """
//        <!DOCTYPE html>
//        <html>
//        <head>
//            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
//            <link rel="stylesheet" href="file:///android_asset/katex/katex.min.css">
//            <script src="file:///android_asset/katex/katex.min.js"></script>
//            <script src="file:///android_asset/katex/contrib/auto-render.min.js"></script>
//            <style>
//                body {
//                    color: $hexColor;
//                    font-size: ${textSizePx}px;
//                    margin: 0;
//                    padding: 0;
//                    background-color: transparent;
//                    font-family: sans-serif;
//                }
//            </style>
//        </head>
//        <body>
//            <div id="math"></div>
//            <script>
//                document.addEventListener("DOMContentLoaded", function() {
//                    var container = document.getElementById("math");
//                    container.textContent = `$safeText`;
//
//                    renderMathInElement(container, {
//                        delimiters: [
//                            {left: "$$", right: "$$", display: true},
//                            {left: "$", right: "$", display: inline},
//                            {left: "\\(", right: "\\)", display: inline},
//                            {left: "\\[", right: "\\]", display: true}
//                        ],
//                        throwOnError: false
//                    });
//                });
//            </script>
//        </body>
//        </html>
//    """.trimIndent()
//
//    AndroidView(
//        modifier = modifier.fillMaxWidth(),
//        factory = { context ->
//            WebView(context).apply {
//                settings.javaScriptEnabled = true
//                settings.allowFileAccess = true
//                settings.allowContentAccess = true
//                settings.domStorageEnabled = true
//                settings.cacheMode = WebSettings.LOAD_NO_CACHE
//                setBackgroundColor(0) // Transparent background
//            }
//        },
//        update = { webView ->
//            webView.loadDataWithBaseURL(
//                "file:///android_asset/katex/",
//                htmlContent,
//                "text/html",
//                "UTF-8",
//                null
//            )
//        }
//    )
//}
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSize
import com.kumar.crackup.ui.theme.textBlack

/**
 * Renders text containing HTML-lite formatting (<p>, <b>) AND a common
 * subset of LaTeX-style math ($...$ delimited) — entirely natively,
 * no WebView, no KaTeX, no third-party LaTeX library.
 *
 * Supported inside $...$:
 *   \frac{a}{b}        -> real stacked fraction (numerator/bar/denominator)
 *   x^{2} or x^2        -> superscript
 *   x_{4} or x_4         -> subscript
 *   \times \div \pm \leq \geq \neq \approx \infty \pi \cdot -> symbols
 *   \sqrt{x}            -> √(x)
 *   \text{...}          -> plain text (command stripped)
 *
 * NOT supported: matrices, integrals/sums with limits, deeply nested
 * fractions-within-fractions, and most advanced LaTeX. For content
 * that goes beyond this subset, fall back to the WebView+KaTeX
 * version (KatexText) — this is a lighter, offline-friendly option
 * for the common cases, not a full LaTeX engine.
 */
@Composable
fun NativeMathText(
    content: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight,
    color: Color = textBlack
) {
    val inlineContentMap = remember(content) { mutableMapOf<String, InlineTextContent>() }

    val annotated = remember(content) {
        buildNativeMathAnnotatedString(content, bodyFontSize, fontWeight, color, inlineContentMap)
    }

    Text(
        text = annotated,
        inlineContent = inlineContentMap,
        fontSize = bodyFontSize,
        color = color,
        fontFamily = bodyFont,
        lineHeight = 24.sp,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

private fun buildNativeMathAnnotatedString(
    raw: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    color: Color,
    inlineContentMap: MutableMap<String, InlineTextContent>
): AnnotatedString {
    val cleaned = raw
        .replace(Regex("</p>\\s*"), "\n")
        .replace(Regex("<p>"), "")

    return androidx.compose.ui.text.buildAnnotatedString {
        val mathRegex = Regex("\\$(.+?)\\$")
        var lastIndex = 0
        var mathBlockIndex = 0

        for (match in mathRegex.findAll(cleaned)) {
            if (match.range.first > lastIndex) {
                appendFormattedText(this, fontWeight,cleaned.substring(lastIndex, match.range.first))
            }
            appendMathExpression(
                this,
                match.groupValues[1],
                fontSize,
                fontWeight,
                color,
                inlineContentMap,
                "math_${mathBlockIndex++}"
            )
            lastIndex = match.range.last + 1
        }
        if (lastIndex < cleaned.length) {
            appendFormattedText(this, fontWeight, cleaned.substring(lastIndex))
        }
    }
}

/** Handles plain text with <b>...</b> bold spans (outside math mode). */
private fun appendFormattedText(builder: AnnotatedString.Builder, fontWeight: FontWeight, text: String) {
    val boldRegex = Regex("<b>(.*?)</b>")
    var last = 0
    for (m in boldRegex.findAll(text)) {
        if (m.range.first > last) builder.append(text.substring(last, m.range.first))
        builder.withStyle(SpanStyle(fontWeight = fontWeight)) { append(m.groupValues[1]) }
        last = m.range.last + 1
    }
    if (last < text.length) builder.append(text.substring(last))
}

/** Handles the content between $...$ — splits out \frac{}{} first (needs inline content), then everything else. */
private fun appendMathExpression(
    builder: AnnotatedString.Builder,
    latex: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    color: Color,
    inlineContentMap: MutableMap<String, InlineTextContent>,
    runId: String
) {
    val fracRegex = Regex("\\\\frac\\{([^{}]*)\\}\\{([^{}]*)\\}")
    var lastIndex = 0
    var fracIndex = 0

    for (m in fracRegex.findAll(latex)) {
        if (m.range.first > lastIndex) {
            appendSimpleMath(builder, latex.substring(lastIndex, m.range.first), fontSize, color)
        }

        val numerator = m.groupValues[1]
        val denominator = m.groupValues[2]
        val id = "${runId}_frac_$fracIndex"
        fracIndex++

        // Approximate size — good enough for typical short numerator/
        // denominator content like "1" over "8"; longer expressions
        // may need this scaled up slightly.
        val charCount = maxOf(numerator.length, denominator.length, 1)
        inlineContentMap[id] = InlineTextContent(
            placeholder = Placeholder(
                width = (fontSize.value * charCount * 0.62f).sp,
                height = (fontSize.value * 2.8f).sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        ) {
            FractionWidget(numerator, denominator, fontSize, fontWeight, color)
        }
        builder.appendInlineContent(id, "[frac]")
        lastIndex = m.range.last + 1
    }

    if (lastIndex < latex.length) {
        appendSimpleMath(builder, latex.substring(lastIndex), fontSize, color)
    }
}

/** Handles symbols, \sqrt{}, \text{}, and ^{}/_{} superscript/subscript — no fractions left at this point. */
private fun appendSimpleMath(builder: AnnotatedString.Builder, raw: String, fontSize: TextUnit, color: Color) {
    var s = raw
        .replace(Regex("\\\\text\\{([^{}]*)\\}")) { it.groupValues[1] }
        .replace(Regex("\\\\sqrt\\{([^{}]*)\\}")) { "\u221A(${it.groupValues[1]})" } // √(...)

    val symbols = listOf(
        "\\times" to "\u00D7", "\\div" to "\u00F7", "\\pm" to "\u00B1",
        "\\leq" to "\u2264", "\\geq" to "\u2265", "\\neq" to "\u2260",
        "\\approx" to "\u2248", "\\infty" to "\u221E", "\\pi" to "\u03C0",
        "\\cdot" to "\u00B7"
    )
    symbols.forEach { (cmd, sym) -> s = s.replace(cmd, sym) }

    // ^{2} / _{4}  OR bare ^2 / _4 (single char, no braces — common in "H2O"-style shorthand)
    val supSubRegex = Regex("([\\^_])\\{([^{}]*)\\}|([\\^_])([A-Za-z0-9])")
    var last = 0
    for (m in supSubRegex.findAll(s)) {
        if (m.range.first > last) builder.append(s.substring(last, m.range.first))

        val marker = m.groupValues[1].ifEmpty { m.groupValues[3] }
        val value = m.groupValues[2].ifEmpty { m.groupValues[4] }
        val style = if (marker == "^") {
            SpanStyle(baselineShift = BaselineShift.Superscript, fontSize = fontSize * 0.7f, fontStyle = FontStyle.Normal)
        } else {
            SpanStyle(baselineShift = BaselineShift.Subscript, fontSize = fontSize * 0.7f, fontStyle = FontStyle.Normal)
        }
        builder.withStyle(style) { builder.append(value) }
        last = m.range.last + 1
    }
    if (last < s.length) builder.append(s.substring(last))
}

@Composable
private fun FractionWidget(
    numerator: String,
    denominator: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    color: Color
) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(numerator, fontSize = fontSize * 0.85f, fontWeight = fontWeight, color = color, maxLines = 1, fontStyle = FontStyle.Normal)
        HorizontalDivider(color = color, thickness = 1.dp)
        Text(denominator, fontSize = fontSize * 0.85f, fontWeight = fontWeight, color = color, maxLines = 1, fontStyle = FontStyle.Normal)
    }
}

// -----------------------------------------------------------------
// Usage — same drop-in shape as KatexText:
// -----------------------------------------------------------------
// NativeMathText(content = question.explanation, modifier = Modifier.fillMaxWidth())
//
// For your actual example:
// "Total parts = \$1 + 7 = 8\$. Remaining fraction of X = \$\\frac{1}{8} = (\\frac{1}{2})^3\$..."
// renders as real inline fractions and a real superscript "3" — no
// external library, no network call, works fully offline.