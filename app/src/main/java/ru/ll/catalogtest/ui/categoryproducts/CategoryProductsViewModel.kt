package ru.ll.catalogtest.ui.categoryproducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ll.catalogtest.domain.ProductsRepository
import ru.ll.catalogtest.domain.UiProduct
import timber.log.Timber

@HiltViewModel(assistedFactory = CategoryProductsViewModel.Factory::class)
class CategoryProductsViewModel @AssistedInject constructor(
    private val productsRepository: ProductsRepository,
    @Assisted("slug") private val slug: String
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("slug") slug: String
        ): CategoryProductsViewModel
    }

    private val _catalog = MutableStateFlow<List<UiProduct>?>(null)
    val catalog = _catalog.asStateFlow()

    private val _progress = MutableStateFlow<Boolean>(false)
    val progress = _progress.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        getData()
        Timber.d("slug: $slug")
    }

    fun getData() {
        viewModelScope.launch {
            try {
                _progress.emit(true)
                _error.emit(null)
                val data: List<UiProduct> = productsRepository.getData(slug)
                _catalog.emit(data)
            } catch (e: Exception) {
                Timber.e(e, "Error Catalog")
                _error.emit(e.message ?: "Error")
            } finally {
                _progress.emit(false)
            }
        }
    }
}