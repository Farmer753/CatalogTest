package ru.ll.catalogtest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.ll.catalogtest.domain.ApiConst.API_URL_IMG_CATEGORY
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.ui.theme.CatalogTestTheme
import ru.ll.catalogtest.ui.theme.GrayDark


@Composable
fun CatalogItem(
    modifier: Modifier = Modifier,
    category: UiCategory,
    onClick: (UiCategory) -> Unit,
    setColorFilter: Boolean
) {
    Row(
        modifier = modifier
            .clickable(onClick = { onClick(category) })
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .size(36.dp),
            model = API_URL_IMG_CATEGORY + category.icon,
            contentDescription = "back",
            colorFilter = if (setColorFilter) {
                ColorFilter.tint(MaterialTheme.colorScheme.primary)
            } else {
                null
            }
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.titleSmall.copy(color = GrayDark),
            text = category.title
        )
    }
}

@Composable
@Preview
fun CatalogItemPreview() {
    CatalogTestTheme {
        Column {
            CatalogItem(
                category = UiCategory.default(false),
                onClick = {},
                setColorFilter = true
            )
            CatalogItem(
                category = UiCategory.default(false),
                onClick = {},
                setColorFilter = false
            )
        }
    }
}