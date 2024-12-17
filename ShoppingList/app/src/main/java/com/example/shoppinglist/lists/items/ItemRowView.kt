package com.example.shoppinglist.lists.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.models.Item

@Composable
fun ItemRomView(
    modifier: Modifier = Modifier,
    item : Item,
    onCheckedChange : ()->Unit = {}
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xBA6650A4))
    ) {
        Row {
            Text(
                modifier = Modifier
                    .padding(16.dp).weight(1f),
                fontSize = 20.sp,
                text = item.name ?: ""
            )

            Text(
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 20.sp,
                text = item.qtd.toString() ?: ""
            )
            Checkbox(
                checked = item.checked,
                onCheckedChange = { onCheckedChange() }
            )
        }
    }
    }
