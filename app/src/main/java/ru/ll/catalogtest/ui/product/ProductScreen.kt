package ru.ll.catalogtest.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import ru.ll.catalogtest.R
import ru.ll.catalogtest.domain.ApiConst.API_URL_IMG
import ru.ll.catalogtest.domain.ProductsRepository
import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.ui.components.OldPriceView
import ru.ll.catalogtest.ui.components.Toolbar
import ru.ll.catalogtest.ui.debugPlaceholder
import ru.ll.catalogtest.ui.theme.Accent
import ru.ll.catalogtest.ui.theme.CatalogTestTheme
import ru.ll.catalogtest.ui.theme.GrayDark
import ru.ll.catalogtest.ui.theme.GrayLite
import timber.log.Timber


@Preview
@Composable
fun ProductPreview() {
    CatalogTestTheme {
        ProductScreen(
            "hhh", onBackClick = {}, viewModel = ProductViewModel(
                productsRepository = object : ProductsRepository {

                    override suspend fun getData(slug: String): List<UiProduct> {
                        return listOf(UiProduct.test())
                    }

                    override suspend fun getProduct(slug: String): UiProduct {
                        return UiProduct.test()
                    }
                },
                slug = ""
            )
        )
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
        val pagerState = rememberPagerState(
            pageCount = { product.images.size },
        )

        HorizontalPager(state = pagerState) { page ->
            AsyncImage(
                model = API_URL_IMG + product.images[page],
                contentDescription = "test",
                placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(288.dp)
            )
        }

        if (product.images.size > 1) {
            PointsView(
                modifier = Modifier.padding(top = 8.dp),
                itemsCount = product.images.size,
                selectedItem = pagerState.currentPage
            )
        }
        if (product.isDiscount) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .background(
                        color = Accent,
                        shape = RoundedCornerShape(4.dp)
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
            text = stringResource(id = R.string.sku, product.sku),
            style = MaterialTheme.typography.titleSmall.copy(color = GrayLite)
        )
        Text(
            text = product.title,
            style = MaterialTheme.typography.titleMedium.copy(color = GrayDark)
        )
    }
}

@Composable
fun ProductDetailsView(product: UiProduct) {
    Row(
        modifier = Modifier.padding(16.dp, 13.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        val color = if (product.isDiscount) {
            Accent
        } else {
            GrayDark
        }
        Text(
            text = "${product.price / 100.0} P/${product.units}",

            style = MaterialTheme.typography.titleLarge.copy(color = color),
            modifier = Modifier
                .padding(end = 16.dp)
        )
        if (product.isDiscount) {
            OldPriceView(product.priceOld, product.units)
        }
    }
}

@Composable
fun PointsView(
    modifier: Modifier = Modifier,
    itemsCount: Int,
    selectedItem: Int,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(itemsCount) { iteration ->
            val color =
                if (selectedItem == iteration) {
                    Color.DarkGray
                } else {
                    Color.Gray
                }
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .background(color, CircleShape)
                    .size(6.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PagerIndicatorPreview() {
    CatalogTestTheme {
        PointsView(
            modifier = Modifier.padding(16.dp),
            itemsCount = 4,
            selectedItem = 2,
        )
    }
}