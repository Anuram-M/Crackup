package com.kumar.crackup.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kumar.crackup.R

@OptIn(ExperimentalTextApi::class)
val bodyFont = FontFamily(
    Font(
        resId = R.font.poppins_regular,
        weight = FontWeight.Normal,
    ),
    Font(
        resId = R.font.poppins_semibold,
        weight = FontWeight.SemiBold,
    ),
    Font(
        resId = R.font.poppins_bold,
        weight = FontWeight.Bold,
    ),
)

val heroFont = FontFamily(
    Font(
        resId = R.font.poppins_bold,
        weight = FontWeight.Bold
    ),
//    Font(
//        resId = R.font.interb,
//        weight = FontWeight.Bold
//    ),
//    Font(
//        resId = R.font.titanr,
//        weight = FontWeight.Bold
//    ),
)
val titanFont = FontFamily(
    Font(
        resId = R.font.titanr,
        weight = FontWeight.Bold
    ),
)

val heroFontSize = 32.sp
val heroFontLargeSize = 42.sp
val bodyFontSize = 16.sp
val bodyFontLargeSize = 18.sp
val bodyFontSmallSize = 14.sp
val bodyFontTinySize = 12.sp
val bodyFontExtraLargeSize = 24.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)