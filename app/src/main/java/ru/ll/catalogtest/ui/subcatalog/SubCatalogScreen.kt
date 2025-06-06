package ru.ll.catalogtest.ui.subcatalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.ui.components.CatalogItem
import ru.ll.catalogtest.ui.components.Toolbar
import ru.ll.catalogtest.ui.theme.CatalogTestTheme

@Preview
@Composable
private fun SubCatalogPreview() {
    CatalogTestTheme {
        SubCatalogScreen(
            uiCategory = UiCategory.default(true),
            onBackClick = {}
        )
    }
}

@Composable
fun SubCatalogScreen(
    viewModel: SubCatalogViewModel = hiltViewModel(),
    onCategoryClick: (UiCategory) -> Unit = {},
    uiCategory: UiCategory,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Toolbar(
            title = uiCategory.title,
            endIcon = null,
            onStartIconClick = onBackClick
        )
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