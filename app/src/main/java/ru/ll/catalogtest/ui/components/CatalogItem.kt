package ru.ll.catalogtest.ui.components

import android.util.Base64
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.ll.catalogtest.domain.UiCategory
import timber.log.Timber

//TODO show/not icon
@Composable
fun CatalogItem(
    modifier: Modifier = Modifier,
    category: UiCategory,
    onClick: (UiCategory) -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = { onClick(category) })
    ) {
        Timber.d("Base ${String(Base64.decode(category.icon.toByteArray(), Base64.DEFAULT))}")
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .size(36.dp),
            model = "https://vimos.ru/${
                String(Base64.decode(category.icon.toByteArray(), Base64.DEFAULT))
            }",
            contentDescription = "back",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = category.title
        )
    }
}
