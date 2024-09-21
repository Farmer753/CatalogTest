package ru.ll.catalogtest.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ll.catalogtest.domain.ProductsRepository
import ru.ll.catalogtest.domain.UiCategory
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class CatalogViewModel @Inject constructor(
    val productsRepository: ProductsRepository
) : ViewModel() {
    private val _catalog = MutableStateFlow<List<UiCategory>?>(null)
    val catalog = _catalog.asStateFlow()

    init {
        viewModelScope.launch {
//            TODO generate data
            val data = productsRepository.getData()
            _catalog.emit(data)
//            val emit = (1..10).map { UiCategory.test(3) }
//            _catalog.emit(emit)
//            Timber.d("emit $emit")
        }
    }
}