package com.cleancompose.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cleancompose.interface_adapter.model.WeatherUIModel

@Composable
fun WeatherListItem(
    weatherUIModel: WeatherUIModel,
    onItemClick: (() -> Unit)?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick?.invoke() }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = weatherUIModel.date,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = weatherUIModel.imageRes),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = weatherUIModel.description,
            color = Color.Black,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(color = Color.Gray, thickness = 1.dp)
    }
}


@Preview
@Composable
fun PreviewWeatherList() {
    WeatherList(
        weatherList = listOf(
            WeatherUIModel(
                date = "Today",
                imageRes = com.cleancompose.interface_adapter.R.drawable.ic_wb_rainny,
                description = "Sunny, 25°C"
            ),
            WeatherUIModel(
                date = "Tomorrow",
                imageRes = com.cleancompose.interface_adapter.R.drawable.ic_wb_rainny,
                description = "Cloudy, 22°C"
            )
        )
    ) {

    }
}

