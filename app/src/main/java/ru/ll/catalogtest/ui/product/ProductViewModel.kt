package ru.ll.catalogtest.ui.product

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

@HiltViewModel(assistedFactory = ProductViewModel.Factory::class)
class ProductViewModel @AssistedInject constructor(
    @Assisted("slug") val slug: String,
    private val productsRepository: ProductsRepository,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("slug") slug: String
        ): ProductViewModel
    }

    private val _product = MutableStateFlow<UiProduct?>(null)
    val product = _product.asStateFlow()

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
                val data: UiProduct = productsRepository.getProduct(slug)
                _product.emit(data)
                Timber.d("product $data")
            } catch (e: Exception) {
                Timber.e(e, "Error Product")
                _error.emit(e.message ?: "Error")
            } finally {
                _progress.emit(false)
            }
        }
    }
}
