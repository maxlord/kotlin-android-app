package ru.maxlord.kotlinandroidapp.activity.splash

import android.os.SystemClock
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import ru.maxlord.kotlinandroidapp.BuildConfig
import ru.maxlord.kotlinandroidapp.R
import ru.maxlord.kotlinandroidapp.activity.base.SchedulersManager
import ru.maxlord.kotlinandroidapp.activity.main.Main
import ru.maxlord.kotlinandroidapp.app.KotlinAndroidApplication
import ru.maxlord.kotlinandroidapp.base.BaseFragment
import ru.maxlord.kotlinandroidapp.helper.ActivityHelper
import ru.maxlord.kotlinandroidapp.rest.Api
import javax.inject.Inject

/**
 * Стартовый экран
 *
 * @author Lord (Kuleshov M.V.)
 * @since 16.03.16
 */
class SplashFragment: BaseFragment() {
    companion object {
        fun newInstance(): SplashFragment {
            val f = SplashFragment()
            return f
        }
    }

    @Inject lateinit var application: KotlinAndroidApplication
    @Inject lateinit var api: Api
    @Inject lateinit var schedulersManager: SchedulersManager

    override fun inject() {
        getComponent().inject(this)
    }

    override fun onStart() {
        super.onStart()

        IntentLauncher().start()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_splash
    }

    private inner class IntentLauncher : Thread() {
        /**
         * Sleep for some time and than start new activity.
         */
        override fun run() {
            // Crashlytics
            if (!BuildConfig.DEBUG) {
                Fabric.with(activity, Crashlytics())
            }

            SystemClock.sleep(2000)

            startMainActivity()
        }
    }

    private fun startMainActivity() {
        // Запускаем главный экран
        ActivityHelper.startActivity(activity, Main::class.java, true)
    }
}
