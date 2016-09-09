package ru.maxlord.kotlinandroidapp.inputfilter

import android.text.Editable
import android.text.Selection
import android.text.TextWatcher

/**
 * Обработчик ввода номера телефона
 *
 * @author Lord (Kuleshov M.V.)
 *
 * @since 27.08.2013
 */
class PhoneTextWatcher : TextWatcher {

    private var mIsFormatting: Boolean = false
    private var mDeletingHyphen: Boolean = false
    private var mHyphenStart: Int = 0
    private var mDeletingBackward: Boolean = false

    override fun afterTextChanged(text: Editable) {
        if (mIsFormatting)
            return

        mIsFormatting = true

        // If deleting hyphen, also delete character before or after it
        if (mDeletingHyphen && mHyphenStart > 0) {
            if (mDeletingBackward) {
                if (mHyphenStart - 1 < text.length) {
                    text.delete(mHyphenStart - 1, mHyphenStart)
                }
            } else if (mHyphenStart < text.length) {
                text.delete(mHyphenStart, mHyphenStart + 1)
            }
            if (text.length == 7) {
                text.delete(text.length - 1, text.length)
            }
            if (text.length == 2) {
                text.append(" (")
            }
        } else {
            if (text.length == 2) {
                // +7
                text.append(" (")
            } else if (text.length == 7) {
                text.append(") ")
            } else if (text.length == 12 || text.length == 15) {
                text.append("-")
            }
        }

        mIsFormatting = false
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        if (mIsFormatting)
            return

        // Make sure user is deleting one char, without a selection
        val selStart = Selection.getSelectionStart(s)
        val selEnd = Selection.getSelectionEnd(s)
        if (s.length > 1 // Can delete another character

                && count == 1 // Deleting only one character

                && after == 0 // Deleting

                && (s[start] == '-' || s[start] == ' ' || s[start] == '(' || s[start] == ')') // a hyphen

                && selStart == selEnd) {
            // no selection
            mDeletingHyphen = true
            mHyphenStart = start
            // Check if the user is deleting forward or backward
            if (selStart == start + 1) {
                mDeletingBackward = true
            } else {
                mDeletingBackward = false
            }
        } else {
            mDeletingHyphen = false
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

    }
}
