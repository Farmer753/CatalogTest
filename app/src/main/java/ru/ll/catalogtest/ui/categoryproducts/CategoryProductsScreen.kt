package ru.ll.catalogtest.ui.categoryproducts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import ru.ll.catalogtest.ui.theme.AccentLite
import ru.ll.catalogtest.ui.theme.CatalogTestTheme
import ru.ll.catalogtest.ui.theme.GrayBg
import ru.ll.catalogtest.ui.theme.GrayDark
import ru.ll.catalogtest.ui.theme.GrayLite
import ru.ll.catalogtest.ui.theme.Primary
import timber.log.Timber


@Preview
@Composable
fun CategoryProductsPreview() {
    CatalogTestTheme {
        CategoryProductsScreen(
            categorySlug = "",
            onBackClick = {},
            viewModel = CategoryProductsViewModel(
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
fun CategoryProductsScreen(
    categorySlug: String,
    viewModel: CategoryProductsViewModel = hiltViewModel<CategoryProductsViewModel, CategoryProductsViewModel.Factory>(
        creationCallback = { factory -> factory.create(slug = categorySlug) }
    ),
    onProductClick: (UiProduct) -> Unit = {},
    onBackClick: () -> Unit
) {
    Column {
        Toolbar(
            title = stringResource(R.string.goods_catalog),
            endIcon = null,
            onStartIconClick = onBackClick
        )
        val errorMessage = viewModel.error.collectAsStateWithLifecycle()
        val progress = viewModel.progress.collectAsStateWithLifecycle()
        val textErrorMessage = errorMessage.value
        val products = viewModel.catalog.collectAsStateWithLifecycle()
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
                if (products.value != null) {
                    Products(
                        modifier = Modifier.weight(1f),
                        products.value!!
                    ) {
                        onProductClick(it)
                    }
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
fun Products(
    modifier: Modifier = Modifier,
    products: List<UiProduct>,
    onClick: (UiProduct) -> Unit
) {
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = modifier,
        state = state,
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            Product(product = product, onClick = onClick)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = GrayBg
            )
        }
    }
}

@Composable
fun Product(
    product: UiProduct,
    onClick: (UiProduct) -> Unit
) {
    Row(modifier = Modifier.clickable(onClick = { onClick(product) })) {
        Column {
            Box(modifier = Modifier.padding(16.dp, 12.dp)) {
                AsyncImage(
                    model = API_URL_IMG + product.images.first(),
                    contentDescription = "test",
                    placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
                    modifier = Modifier
                        .size(114.dp, 101.dp)
                )
                if (product.isDiscount) {
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .background(
                                color = AccentLite,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp),
                        text = "-${product.sizeDiscount}%",
                        color = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .wrapContentHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.like),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sku, product.sku),
                style = MaterialTheme.typography.bodySmall.copy(color = GrayLite)
            )
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyMedium.copy(color = GrayDark)
            )
            Row {
                Text(
                    text = stringResource(id = R.string.rub, product.price / 100.0),
                    style = MaterialTheme.typography.bodyLarge.copy(color = GrayDark),
                    modifier = Modifier
                        .padding(end = 12.dp)
                )
                if (product.isDiscount) {
                    OldPriceView(product.priceOld, null)
                }
            }
            if (product.countAvailable > 0) {
                Text(
                    text = stringResource(R.string.available),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Primary)
                )
            }
        }
    }
}