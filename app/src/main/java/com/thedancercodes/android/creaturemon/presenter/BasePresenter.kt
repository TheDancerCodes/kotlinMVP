package com.thedancercodes.android.creaturemon.presenter

import java.lang.ref.WeakReference

/**
 * This is an abstract class that taskes a generic parameter V for the type of the View that the
 * Presenter communicates with
 */
abstract class BasePresenter<V> {

    // Nullable private property for the View, wrapped in a WeakReference.
    // We use a
    private var view: WeakReference<V>? = null

    // Add a Setter & Getter for View attached to the Presenter.

    fun setView(view: V) {
        this.view = WeakReference(view) // Wrap View in a WeakReference
    }

    // Extract the View from WeakReference in the Getter
    protected fun getView(): V? = view?.get()
}