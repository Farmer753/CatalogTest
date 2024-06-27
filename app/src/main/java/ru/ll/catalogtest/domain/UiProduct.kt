package ru.ll.catalogtest.domain

data class UiProduct(
    val title: String,
    val seoTitle: String?,
    val slug: String,
    val depth: Int,
    val icon: String,
    val webpIcon: String,
    val description: String?,
    val seoDescription: String?,
    val bannerImage: String?,
    val bannerMobileImage: String?,
    val bannerHref: String?,
    val subCategories: List<String>
)
{
    companion object{
        const val ESKARO = "https://s3-alpha-sig.figma.com/img/073e/0a2a/fc2758da57ec1cfd3b55fb79d81c9c7c?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=jvoVzD461ieUOLJGM3mVkPdtWbvIUJhJMFhxVnR~-6PWedDb9-by61w2IOxNQraWPyWCtkFSXwXrspD~vcm0icf8kBOgDACTvvuGJCnWRZtL~g06GoSTW6v2fyV1hSxvYfRT7BlsfnvA-uiPHbOEk~t-AuGZj0ksuZHb0xypLqdKsKrEtIubfWNRew188Bf-GjnPv8AVoXqCLUJ1C6iOVhNW7prbtvJ-3sXUzoxYDKHBt84dENoTDQoPQhsa4tmFpK1iNWETBv-oGqqQw6lj6xcHYJxYyTiW-0~d1TrSKeRHHlBVZoaj5w3I7GNEi-uVdfNAWMaftAibaJQWi0~UiQ__"
    }
}
//fun test():UiProduct{
//return UiProduct(
//    title = "Заголовок",
//)
//}
