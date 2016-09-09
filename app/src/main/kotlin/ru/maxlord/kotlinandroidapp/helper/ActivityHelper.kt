package ru.maxlord.kotlinandroidapp.helper

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import android.widget.EditText
import java.io.File

/**
 * Хелпер для работы с Активити. Содержит набор методов для работы с актитиви.
 *
 * @author Lord (Kuleshov M.V.)
 * @since 11.01.16
 */
class ActivityHelper {
    companion object {
        /**
         * Открывает активити

         * @param parentActivity
         * *            класс родительской активити
         * *
         * @param cls
         * *            класс открываемой активити
         * *
         * @param closeCurrent
         * *            нужно ли закрывать текущую активити
         * *
         * @param extras
         * *            дополнительные параметры, передаваемые в открываемую актвити
         * *
         * @throws NullPointerException
         * *
         * @throws ActivityNotFoundException
         */
        @Throws(NullPointerException::class, ActivityNotFoundException::class)
        fun startActivity(parentActivity: Activity, cls: Class<*>, closeCurrent: Boolean, extras: Bundle?) {

            val intent = Intent(parentActivity, cls)
            if (extras != null) {
                intent.putExtras(extras)
            }
            parentActivity.startActivity(intent)
            if (closeCurrent) {
                parentActivity.finish()
            }
        }

        /**
         * Открывает новую Activity и сохраняет класс текущей активити

         * @param parentActivity
         * *            - текущая активити
         * *
         * @param cls
         * *            - класс новой активити
         * *
         * @param closeCurrent
         * *            - если true, то закрывает текущую
         * *
         * @throws NullPointerException
         * *             если parentActivity или cls равны null
         * *
         * @throws ActivityNotFoundException
         * *             если активити не найдена
         */
        @Throws(NullPointerException::class, ActivityNotFoundException::class)
        fun startActivity(parentActivity: Activity, cls: Class<*>, closeCurrent: Boolean) {
            startActivity(parentActivity, cls, closeCurrent, null)
        }

        fun hideSoftKeyboardOnOutside(activity: Activity) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            val focusView = activity.currentFocus
            if (focusView != null) {
                inputMethodManager.hideSoftInputFromWindow(focusView.windowToken, 0)
            }
        }

        fun setupAutoHideKeyboardOnOutside(activity: Activity, view: View) {

            // Set up touch listener for non-text box views to hide keyboard.
            if (view !is EditText) {

                view.setOnTouchListener { v, event ->
                    hideSoftKeyboardOnOutside(activity)
                    false
                }
            }

            // If a layout container, iterate over children and seed recursion.
            if (view is ViewGroup) {

                for (i in 0..view.childCount - 1) {

                    val innerView = view.getChildAt(i)

                    setupAutoHideKeyboardOnOutside(activity, innerView)
                }
            }
        }

        /**
         * Вызывает диалог выбора приложения для открытия документа

         * @param context конекст
         * *
         * @param fileName название файла
         */
        fun openDocument(context: Context, fileName: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            val file = File(fileName)
            val extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString())
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            if (extension.equals("", ignoreCase = true) || mimeType == null) {
                // if there is no extension or there is no definite mimeType, still try to open the file
                intent.setDataAndType(Uri.fromFile(file), "text/*")
            } else {
                intent.setDataAndType(Uri.fromFile(file), mimeType)
            }

            // custom message for the intent
            context.startActivity(Intent.createChooser(intent, "Выберите приложение:"))
        }

        fun checkPlayServices(activity: Activity): Boolean {
//            val apiAvailability = GoogleApiAvailability.getInstance()
//            val resultCode = apiAvailability.isGooglePlayServicesAvailable(activity)
//            if (resultCode != ConnectionResult.SUCCESS) {
//                if (apiAvailability.isUserResolvableError(resultCode)) {
//                    activity.onUiThread {
//                        apiAvailability.getErrorDialog(activity, resultCode, SplashFragment.PLAY_SERVICES_RESOLUTION_REQUEST)
//                                .show()
//                    }
//                } else {
//                    Timber.i("This device is not supported.")
//                }
//
//                return false
//            }

            return true
        }
    }
}
