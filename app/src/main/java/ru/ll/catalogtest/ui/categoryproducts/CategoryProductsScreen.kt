package ru.ll.catalogtest.ui.categoryproducts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import ru.ll.catalogtest.ui.theme.CatalogTestTheme
import ru.ll.catalogtest.ui.theme.Sale


@Preview
@Composable
fun CategoryProductsPreview() {
    CatalogTestTheme {
        CategoryProductsScreen("", onBackClick = {})
    }
}

@Composable
fun CategoryProductsScreen(
    categorySlug: String,
    viewModel: CategoryProductsViewModel = hiltViewModel<CategoryProductsViewModel, CategoryProductsViewModel.Factory>(
        creationCallback = { factory -> factory.create(slug = categorySlug) }
    ),
    onProductClick: (UiProduct) -> Unit = {},
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.background(color = Color.Yellow)) {
        Toolbar(
            title = "Каталог товаров",
            endIcon = null,
            onStartIconClick = onBackClick
        )
//        val products: MutableState<List<UiProduct>> = remember {
//            mutableStateOf(
//                (1..100).map { test() }
//            )
//        }
        val products = viewModel.catalog.collectAsStateWithLifecycle()
        if (products.value != null) {
            Products(
                modifier = Modifier.weight(1f),
                products.value!!
            ) {
                onProductClick(it)
            }
        }
    }
}

@Composable
fun Products(
    //TODO проверять наличие товара
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
        }
    }
}

@Composable
fun Product(
    product: UiProduct,
    onClick: (UiProduct) -> Unit
) {
    Card(
        onClick = { onClick(product) }
    ) {
        Row {
            Column {
                Box(modifier = Modifier.padding(16.dp, 12.dp)) {
                    AsyncImage(
                        model = "https://vimos.ru/${product.images.first()}",
                        contentDescription = "test",
                        placeholder = debugPlaceholder(R.drawable.ic_launcher_background),
                        modifier = Modifier
                            .size(114.dp, 101.dp)

                    )
                    if (product.isDiscount) {
                        Text(
                            modifier = Modifier.background(
                                color = Sale,
                                shape = MaterialTheme.shapes.medium
                            ),
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
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 40.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Арт. ${product.sku}",
//                    style = MaterialTheme.typography.body1.copy(color = Dark60)
                )
                Text(
                    text = product.title,
//                    style = MaterialTheme.typography.h6
                )
                Row {
                    Text(
                        text = "${product.price / 100.0} P",
//                        style = MaterialTheme.typography.body1.copy(color = Dark60),
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()

                    )
                    if (product.isDiscount) {
                        OldPriceView(product.priceOld)
                    }
                }
            }
            Divider(color = Color.Black, thickness = 1.dp)
        }
    }
}
