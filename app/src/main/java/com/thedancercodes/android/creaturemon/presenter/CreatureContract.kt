package com.thedancercodes.android.creaturemon.presenter

import androidx.annotation.DrawableRes
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
        fun saveCreature()
    }

    interface View {

        // Lets the presenter tell the view to show the hit points calculated by the app model
        fun showHitPoints(hitPoints: String)

        // Tells View to show a creature avatar drawable
        fun showAvatarDrawable(@DrawableRes resourceId: Int)

        fun showCreatureSaved()
        fun showCreatureSaveError()
    }
}