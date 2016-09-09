package ru.maxlord.kotlinandroidapp.activity.main

import ru.maxlord.kotlinandroidapp.R
import ru.maxlord.kotlinandroidapp.activity.base.SchedulersManager
import ru.maxlord.kotlinandroidapp.app.KotlinAndroidApplication
import ru.maxlord.kotlinandroidapp.base.BaseFragment
import ru.maxlord.kotlinandroidapp.rest.Api
import javax.inject.Inject


/**
 * Главный экран
 *
 * @author Lord (Kuleshov M.V.)
 * @since 11.01.16
 */
class MainFragment: BaseFragment() {
    @Inject lateinit var application: KotlinAndroidApplication
    @Inject lateinit var api: Api
    @Inject lateinit var schedulersManager: SchedulersManager

    override fun inject() {
        getComponent().inject(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_main;
    }

    companion object {
        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }
}
