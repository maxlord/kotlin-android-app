package ru.maxlord.kotlinandroidapp.activity.main

import android.app.Fragment
import android.os.Bundle
import ru.maxlord.kotlinandroidapp.activity.base.BaseActivity

/**
 * Главный экран приложения
 *
 * @author Lord (Kuleshov M.V.)
 * @since 11.01.16
 */
class Main: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun loadFragment(): Fragment {
        return MainFragment.newInstance()
    }
}
