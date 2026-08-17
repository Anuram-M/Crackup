package com.kumar.crackup.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kumar.crackup.R
import com.kumar.crackup.model.Topic
import com.kumar.crackup.ui.theme.dialogCardColor

@Composable
fun SyllabusCard(syllabusTopic: Topic, isEnglish: Boolean, onPress: () -> Unit) {

    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {

        Box(
            modifier = Modifier.fillMaxWidth()
                .background(color = dialogCardColor, shape = RoundedCornerShape(10.dp)),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth().clickable{
                onPress()
            }.padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                BodyText(text = if(isEnglish) syllabusTopic.name else syllabusTopic.nameTamil, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(5.dp))
                Icon(painter = painterResource(R.drawable.back_arrow), modifier = Modifier.size(24.dp).padding(6.dp).rotate(-180f), contentDescription = null)
            }
        }
    }
}