package ru.ll.catalogtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.ui.catalog.CatalogScreen
import ru.ll.catalogtest.ui.categoryproducts.CategoryProductsScreen
import ru.ll.catalogtest.ui.product.ProductScreen
import ru.ll.catalogtest.ui.serializableType
import ru.ll.catalogtest.ui.theme.CatalogTestTheme
import timber.log.Timber
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Serializable
    object Catalog

    // Define a asset page destination that takes an ID
    @Serializable
    data class SubCatalog(val uiCategory: UiCategory)

    @Serializable
    data class CategoryProducts(val categorySlug: String)

    @Serializable
    data class Product(val product: UiProduct)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalogTestTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Catalog) {
                    composable<Catalog> {
                        CatalogScreen(
                            onCategorySlugClick = { categorySlug ->
                                Timber.d("CatalogScreen onCategorySlugClick $categorySlug")
                                navController.navigate(CategoryProducts(categorySlug))
                            },
                            onCategoryClick = { category ->
                                Timber.d("CatalogScreen onCategoryClick $category")

                                navController.navigate(SubCatalog(category))
                            }
                        )
                    }
                    composable<SubCatalog>(
                        typeMap = mapOf(typeOf<UiCategory>() to serializableType<UiCategory>())
                    ) {
//                        SubCatalogScreen(
//                            onCategorySlugClick = { categorySlug ->
//                                Timber.d("SubCatalogScreen onCategorySlugClick $categorySlug")
//                                navController.navigate(CategoryProducts(categorySlug))
//                            },
//                            onCategoryClick = { category ->
//                                Timber.d("SubCatalogScreen onCategoryClick $category")
//                                navController.navigate(SubCatalog(category))
//                            }
//                        )
                    }
                    composable<CategoryProducts> { backStackEntry ->
                        Timber.d("CategoryProducts 75")
                        val categoryProduct: CategoryProducts = backStackEntry.toRoute()
                        Timber.d("CategoryProducts 77")
                        CategoryProductsScreen(categoryProduct.categorySlug) { uiProduct ->
                            Timber.d("CategoryProducts onProductClick $uiProduct")
                            navController.navigate(
                                Product(
                                    uiProduct.copy(
                                        images = uiProduct.images.map {
                                            URLEncoder.encode(
                                                it, Charsets.UTF_8.name()
                                            )
                                        }
                                    )
                                )
                            )
                        }
                    }
                    composable<Product>(
                        typeMap = mapOf(typeOf<UiProduct>() to serializableType<UiProduct>())
                    ) { backStackEntry ->
                        val product: Product = backStackEntry.toRoute()
                        val uiProduct = product.product.copy(
                            images = product.product.images.map {
                                URLDecoder.decode(it, Charsets.UTF_8.name())
                            }
                        )
                        Timber.d("product.product ${product.product} ")
                        Timber.d("to ProductScreen uiProduct $uiProduct ")
                        ProductScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CatalogTestTheme {
        Greeting("Android")
    }
}