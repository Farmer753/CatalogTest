package ru.ll.catalogtest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.ll.catalogtest.R
import ru.ll.catalogtest.ui.theme.CatalogTestTheme
import ru.ll.catalogtest.ui.theme.Primary

@Preview
@Composable
private fun ToolbarPreview() {
    CatalogTestTheme {
        Column {
            Toolbar()
            Toolbar(
                contentColor = Color.DarkGray,
                backgroundColor = Color.White
            )
        }
    }
}

@Composable
fun Toolbar(
    contentColor: Color = Color.White,
    backgroundColor: Color = Primary
) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "back",
                colorFilter = ColorFilter.tint(contentColor),
                modifier = Modifier
                    .padding(start = 16.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
            )
        }
        Text(
            text = "Металлопрокат",
            style = MaterialTheme.typography.bodyLarge.copy(color = contentColor),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.share_symbol),
                contentDescription = "back",
                colorFilter = ColorFilter.tint(contentColor),
                modifier = Modifier
            )
        }
    }

}