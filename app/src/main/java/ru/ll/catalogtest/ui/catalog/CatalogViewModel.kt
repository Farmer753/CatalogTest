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
    private val productsRepository: ProductsRepository
) : ViewModel() {
    private val _catalog = MutableStateFlow<List<UiCategory>?>(null)
    val catalog = _catalog.asStateFlow()

    private val _progress = MutableStateFlow<Boolean>(false)
    val progress = _progress.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                _progress.emit(true)
                _error.emit(null)
                val data: List<UiCategory> = productsRepository.getData()
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