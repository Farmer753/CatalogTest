package ru.ll.catalogtest.ui.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ll.catalogtest.R
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.ui.theme.CatalogTestTheme

@Preview
@Composable
fun CatalogPreview() {
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
        Box(
            modifier = Modifier.padding(16.dp)
        )
        {
            Text(text = "Каталог товаров")
        }
        val categories = viewModel.catalog.collectAsState()
//        val categories: MutableState<List<UiCategory>> = remember {
//            mutableStateOf(
//                (1..10).map { UiCategory.test(1) }
//            )
//        }
        categories.value?.let {
            LazyColumn {
                items(it) {
                    CatalogStart(
                        modifier = Modifier.weight(1f),
                        it
                    ) {
//          TODO
                    }
                }
            }
        } ?: run {
            //TODO show progress
        }

        Box(modifier = Modifier.padding(16.dp, 12.dp)) {

            Button(
                onClick = { onCategorySlugClick("hhh") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "cart"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "2 160 Р",
                    color = Color.White
                )
            }

        }
    }
}

@Composable
fun CatalogStart(
    modifier: Modifier = Modifier,
    category: UiCategory,
    onClick: (UiCategory) -> Unit
) {
    Column {
        Row() {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .wrapContentHeight()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.group_10_1),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
            Text(text = "Пиломатериалы")
        }
    }
}



