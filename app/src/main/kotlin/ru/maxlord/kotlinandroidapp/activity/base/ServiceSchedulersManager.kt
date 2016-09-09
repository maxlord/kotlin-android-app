package ru.maxlord.kotlinandroidapp.activity.base

import ru.maxlord.kotlinandroidapp.annotation.IOSched
import ru.maxlord.kotlinandroidapp.annotation.UISched
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

/**
 *
 *
 * @author Lord (Kuleshov M.V.)
 * @since 21.03.16
 */
class ServiceSchedulersManager
@Inject
constructor(@IOSched val io: Scheduler,
            @UISched val ui: Scheduler) {

    fun <T> applySchedulers(): Observable.Transformer<T, T>
            = Observable.Transformer { observable ->
        (observable as Observable)
                .subscribeOn(io)
                .observeOn(ui)
    }
}
