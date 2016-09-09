package ru.maxlord.kotlinandroidapp.app

import dagger.Component
import ru.maxlord.kotlinandroidapp.activity.base.ActivityModule
import ru.maxlord.kotlinandroidapp.activity.base.ActivitySubComponent
import ru.maxlord.kotlinandroidapp.annotation.IOSched
import ru.maxlord.kotlinandroidapp.annotation.UISched
import ru.maxlord.kotlinandroidapp.base.ServiceModule
import ru.maxlord.kotlinandroidapp.base.ServiceSubComponent
import ru.maxlord.kotlinandroidapp.rest.Api
import ru.maxlord.kotlinandroidapp.rest.MockApi
import rx.Scheduler
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class)) interface AppComponent {
    fun inject(application: KotlinAndroidApplication)

    fun provideApplication(): KotlinAndroidApplication
    fun provideApi(): Api
    fun provideMockApi(): MockApi

    @IOSched fun provideIoScheduler(): Scheduler

    @UISched fun provideUiScheduler(): Scheduler

    operator fun plus(module: ActivityModule): ActivitySubComponent
    operator fun plus(module: ServiceModule): ServiceSubComponent
}
