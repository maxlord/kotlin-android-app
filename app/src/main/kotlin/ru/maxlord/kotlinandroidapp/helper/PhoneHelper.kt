package ru.maxlord.kotlinandroidapp.helper

/**
 *
 *
 * @author Lord (Kuleshov M.V.)
 * @since 22.03.16
 */
class PhoneHelper {
    companion object {
        /**
         * Преобразует номер телефона из формата:
         * +7 (123) 456-78-90
         * в формат
         * 1234567890
         * @param phone
         * *
         * @return
         */
        fun stripPhoneNumber(phone: String): String {
            var p = phone
            if (p.startsWith("+7")) {
                p = p.substring(2)
            }

            p = p.replace("(", "")
            p = p.replace(")", "")
            p = p.replace(" ", "")
            p = p.replace("-", "")

            return p
        }


        /**
         * Форматирует номер телефона из формата 9185448339 в формат +7 (918) 544-83-39
         * @param p номер телефона в формате 9001234567
         * *
         * @return номер телефона в формате +7 (900) 123-45-66
         */
        fun formatPhone(p: String): String {
            if (p.length == 10) {
                return "+7 (${p.substring(0, 3)}) ${p.substring(3, 6)}-${p.substring(6, 8)}-${p.substring(8, 10)}"
            }


//            try {
//                val pn = PhoneNumberUtil.getInstance().parse(p, "RU")
//
//                val phone = PhoneNumberUtil.getInstance().format(pn, PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
//
//                return "+7" + phone.substring(1)
//            } catch (e: NumberParseException) {
//
//            }

            return ""
        }
    }
}
