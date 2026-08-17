//package com.kumar.crackup.components
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.viewinterop.AndroidView
//import io.github.kostub.iosmath.MTMathView
//
//@Composable
//fun NativeMathText(
//    latexText: String,
//    modifier: Modifier = Modifier,
//    textColor: Color = Color.Unspecified,
//    textSizePx: Float = 48f
//) {
//    AndroidView(
//        modifier = modifier,
//        factory = { context ->
//            MTMathView(context).apply {
//                fontSize = textSizePx
//                if (textColor != Color.Unspecified) {
//                    setTextColor(textColor.toArgb())
//                }
//            }
//        },
//        update = { mathView ->
//            // MTMathView uses raw LaTeX strings directly
//            mathView.latex = latexText
//        }
//    )
//}