package com.cleancompose.application

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cleancompose.ui.theme.Purple80

@Composable
fun CustomSplashScreen(onLoaded: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        onLoaded()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple80),
    ) {
        Icon(
            painter = painterResource(id = com.cleancompose.interface_adapter.R.drawable.ic_wb_sunny),
            contentDescription = "Group icon",
            tint = Color.White,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center),
        )
    }
}