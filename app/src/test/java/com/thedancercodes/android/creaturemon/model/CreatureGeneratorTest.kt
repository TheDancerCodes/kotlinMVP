package com.thedancercodes.android.creaturemon.model

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CreatureGeneratorTest {

    // Add creatureGenerator property
    private lateinit var creatureGenerator: CreatureGenerator

    // Initialize creatureGenerator in a setUp method

    @Before
    fun setUp() {
        creatureGenerator = CreatureGenerator()
    }

    // Test for HitPoint Calculation
    @Test
    fun testGenerateHitPoints() {

        // We first arrange the expected creature from the generator
        val attributes = CreatureAttributes(
                intelligence = 7,
                strength = 3,
                endurance = 10
        )
        val name = "Lookachu"
        val expectedCreature = Creature(attributes, 84, name)

        // We then perform the action we're testing on the generator &
        // assert that the value is equal to the expectedCreature
        assertEquals(expectedCreature, creatureGenerator.generateCreature(attributes, name))
    }
}