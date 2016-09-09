package ru.maxlord.kotlinandroidapp

import android.content.res.Resources

/**
 * Класс функций-расширений
 *
 * @author Lord (Kuleshov M.V.)
 * @since 26.03.16
 */

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

fun Int.dip() = this * Resources.getSystem().displayMetrics.density.toInt()

