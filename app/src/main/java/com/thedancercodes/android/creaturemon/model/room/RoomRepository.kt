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

package com.thedancercodes.android.creaturemon.model.room

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.thedancercodes.android.creaturemon.app.CreaturemonApplication
import com.thedancercodes.android.creaturemon.model.Creature
import com.thedancercodes.android.creaturemon.model.CreatureRepository

class RoomRepository : CreatureRepository {

  // property for the Creature DAO
  private val creatureDao: CreatureDao = CreaturemonApplication.database.creatureDao()

  // All Creatures property, that asks the DAO for all Creatures in the init block.
  private val allCreatures: LiveData<List<Creature>>

  init {
      allCreatures = creatureDao.getAllCreatures()
  }

  // Add methods from the Creature Repository interface using the AsyncTasks as needed.
  override fun saveCreature(creature: Creature) {
    InsertAsyncTask(creatureDao).execute(creature)
  }

  override fun getAllCreatures() = allCreatures

  override fun clearAllCreatures() {
    val creatureArray = allCreatures.value?.toTypedArray()

    if (creatureArray != null) {
      DeleteAsyncTask(creatureDao).execute(*creatureArray)
    }
  }

  // AsyncTask to perform the creature insert in the background
  private class InsertAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {

      // Add a line to insert the creature using the DAO
      dao.insert(params[0])
      return null
    }
  }

  // AsyncTask to perform the creature delete in the background
  private class DeleteAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {

      // Add a line delete creatures using the DAO
      dao.clearCreatures(*params)
      return null
    }
  }
}