package ru.maxlord.kotlinandroidapp.activity.splash

import android.R
import android.os.Bundle
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import ru.maxlord.kotlinandroidapp.activity.base.ActivityModule
import ru.maxlord.kotlinandroidapp.activity.base.ActivitySubComponent
import ru.maxlord.kotlinandroidapp.app.KotlinAndroidApplication

/**
 *
 *
 * @author Lord (Kuleshov M.V.)
 * @since 11.01.16
 */
class Splash: RxAppCompatActivity() {
    private lateinit var component: ActivitySubComponent

    fun getComponent(): ActivitySubComponent {
        return component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as KotlinAndroidApplication
        this.component = app.component().plus(ActivityModule(this))
        this.component.inject(this)

        fragmentManager.beginTransaction().add(R.id.content, SplashFragment.newInstance()).commit()
    }

    override fun onBackPressed() {

    }
}
