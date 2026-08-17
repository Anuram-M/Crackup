package com.kumar.crackup.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kumar.crackup.R
import com.kumar.crackup.ui.theme.lightBlue

@Composable
fun <T> SelectionList(items: List<Pair<String, T>>, onItemClick: (T) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(vertical = 10.dp)) {
        items(items) { (label, value) ->
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = { onItemClick(value) }, // <-- the click event driving navigation
                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    BodyText(text = label, modifier = Modifier.weight(1f), textColor = lightBlue)
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(painter = painterResource(R.drawable.back_arrow), modifier = Modifier.size(24.dp).padding(6.dp).rotate(-180f), contentDescription = null)
                }
            }
        }
    }
}