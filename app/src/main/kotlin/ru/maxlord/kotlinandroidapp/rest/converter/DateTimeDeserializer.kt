package ru.maxlord.kotlinandroidapp.rest.converter

import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 *
 * @author Lord (Kuleshov M.V.)
 * @since 21.03.16
 */
class DateTimeDeserializer : JsonDeserializer<Date>, JsonSerializer<Date> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type,
                             jdc: JsonDeserializationContext): Date {

        val jsonDate = json.asJsonPrimitive.asString

        try {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonDate)
        } catch (e: Exception) {
            return SimpleDateFormat("yyyy-MM-dd").parse(jsonDate)
        }
    }

    override fun serialize(src: Date?, typeOfSrc: Type,
                           context: JsonSerializationContext): JsonElement {
        var result: String

        if (src == null) {
            result = ""
        } else {
            val c = Calendar.getInstance()
            c.timeInMillis = src.time

            val daySeconds = (c.get(Calendar.SECOND) +
                    c.get(Calendar.MINUTE) * 60 +
                    c.get(Calendar.HOUR) * 3600)

            if (daySeconds == 0) {
                result = SimpleDateFormat("yyyy-MM-dd").format(src)
            } else {
                result = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(src)
            }
        }

        return JsonPrimitive(result)
    }
}
