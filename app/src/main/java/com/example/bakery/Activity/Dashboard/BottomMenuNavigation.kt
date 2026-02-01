package com.example.bakery.Activity.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakery.R

@Composable
fun BottomMenu(
    modifier: Modifier,
    onExploreClick: (() -> Unit)? = null,
    onItemClick: (() -> Unit)?,
    onFavoriteClick: (() -> Unit)?,
    onProfileClick: (() -> Unit)? = null
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            .background(colorResource(R.color.green),
                shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomMenuItem(
            icon = painterResource(R.drawable.btn_1),
            text = "Explorer",
            onItemClick = onExploreClick
        )
        BottomMenuItem(
            icon = painterResource(R.drawable.btn_2),
            text = "Cart",
            onItemClick = onItemClick
        )
        BottomMenuItem(
            icon = painterResource(R.drawable.btn_3),
            text = "Favorite",
            onItemClick = onFavoriteClick
        )
        BottomMenuItem(icon = painterResource(R.drawable.btn_4), text = "Orders")
        BottomMenuItem(
            icon = painterResource(R.drawable.btn_5),
            text = "Profile",
            onItemClick = onProfileClick
        )
    }
}


@Composable
fun BottomMenuItem(
    icon: Painter,
    text:String,
    onItemClick:(()->Unit)?=null
){
    Column(modifier = Modifier
        .padding(8.dp)
        .clickable{ onItemClick?.invoke()}
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.padding(vertical = 2.dp))
        Text(text, color = Color.White, fontSize = 10.sp)
    }
}
