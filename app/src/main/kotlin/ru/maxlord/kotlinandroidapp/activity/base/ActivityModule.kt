package ru.maxlord.kotlinandroidapp.activity.base

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.squareup.otto.Bus
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import dagger.Module
import dagger.Provides
import ru.maxlord.kotlinandroidapp.annotation.ConfigPrefs
import ru.maxlord.kotlinandroidapp.annotation.PerActivity
import ru.maxlord.kotlinandroidapp.util.AsyncBus

/**
 *
 * @author Lord (Kuleshov M.V.)
 * @since 11.01.16
 */
@Module
class ActivityModule(private val activity: RxAppCompatActivity) {
    @Provides
    @PerActivity
    fun provideActivity(): RxAppCompatActivity {
        return activity
    }

    @Provides
    @PerActivity
    fun provideBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    @Provides
    @ConfigPrefs
    @PerActivity
    fun provideConfigPrefs(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(activity)
    }

    @Provides
    @PerActivity
    fun provideBus(): Bus {
        return AsyncBus(com.squareup.otto.ThreadEnforcer.ANY, "default")
    }

//    @Provides
//    @PerActivity
//    fun provideDatabaseHerlper(): DatabaseHelper {
//        return OpenHelperManager.getHelper(activity, DatabaseHelper::class.java)
//    }
}
