package ru.maxlord.kotlinandroidapp.activity.base

import android.content.SharedPreferences
import com.squareup.otto.Bus
import dagger.Subcomponent
import ru.maxlord.kotlinandroidapp.activity.splash.Splash
import ru.maxlord.kotlinandroidapp.annotation.ConfigPrefs
import ru.maxlord.kotlinandroidapp.annotation.PerActivity
import ru.maxlord.kotlinandroidapp.base.FragmentModule
import ru.maxlord.kotlinandroidapp.base.FragmentSubComponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivitySubComponent {
    @ConfigPrefs fun provideConfigPrefs(): SharedPreferences
    fun provideBus(): Bus

//    fun provideDatabaseHelper(): DatabaseHelper

    operator fun plus(module: FragmentModule): FragmentSubComponent

    fun inject(activity: BaseActivity)
    fun inject(activity: BaseNoActionBarActivity)
    fun inject(activity: Splash)
}
