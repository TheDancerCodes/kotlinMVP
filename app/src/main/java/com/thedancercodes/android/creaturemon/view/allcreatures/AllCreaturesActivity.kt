package com.thedancercodes.android.creaturemon.view.allcreatures

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import com.thedancercodes.android.creaturemon.R
import com.thedancercodes.android.creaturemon.presenter.AllCreaturesContract
import com.thedancercodes.android.creaturemon.presenter.AllCreaturesPresenter
import com.thedancercodes.android.creaturemon.view.creature.CreatureActivity
import kotlinx.android.synthetic.main.activity_all_creatures.*
import kotlinx.android.synthetic.main.content_all_creatures.*

class AllCreaturesActivity : AppCompatActivity(), AllCreaturesContract.View {

  private val adapter = CreatureAdapter(mutableListOf())

  // Presenter Property
  private val presenter = AllCreaturesPresenter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_all_creatures)
    setSupportActionBar(toolbar)

    // Connect the View to the Presenter by calling the setView method from BasePresenter
    presenter.setView(this)

    // Setup Observer on the Presenter Live Data.
    // We update the RecyclerView adapter in the Observer.
    presenter.getAllCreatures().observe(this, Observer { creatures ->
      creatures?.let {
        adapter.updateCreatures(creatures)
      }
    })

    creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
    creaturesRecyclerView.adapter = adapter

    fab.setOnClickListener {
      startActivity(Intent(this, CreatureActivity::class.java))
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_clear_all -> {

        // functionality to clear all creatures
        presenter.clearAllCreatures()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }

    // New View Interface method

  }

  override fun showCreaturesCleared() {
    Toast.makeText(this, getString(R.string.creatures_cleared), Toast.LENGTH_SHORT).show()
  }
}
