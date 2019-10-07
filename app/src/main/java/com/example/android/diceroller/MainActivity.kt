/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.diceroller

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

const val SHAREDPREF_NAME = "diceRollerPref"
const val ROLL_COUNTER = "roll_counter"    // Use as key for storage in SharedPreferences
const val START_COUNTER = "start_counter"  // Use as key for storage in savedInstanceState Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage: ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var shakeDetector: ShakeDetector
    private var startCount = 0
    private var rollCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shakeDetector = ShakeDetector(this)
        sharedPreferences = getSharedPreferences(SHAREDPREF_NAME, Context.MODE_PRIVATE)

        setContentView(R.layout.activity_main)
        roll_button.setOnClickListener {
            rollDice()
        }
        diceImage = findViewById(R.id.dice_image) // Alternative, if not using Kotlin Android Extensions

        if (!shakeDetector.isSupported())
            no_accelerometer_message.visibility = View.VISIBLE

        /* TODO 3b: If savedInstanceState is not null, fetch the value of startCount from Bundle. */
        // startCount =
    }

    override fun onStart() {
        super.onStart()

        /* TODO 2: Increment the startCount value. */

        /* TODO 4b: Fetch the saved rollCount value from sharedPreferences. */

        start_counter.text = getString(R.string.onStart_counter, startCount)
        roll_counter.text = getString(R.string.dice_roll_counter, rollCount)
    }


    /* TODO 4a: Override the onStop and save the rollCount value in sharedPreferences. (key=ROLL_COUNTER) */


    /* TODO 1a: Override the onResume Method to do the following:
     *    If shakeDetection is supported (use isSupported method of ShakeDetector) then:
     *     - startListening for shakes using the startListening method of ShakeDetector class.
     *       Provide your own ShakeListener. (See ShakeDetector class.)
     */


    /* TODO 1b: Override the onPause Method and stopListening for shakes using the stopListening method of ShakeDetector class */


    /* TODO 3a: Override the (single-arg) onSaveInstanceState method and save the value of startCounter in the Bundle. (key=START_COUNTER) */



    private fun rollDice() {
        val drawableResource = when (Random().nextInt(6) + 1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        rollCount++
        roll_counter.text = getString(R.string.dice_roll_counter, rollCount)
        dice_image.setImageResource(drawableResource)
    }
}
