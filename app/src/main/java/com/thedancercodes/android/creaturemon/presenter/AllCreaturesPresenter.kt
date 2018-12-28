package com.thedancercodes.android.creaturemon.presenter

import androidx.lifecycle.LiveData
import com.thedancercodes.android.creaturemon.model.Creature
import com.thedancercodes.android.creaturemon.model.CreatureRepository
import com.thedancercodes.android.creaturemon.model.room.RoomRepository

class AllCreaturesPresenter(private val repository: CreatureRepository = RoomRepository())
    : BasePresenter<AllCreaturesContract.View>(), AllCreaturesContract.Presenter {

    override fun getAllCreatures(): LiveData<List<Creature>> {

        // Passes call along to the repository
        return repository.getAllCreatures()
    }

    override fun clearAllCreatures() {

        // Passes call to the repository & then calls the View method to show the user a message
        repository.clearAllCreatures()
        getView()?.showCreaturesCleared()
    }
}