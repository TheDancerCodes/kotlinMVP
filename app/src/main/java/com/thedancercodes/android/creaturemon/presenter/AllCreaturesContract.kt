package com.thedancercodes.android.creaturemon.presenter

import androidx.lifecycle.LiveData
import com.thedancercodes.android.creaturemon.model.Creature

interface AllCreaturesContract {

    interface Presenter {
        fun getAllCreatures(): LiveData<List<Creature>>
        fun clearAllCreatures()
    }

    interface View {
        fun showCreaturesCleared()
    }
}