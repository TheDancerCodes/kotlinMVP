package com.thedancercodes.android.creaturemon.presenter

import com.thedancercodes.android.creaturemon.model.AttributeType
import com.thedancercodes.android.creaturemon.model.Creature
import com.thedancercodes.android.creaturemon.model.CreatureAttributes
import com.thedancercodes.android.creaturemon.model.CreatureGenerator
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CreaturePresenterTest {

    // Presenter Property
    private lateinit var presenter: CreaturePresenter

    // Mock for the View that the Presenter under test will interact with.
    @Mock
    lateinit var view: CreatureContract.View

    // Property for the mock Creature Generator for the Presenter.
    @Mock
    lateinit var mockGenerator: CreatureGenerator

    @Before
    fun setUp() {

        // init the mocks
        MockitoAnnotations.initMocks(this)

        // Set up presenter to be tested.
        presenter = CreaturePresenter(mockGenerator)

        // Set presenter view to be the mock view
        presenter.setView(view)
    }

    // Test for what happens when an intelligence value is selected in the view.
    @Test
    fun testIntelligenceSelected() {
        val attributes = CreatureAttributes(10, 0, 0)

        // Arrange creature state of the presenter to a stub creature from the mock generator.
        val stubCreature = Creature(attributes, 50)

        // Use the when method for the mock generator to return stub creature.
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        // Perform the action being tested; a call to attribute selected on the Presenter
        presenter.attributeSelected(AttributeType.INTELLIGENCE, 3)

        // Verify that Presenter calls "Show Hit Points" on the View with the correct value.
        verify(view, times(1)).showHitPoints("50")
    }



}