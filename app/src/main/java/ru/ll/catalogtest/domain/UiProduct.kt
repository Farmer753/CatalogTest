package ru.ll.catalogtest.domain

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class UiProduct(
    val id: Int,
    val title: String,
    val slug: String,
    val sky: Int,
    val description: String,
    val price: Int,
    val priceOld: Int,
    val sizeDiscount: Int,
    val images: List<String>
) {
    companion object {
        const val TOM =
            "https://s3-alpha-sig.figma.com/img/073e/0a2a/fc2758da57ec1cfd3b55fb79d81c9c7c?Expires=1721001600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=SpVLgh6sM5suArBJfK8rhX0xVHzP9UtI~DYzMhnY3jTJ8nmboocPqtsErLBfc1tJoph417Fq~FIKt3IjZF62yZm23qdnwDcUGgxE30E63YSwpuvshnD5fMaf0SWaSzbS3l0Bdx3vypuLh7-M4lwQxHZb9ux7FodG~sFLd6K3It8kpW~ZEFsleD8L9jSK4g4aPMpqD6c2zSLkNDbksOuzodTtWg5B7TYY5iQDYuK2ZYdG1hTBWy5hMk3uzvWuL4udXMmcjeC3diLHWgGOklW3iSntkHpckdE2PAa43AZfalruMo871l6Kf0HzN18EjxcH2U4AANst5slEUjOfjjchtw__"

        fun test(): UiProduct {
            return UiProduct(
                id = Random.nextInt(),
                title = "Название ${Random.nextInt()}",
                slug = "Заголовок ${Random.nextInt()}",
                sky = Random.nextInt(),
                description = "Описание ${Random.nextInt()}",
                price = Random.nextInt(),
                priceOld = Random.nextInt(),
                sizeDiscount = Random.nextInt(),
                images = listOf(TOM)
            )
        }
    }

}

