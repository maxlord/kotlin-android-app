package ru.maxlord.kotlinandroidapp.annotation

import javax.inject.Scope

import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 */
@Scope @Retention(RUNTIME) annotation class PerFragment
