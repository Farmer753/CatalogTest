package ru.ll.catalogtest.data

import android.util.Base64
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.domain.api.NwCategory
import ru.ll.catalogtest.domain.api.NwProduct

class Converter {
    fun convert(nwCategory: NwCategory): UiCategory {
        return UiCategory(
            title = nwCategory.title,
//            seoTitle = nwCategory.seoTitle,
            slug = nwCategory.slug,
//            depth = nwCategory.depth,
//            icon = nwCategory.icon,
            icon = Base64.encodeToString(nwCategory.icon.toByteArray(), Base64.DEFAULT),
//            webpIcon = nwCategory.webpIcon,
//            description = nwCategory.description,
//            seoDescription = nwCategory.seoDescription,
//            bannerImage = nwCategory.bannerImage,
//            bannerMobileImage = nwCategory.bannerMobileImage,
//            bannerHref = nwCategory.bannerHref,
            subCategories = nwCategory.subCategories.map { convert(it) }
        )
    }

    fun convert(nwProduct: NwProduct): UiProduct {
        return UiProduct(
            id = nwProduct.id,
            title = nwProduct.title,
            slug = nwProduct.slug,
            sku = nwProduct.sku,
            units = nwProduct.units,
            description = nwProduct.description,
            price = nwProduct.purchase.price,
            priceOld = nwProduct.purchase.priceOld,
            sizeDiscount = nwProduct.purchase.sizeDiscount,
            countAvailable = nwProduct.purchase.countAvailable,
            images = nwProduct.images.map { it.original }
        )
    }
}