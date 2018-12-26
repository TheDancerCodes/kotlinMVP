package com.thedancercodes.android.creaturemon.model

class CreatureGenerator {

    fun generateCreature(attributes: CreatureAttributes, name: String = "",
                         drawable: Int = 0): Creature {

        // Add the hitPoints calculation & return the generated creature.
        val hitPoints = 5 * attributes.intelligence +
                3 * attributes.strength +
                4 * attributes.endurance

        return Creature(attributes, hitPoints, name, drawable)

    }
}