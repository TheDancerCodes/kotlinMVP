package com.thedancercodes.android.creaturemon.presenter

import com.thedancercodes.android.creaturemon.model.AttributeType

/**
 * Add the Contract interface here along with nested interfaces for the Presenter and View.
 */

interface CreatureContract {

    interface Presenter {
        // Methods View calls on Presenter
        fun updateName(name: String)
        fun attributeSelected(attributeType: AttributeType, position: Int)
        fun drawableSelected(drawable: Int)
        fun isDrawableSelected(): Boolean
    }
    interface View
}