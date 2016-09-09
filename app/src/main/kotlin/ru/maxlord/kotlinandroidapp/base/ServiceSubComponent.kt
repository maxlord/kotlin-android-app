package ru.maxlord.kotlinandroidapp.base

import dagger.Subcomponent
import ru.maxlord.kotlinandroidapp.annotation.PerService

/**
 *
 *
 * @author Lord (Kuleshov M.V.)
 * @since 15.04.16
 */
@PerService
@Subcomponent(modules = arrayOf(ServiceModule::class))
interface ServiceSubComponent {
//    fun inject(service: RegistrationIntentService)
//    fun inject(service: GcmDataService)
}
