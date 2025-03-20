package ru.ll.catalogtest.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
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
            Toolbar(
                title = "Металлопрокат",
                startIcon = null
            )
            Toolbar(
                contentColor = Color.DarkGray,
                backgroundColor = Color.White,
                endIcon = null
            )
        }
    }
}

@Composable
fun Toolbar(
    contentColor: Color = Color.White,
    backgroundColor: Color = Primary,
    title: String? = null,
    onStartIconClick: () -> Unit = {},
    onEndIconClick: () -> Unit = {},
    @DrawableRes
    startIcon: Int? = R.drawable.arrow,
    @DrawableRes
    endIcon: Int? = R.drawable.share_symbol
) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .height(56.dp)
    ) {
        if (startIcon != null) {
            IconButton(
                onClick = onStartIconClick,
                modifier = Modifier
                    .size(48.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = startIcon),
                    contentDescription = "back",
                    colorFilter = ColorFilter.tint(contentColor),
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        Text(
            text = title ?: "",
            style = MaterialTheme.typography.titleLarge.copy(color = contentColor),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (endIcon != null) {
            IconButton(
                onClick = onEndIconClick,
                modifier = Modifier
                    .size(48.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.share_symbol),
                    contentDescription = "back",
                    colorFilter = ColorFilter.tint(contentColor),
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}