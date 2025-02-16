package ru.ll.catalogtest.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import ru.ll.catalogtest.ui.theme.Gray
import ru.ll.catalogtest.ui.theme.GrayLite
import timber.log.Timber

@Composable
fun OldPriceView(priceOld: Int, units: String?) {

//TODO передавать юниты
    Box(
//        modifier = Modifier
//            .padding(8.dp)
    ) {
        val textSize = remember {
            mutableStateOf(IntSize(0, 0))
        }
        Text(
            text = "${priceOld / 100.0} P${
                if (units != null) {
                    "/${units}"
                } else {
                    ""
                }
            }",
            style = if (units != null) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodySmall
            }.copy(color = Gray),
            modifier = Modifier
                .onSizeChanged {
                    textSize.value = it
                    Timber.i("canvasWidth ${it.width}")
                    Timber.i("canvasHeight ${it.height}")
                }
        )

        Canvas(modifier = Modifier.size(
            with(LocalDensity.current) {
                DpSize(
                    textSize.value.width.toDp(),
                    textSize.value.height.toDp()
                )
            }
        )) {

            // Fetching width and height for
            // setting start x and end y
            val canvasWidth = size.width
            val canvasHeight = size.height
            Timber.d("canvasWidth $canvasWidth")
            Timber.d("canvasHeight $canvasHeight")

            // drawing a line between start(x,y) and end(x,y)
            drawLine(
                start = Offset(x = canvasWidth, y = 0f),
                end = Offset(x = 0f, y = canvasHeight),
                color = Color.Red,
                strokeWidth = 5F
            )
        }
    }

}