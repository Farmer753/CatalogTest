package ru.ll.catalogtest.ui.subcatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.ui.components.CatalogItem
import ru.ll.catalogtest.ui.theme.CatalogTestTheme

@Preview
@Composable
private fun SubCatalogPreview() {
    CatalogTestTheme {
        SubCatalogScreen(uiCategory = UiCategory.default(true))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubCatalogScreen(
    viewModel: SubCatalogViewModel = hiltViewModel(),
    onCategoryClick: (UiCategory) -> Unit = {},
    onCategorySlugClick: (String) -> Unit = {},
    uiCategory: UiCategory
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
    ) {
        Toolbar()
        //TODO title
        //TODO backButton
        LazyColumn {
            items(uiCategory.subCategories) {
                CatalogItem(
                    modifier = Modifier.fillMaxWidth(),
                    category = it,
                    onClick = onCategoryClick,
                    setColorFilter = false
                )
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




