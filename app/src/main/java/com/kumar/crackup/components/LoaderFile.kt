package com.kumar.crackup.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.ui.theme.lightBlue

/**
 * A loading indicator built around a single dynamic letter, with a row
 * of pulsing "progress" dots beneath it.
 *
 * The letter is just a Composable parameter, so it's dynamic by nature —
 * recompose this with a different `letter` (e.g. the first letter of
 * whatever is currently loading) and it cross-fades/scales into place
 * instead of hard-cutting.
 *
 * @param letter the character to display — pass any Char, changes are animated.
 * @param modifier standard Compose modifier for the outer Column.
 * @param letterSize font size of the big letter.
 * @param dotSize diameter of each progress dot.
 * @param dotCount how many dots to show (3 is the classic "..." feel).
 * @param color color of the letter and (at full opacity) the dots.
 */
@Composable
fun LetterLoader(
    letter: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    letterSize: TextUnit = 56.sp,
    dotSize: Dp = 8.dp,
    dotCount: Int = 3,
    color: Color = lightBlue
) {
    val infiniteTransition = rememberInfiniteTransition(label = "letter_loader")

    // Letter gently breathes in and out — signals "still working" without
    // being distracting the way a spin or shake would be.
    val letterScale by infiniteTransition.animateFloat(
        initialValue = 0.88f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 650, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "letter_scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // AnimatedContent handles the "dynamic" part: whenever the caller
        // passes a new letter, this cross-fades + scales between the old
        // and new glyph instead of an abrupt swap.
        AnimatedContent(
            targetState = letter,
            transitionSpec = {
                (fadeIn(tween(220)) + scaleIn(initialScale = 0.7f, animationSpec = tween(220)))
                    .togetherWith(fadeOut(tween(150)) + scaleOut(targetScale = 0.7f, animationSpec = tween(150)))
            },
            label = "letter_swap"
        ) { animatedLetter ->

            Box(modifier = Modifier.scale(letterScale)) {
                letter()
            }

        }

        Spacer(Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(dotCount) { index ->
                // Each dot's animation is offset by a delay so they pulse
                // left-to-right in sequence rather than all at once.
                val dotScale by infiniteTransition.animateFloat(
                    initialValue = 0.35f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 600,
                            delayMillis = index * 150,
                            easing = FastOutSlowInEasing
                        ),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "dot_scale_$index"
                )
                val dotAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.35f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 600,
                            delayMillis = index * 150,
                            easing = FastOutSlowInEasing
                        ),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "dot_alpha_$index"
                )

                Box(
                    modifier = Modifier
                        .size(dotSize)
                        .scale(dotScale)
                        .clip(CircleShape)
                        .background(color.copy(alpha = dotAlpha))
                )
            }
        }
    }
}
