package ru.maxlord.kotlinandroidapp.app

import android.app.Application

/**
 * Главный класс приложения
 */
open class KotlinAndroidApplication : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        createComponent()
        component.inject(this)
    }

    open fun createComponent(): AppComponent {
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        return component
    }

    fun component(): AppComponent {
        return component
    }
}
