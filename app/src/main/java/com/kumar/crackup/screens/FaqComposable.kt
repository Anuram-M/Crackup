package com.kumar.crackup.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kumar.crackup.R
import com.kumar.crackup.model.FAQItem
import com.kumar.crackup.templates.ExpandableQACard
import com.kumar.crackup.ui.theme.BaseAppTheme
import com.kumar.crackup.ui.theme.bodyFont
import com.kumar.crackup.ui.theme.bodyFontSmallSize

@Composable
fun FaqComposable() {

    val qaItems = listOf(
        FAQItem(
            id = "1",
            question = "How do I access practice tests?",
            answer = "After subscription, students can access TNPSC practice MCQs, PYQs and mock tests directly through the platform."
        ),
        FAQItem(
            id = "1",
            question = "Is Tamil medium available?",
            answer = "Yes, Practice questions and materials are available in both Tamil and English."
        ),
        FAQItem(
            id = "1",
            question = "Are current affairs updated daily?",
            answer = "Yes, Daily current affairs MCQs and monthly PDFs are updated regularly for TNPSC preparation."
        ),
        FAQItem(
            id = "1",
            question = "Can I watch free TNPSC classes?",
            answer = "Yes, Free TNPSC revision and explanation classes are available through our YouTube channel."
        ),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(vertical = 20.dp)) {

            Image(painter = painterResource(R.drawable.question), contentDescription = null, modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp), contentScale = ContentScale.FillHeight)
            LazyColumn() {
                items(qaItems) { item ->
                    ExpandableQACard(
                        Color.White,
                        Color.Black,
                        bodyFont,
                        bodyFontSmallSize,
                        item
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun previewFaq() {
    BaseAppTheme{
        FaqComposable()
    }
}