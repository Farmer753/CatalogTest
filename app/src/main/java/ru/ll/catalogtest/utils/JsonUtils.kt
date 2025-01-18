package ru.ll.catalogtest.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64

/**
 * Wraps and unwraps String into/from Base64 format
 *
 * Use it to prevent compose navigation crashes in case of special symbols in String
 * such as "%", which ruins route parsing in compose navigation
 */
object FuckingStringSerializer : KSerializer<String> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("com.distinctline.FuckingString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(
            URLEncoder.encode(
                value, Charsets.UTF_8.name()
            )
        )
    }

    override fun deserialize(decoder: Decoder): String {
        val string = decoder.decodeString()
        return URLDecoder.decode(string, Charsets.UTF_8.name())
    }
}

fun String?.toBase64(): String {
    if (this == null) return ""
    return Base64.getEncoder().encodeToString(this.toByteArray())
}

fun String?.fromBase64(): String {
    if (this == null) return ""
    return String(Base64.getDecoder().decode(this))
}
