package ru.maxlord.kotlinandroidapp.app

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.maxlord.kotlinandroidapp.BuildConfig
import ru.maxlord.kotlinandroidapp.annotation.IOSched
import ru.maxlord.kotlinandroidapp.annotation.UISched
import ru.maxlord.kotlinandroidapp.rest.Api
import ru.maxlord.kotlinandroidapp.rest.MockApi
import ru.maxlord.kotlinandroidapp.rest.converter.DateTimeDeserializer
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Singleton


@Module class AppModule(application: KotlinAndroidApplication) {
    protected val application: KotlinAndroidApplication

    init {
        this.application = application

        Timber.plant(Timber.DebugTree())
    }

    @Provides
    @Singleton fun provideApplication(): KotlinAndroidApplication {
        return application
    }

    @Provides
    @Singleton fun provideApi(gson: Gson): Api {
        val b = Retrofit.Builder()

        val clientBuilder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        clientBuilder.addInterceptor(interceptor)

        b.client(clientBuilder.build())
        b.addConverterFactory(GsonConverterFactory.create(gson))
        b.addCallAdapterFactory(RxJavaCallAdapterFactory.create())

        val retrofit = b.baseUrl(BuildConfig.API_URL).build()

        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton fun provideMockApi(gson: Gson): MockApi {
        return MockApi()
    }

    @Provides
    @Singleton fun provideGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateTimeDeserializer())
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create()
    }

    @Provides @Singleton @IOSched fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides @Singleton @UISched fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
