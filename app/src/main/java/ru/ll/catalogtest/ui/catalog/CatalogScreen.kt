package ru.ll.catalogtest.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.ll.catalogtest.R
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.ui.components.CatalogItem
import ru.ll.catalogtest.ui.components.Toolbar
import ru.ll.catalogtest.ui.theme.CatalogTestTheme
import timber.log.Timber

@Preview
@Composable
private fun CatalogPreview() {
    CatalogTestTheme {
        CatalogScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    onCategoryClick: (UiCategory) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Toolbar(
            title = stringResource(R.string.goods_catalog),
            endIcon = null,
            startIcon = null
        )
        val categories: State<List<UiCategory>?> = viewModel.catalog.collectAsStateWithLifecycle()
        val categoryValues = categories.value
        val errorMessage = viewModel.error.collectAsStateWithLifecycle()
        val progress = viewModel.progress.collectAsStateWithLifecycle()
        val textErrorMessage = errorMessage.value
        PullToRefreshBox(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            isRefreshing = progress.value,
            onRefresh = viewModel::getData
        ) {
            if (categoryValues != null) {
                Timber.i("categoryValues $categoryValues")
                LazyColumn {
                    items(categoryValues) {
                        CatalogItem(
                            modifier = Modifier.fillMaxWidth(),
                            category = it,
                            onClick = onCategoryClick,
                            setColorFilter = true
                        )
                    }
                }
            }
            if (textErrorMessage != null) {
                Timber.i("textErrorMessage $textErrorMessage")
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(color = MaterialTheme.colorScheme.background)
                            .fillMaxWidth(),
                        text = textErrorMessage,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}