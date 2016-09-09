package ru.maxlord.kotlinandroidapp.annotation

import javax.inject.Qualifier

import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@MustBeDocumented
@Retention(RUNTIME)
annotation class ConfigPrefs
