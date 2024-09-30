package ru.ll.catalogtest.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.ll.catalogtest.R
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.ui.theme.CatalogTestTheme

@Preview
@Composable
private fun CatalogPreview() {
    CatalogTestTheme {
        CatalogScreen()
    }
}

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    onCategoryClick: (UiCategory) -> Unit = {},
    onCategorySlugClick: (String) -> Unit = {}
) {


    Column {
        Toolbar()
        val categories: State<List<UiCategory>?> = viewModel.catalog.collectAsState()
        val categoryValues = categories.value
        if (categoryValues != null) {
            LazyColumn {
                items(categoryValues) {
                    CatalogItem(
                        modifier = Modifier.weight(1f),
                        category = it,
                        onClick = onCategoryClick
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun Toolbar() {
    Box(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(start = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Каталог товаров",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 22.sp
        )
    }
}

@Composable
private fun CatalogItem(
    modifier: Modifier = Modifier,
    category: UiCategory,
    onClick: (UiCategory) -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = { onClick(category) })
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .size(36.dp),
            model = "https://vimos.ru/${category.icon}",
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



