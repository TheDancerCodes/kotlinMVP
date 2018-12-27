/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.thedancercodes.android.creaturemon.view.creature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.thedancercodes.android.creaturemon.R
import com.thedancercodes.android.creaturemon.model.AttributeStore
import com.thedancercodes.android.creaturemon.model.AttributeType
import com.thedancercodes.android.creaturemon.model.AttributeValue
import com.thedancercodes.android.creaturemon.model.Avatar
import com.thedancercodes.android.creaturemon.presenter.CreatureContract
import com.thedancercodes.android.creaturemon.presenter.CreaturePresenter
import com.thedancercodes.android.creaturemon.view.avatars.AvatarAdapter
import com.thedancercodes.android.creaturemon.view.avatars.AvatarBottomDialogFragment
import kotlinx.android.synthetic.main.activity_creature.*


class CreatureActivity : AppCompatActivity(), AvatarAdapter.AvatarListener, CreatureContract.View {

    // Add a property for the Presenter & instantiate it.
    private val presenter = CreaturePresenter()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_creature)

      // Tell the Presenter that this View is its View,
      // by calling the BasePresenter setView() method
      presenter.setView(this)

    // Configure methods for setting up the screen,
    configureUI()
    configureSpinnerAdapters()
    configureSpinnerListeners()
    configureEditText()
    configureClickListeners()
  }

  // Sets up the screen chrome (i.e.) back button and title
  private fun configureUI() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.add_creature)

      // A call to hideTapLabel if the Presenter says a drawable has been selected
      if (presenter.isDrawableSelected()) hideTapLabel()
  }

  // Sets up attribute spinners with static data from a Kotlin object named AttributeStore
  private fun configureSpinnerAdapters() {
    intelligence.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE)
    strength.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.STRENGTH)
    endurance.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.ENDURANCE)
  }

  // Sets up Listeners that get called when choices are made
  private fun configureSpinnerListeners() {
    intelligence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.attributeSelected(AttributeType.INTELLIGENCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.attributeSelected(AttributeType.STRENGTH, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.attributeSelected(AttributeType.ENDURANCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
  }

    // Sets up a TextChangedListener for the name EditText
  private fun configureEditText() {
    nameEditText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {}
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        presenter.updateName(s.toString())
      }
    })
  }

    // Sets up ClickListeners for showing the Avatar Chooser & for the Save button.
  private fun configureClickListeners() {
    avatarImageView.setOnClickListener {
      val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
      bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
    }

    saveButton.setOnClickListener {
      // TODO: handle save button clicked
    }
  }

    // Avatar Adapter Listener override
  override fun avatarClicked(avatar: Avatar) {
    presenter.drawableSelected(avatar.drawable)
    hideTapLabel()
  }

    // Private helper function to hide the label: "Tap to select an avatar"
  private fun hideTapLabel() {
    tapLabel.visibility = View.INVISIBLE
  }

    override fun showHitPoints(hitPoints: String) {
        this.hitPoints.text = hitPoints
    }

    override fun showAvatarDrawable(resourceId: Int) {
        avatarImageView.setImageResource(resourceId)
    }
}
