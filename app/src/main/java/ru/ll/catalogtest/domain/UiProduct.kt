package ru.ll.catalogtest.domain

import kotlinx.serialization.Serializable
import ru.ll.catalogtest.utils.FuckingStringSerializer
import kotlin.random.Random

@Serializable
data class UiProduct(
    val id: Int,
    @Serializable(with = FuckingStringSerializer::class)
    val title: String,
    val slug: String,
    val sku: Int,
    @Serializable(with = FuckingStringSerializer::class)
    val description: String?,
    val price: Int,
    val priceOld: Int,
    val sizeDiscount: Double,
//    val images: List< @Serializable(with = FuckingStringSerializer::class)String>
    val images: List<String>
) {
    companion object {

        const val PNG =
            "https://vimos.ru/u/shop/238/orig/23882485_2ce7b052b97a8bc8264136319fa4425f07e93a40e0b582435f3be5cd05ca56ce.png"

        fun test(): UiProduct {
            return UiProduct(
                id = Random.nextInt(),
                title = "Название ${Random.nextInt()}",
                slug = "Заголовок ${Random.nextInt()}",
                sku = Random.nextInt(),
                description = "Описание ${Random.nextInt()}",
                price = Random.nextInt(),
                priceOld = Random.nextInt(),
                sizeDiscount = Random.nextDouble(),
                images = listOf(PNG)
            )
        }
    }

}

