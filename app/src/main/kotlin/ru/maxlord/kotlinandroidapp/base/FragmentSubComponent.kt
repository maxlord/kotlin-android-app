package ru.maxlord.kotlinandroidapp.base

import dagger.Subcomponent
import ru.maxlord.kotlinandroidapp.activity.main.MainFragment
import ru.maxlord.kotlinandroidapp.activity.splash.SplashFragment
import ru.maxlord.kotlinandroidapp.annotation.PerFragment


/**
 *
 *
 * @author Lord (Kuleshov M.V.)
 * @since 11.01.16
 */
@PerFragment
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentSubComponent {
//    fun provideValidator(): Validator

    fun inject(fragment: SplashFragment)
    fun inject(fragment: MainFragment)
}
