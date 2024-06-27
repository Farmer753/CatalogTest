package ru.ll.catalogtest.data

import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.domain.UiSubCategorie
import ru.ll.catalogtest.domain.api.NwProduct
import ru.ll.catalogtest.domain.api.NwSubCategorie

class Converter {
    fun convert(nwProduct: NwProduct): UiProduct {
        return UiProduct(
            title = nwProduct.title,
            seoTitle = nwProduct.seoTitle,
            slug = nwProduct.slug,
            depth = nwProduct.depth,
            icon = nwProduct.icon,
            webpIcon = nwProduct.webpIcon,
            description = nwProduct.description,
            seoDescription = nwProduct.seoDescription,
            bannerImage = nwProduct.bannerImage,
            bannerMobileImage = nwProduct.bannerMobileImage,
            bannerHref = nwProduct.bannerHref,
            subCategories = nwProduct.subCategories
        )
    }

    fun convert(nwSubCategorie: NwSubCategorie): UiSubCategorie {
        return UiSubCategorie(
            name = nwSubCategorie.name,
            message = nwSubCategorie.message,
            code = nwSubCategorie.code,
            status = nwSubCategorie.status
        )
    }
}