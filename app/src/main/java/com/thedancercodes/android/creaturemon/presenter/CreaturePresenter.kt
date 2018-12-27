package com.thedancercodes.android.creaturemon.presenter

import com.thedancercodes.android.creaturemon.model.*
import com.thedancercodes.android.creaturemon.model.room.RoomRepository


/**
 * CreaturePresenter extends the BasePresenter class typed by the ViewContract
 */
class CreaturePresenter(private val generator: CreatureGenerator = CreatureGenerator(),
                        private val repository: CreatureRepository = RoomRepository())
    : BasePresenter<CreatureContract.View>(), CreatureContract.Presenter {

    // Enables Presenter to keep track of the state of the creature being created in the Activity
    private lateinit var creature: Creature

    // Local private properties used to help keep track of the creature's state,
    // using typed inference to set the types.

    private var name = ""
    private var intelligence = 0
    private var strength = 0
    private var endurance = 0
    private var drawable = 0


    override fun updateName(name: String) {
        this.name = name
        updateCreature()
    }

    override fun attributeSelected(attributeType: AttributeType, position: Int) {
        when (attributeType) {
            AttributeType.INTELLIGENCE ->
                intelligence = AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH ->
                strength = AttributeStore.STRENGTH[position].value
            AttributeType.ENDURANCE ->
                endurance = AttributeStore.ENDURANCE[position].value
        }
        updateCreature()
    }

    // Sets local drawable & tells View to show the drawable
    override fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        getView()?.showAvatarDrawable(drawable)
        updateCreature()

    }

    override fun isDrawableSelected(): Boolean {
        return drawable != 0
    }

    // Private function to set the creature value for the Presenter based on this local state &
    //   make a call into the View to show the creature Hit Points value.
    private fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)

        creature =  generator.generateCreature(attributes, name, drawable)

        getView()?.showHitPoints(creature.hitPoints.toString())
    }

    // Helper method to prevent saving of blank attributes
    private fun canSaveCreature(): Boolean {
        return intelligence != 0 && strength != 0 && endurance != 0 &&
                name.isNotEmpty() && drawable != 0
    }

    override fun saveCreature() {
        if (canSaveCreature()) {
            repository.saveCreature(creature)
            getView()?.showCreatureSaved()
        } else {
            getView()?.showCreatureSaveError()
        }
    }
}