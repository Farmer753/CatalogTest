package ru.ll.catalogtest.ui.product

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import ru.ll.catalogtest.R
import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.ui.components.OldPriceView
import ru.ll.catalogtest.ui.components.Toolbar
import ru.ll.catalogtest.ui.debugPlaceholder
import ru.ll.catalogtest.ui.theme.Accent
import ru.ll.catalogtest.ui.theme.CatalogTestTheme
import timber.log.Timber


@Preview
@Composable
fun ProductPreview() {
    CatalogTestTheme {
        ProductScreen("hhh", onBackClick = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    productSlug: String,
    viewModel: ProductViewModel = hiltViewModel<ProductViewModel, ProductViewModel.Factory>(
        creationCallback = { factory -> factory.create(slug = productSlug) }
    ),
    onBackClick: () -> Unit
) {
    Timber.d("productSlug $productSlug")
    Timber.d("viewModel ${viewModel.slug}")
    Column(modifier = Modifier) {
        Toolbar(
            contentColor = Color.Gray,
            title = null,
            backgroundColor = Color.White,
            onStartIconClick = onBackClick
        )
        val errorMessage = viewModel.error.collectAsStateWithLifecycle()
        val progress = viewModel.progress.collectAsStateWithLifecycle()
        val textErrorMessage = errorMessage.value
        val productState = viewModel.product.collectAsStateWithLifecycle()
        val product = productState.value
        PullToRefreshBox(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            isRefreshing = progress.value,
            onRefresh = viewModel::getData
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                if (product != null) {
                    Timber.i("product $product")
                    ProductView(product)
                }
                if (textErrorMessage != null) {
                    Timber.i("textErrorMessage $textErrorMessage")

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

@Composable
fun ColumnScope.ProductView(
    product: UiProduct
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .weight(1f)
    ) {
        //TODO листание картинок
        AsyncImage(
            model = "https://vimos.ru/${product.images.first()}",
            contentDescription = "test",
            placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
            modifier = Modifier
                .fillMaxWidth()
                .height(288.dp)
        )
        PointsView()
        if (product.isDiscount) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .background(
                        color = Accent,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp),
                text = "-${product.sizeDiscount}%",
                color = Color.White
            )
        }
        ProductTitleView(product)
        ProductDetailsView(product)
    }
}

@Composable
fun ProductTitleView(product: UiProduct) {
    Column(
        modifier = Modifier.padding(16.dp, 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Арт. ${product.sku}",
//            style = MaterialTheme.typography.body1.copy(color = Dark60)
        )
        Text(
            text = product.title,
//            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun ProductDetailsView(product: UiProduct) {
    Row(
        modifier = Modifier.padding(16.dp, 13.dp)
    ) {
        Text(
            text = "${product.price / 100.0} P/${product.units}",
//            style = MaterialTheme.typography.body1.copy(color = Dark60),
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()

        )
        if (product.isDiscount) {
            OldPriceView(product.priceOld)
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




