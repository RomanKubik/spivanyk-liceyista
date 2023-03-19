package com.roman.kubik.songer.home.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roman.kubik.songer.home.R
import com.roman.kubik.songer.home.ui.HomeCategory
import com.roman.kubik.songer.songs.domain.song.SongCategory

@Composable
fun HomeCategory(modifier: Modifier = Modifier, category: HomeCategory) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(colorResource(id = category.iconBackgroundColor), CircleShape)
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                painter = painterResource(id = category.icon),
                contentDescription = category.title,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = category.title,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = category.subtitle,
            )
        }
    }
}

@Preview(name = "Default")
@Composable
fun HomeCategoryPreview() {
    HomeCategory(
        modifier = Modifier.clickable {  },
        category = HomeCategory(
            songCategory = SongCategory.BONFIRE,
            title = "Bonfire songs",
            subtitle = "Super duper subtitle",
            icon = R.drawable.ic_bonfire,
            iconBackgroundColor = R.color.bright_green,
        )
    )
}