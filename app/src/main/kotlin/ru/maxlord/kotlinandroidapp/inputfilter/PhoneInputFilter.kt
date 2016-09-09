package ru.maxlord.kotlinandroidapp.inputfilter

import android.text.InputType
import android.text.Spanned
import android.text.method.NumberKeyListener

/**
 * Фильтр для ввода номера телефона
 *
 * @author Lord (Kuleshov M.V.)
 *
 * @since 27.08.2013
 */
class PhoneInputFilter : NumberKeyListener() {
    private val mPattern: String

    init {
        mPattern = "^(\\+{1,1}(7([ ]{1,1}(\\({1,1}(\\d{1,1}(\\d{1,1}(\\d{1,1}(\\){1,1}([ ]{1,1}(\\d{1,1}(\\d{1,1}(\\d{1,1}(-(\\d{1,1}(\\d{1,1}(-(\\d{1,1}(\\d{1,1}?)?)?)?)?)?)?)?)?)?)?)?)?)?)?)?)?)?)?"
    }

    override fun getInputType(): Int {
        return InputType.TYPE_CLASS_PHONE
    }

    override fun getAcceptedChars(): CharArray {
        return charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', ' ', '(', ')')
    }

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        if (end > start) {
            val destTxt = dest.toString()
            val resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend)

            // Номер телефона должен соответствовать формату +7 (XXX) XXX-XX-XX
            if (!resultingTxt.matches(mPattern.toRegex())) {
                return ""
            }
        } else {
            // в режиме удаления
            if (dest.length <= "+7 (".length) {
                return ""
            }
        }

        return null
    }
}
