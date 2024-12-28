package ru.ll.catalogtest.ui.product

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.ll.catalogtest.R
import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.ui.components.Toolbar
import ru.ll.catalogtest.ui.debugPlaceholder
import ru.ll.catalogtest.ui.theme.CatalogTestTheme


@Preview
@Composable
fun ProductPreview() {
    CatalogTestTheme {
        ProductScreen(onBackClick = {})
    }
}

@Composable
fun ProductScreen(
    onBackClick: () -> Unit
) {

    Column(modifier = Modifier) {
        Toolbar(
            contentColor = Color.Gray,
            title = "Карточка товара",
            backgroundColor = Color.White,
            onStartIconClick = onBackClick

        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            AsyncImage(
                model = UiProduct.PNG,
                contentDescription = "test",
                placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            PointsView()
            Box(modifier = Modifier.padding(16.dp, 12.dp)) {
                Button(
                    onClick = { },
                    modifier = Modifier.size(86.dp, 42.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "-15%",
                        color = Color.White
                    )
                }
            }
            ProductTitleView()
            ProductDetailsView()
        }
    }
}

@Composable
fun ProductTitleView() {
    Column(
        modifier = Modifier.padding(16.dp, 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Арт. 24764168",
//            style = MaterialTheme.typography.body1.copy(color = Dark60)
        )
        Text(
            text = "Грунтовка Eskaro Aquastop Contact адгез. для невпит./поверх. 1,5 кг",
//            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun ProductDetailsView() {
    Row(
        modifier = Modifier.padding(16.dp, 13.dp)
    ) {
        Text(
            text = "2 200 P",
//            style = MaterialTheme.typography.body1.copy(color = Dark60),
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()

        )
        Box(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "2 500 P",
//                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            )

            Canvas(modifier = Modifier.size(20.dp)) {

                // Fetching width and height for
                // setting start x and end y
                val canvasWidth = size.width
                val canvasHeight = size.height

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
}


@Composable
fun PointsView() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(5.dp)
    ) {
        // Creating a Canvas to draw a Circle
        Canvas(modifier = Modifier.size(5.dp)) {
            drawCircle(
                color = Color.Black,
                radius = size.minDimension / 2
            )
        }
        Canvas(modifier = Modifier.size(5.dp)) {
            drawCircle(
                color = Color.Gray,
                radius = size.minDimension / 2
            )
        }
        Canvas(modifier = Modifier.size(5.dp)) {
            drawCircle(
                color = Color.Gray,
                radius = size.minDimension / 2
            )
        }
    }
}


