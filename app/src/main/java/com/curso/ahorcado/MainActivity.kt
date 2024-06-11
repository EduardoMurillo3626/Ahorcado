package com.curso.ahorcado

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var wordTextView: TextView
    private lateinit var letterInput: EditText
    private lateinit var guessButton: Button
    private lateinit var attemptsTextView: TextView
    private lateinit var messageTextView: TextView

    private val words = listOf("charmander", "bulbasaur", "squirtle", "chikorita", "cyndaquil","totodile","treecko",
        "torchic","mudkip","turtwig","chimchar","piplup")
    private lateinit var currentWord: String
    private var displayedWord: StringBuilder = StringBuilder()
    private var attempts = 0
    private val maxAttempts = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordTextView = findViewById(R.id.wordTextView)
        letterInput = findViewById(R.id.letterInput)
        guessButton = findViewById(R.id.guessButton)
        attemptsTextView = findViewById(R.id.attemptsTextView)
        messageTextView = findViewById(R.id.messageTextView)

        startNewGame()

        guessButton.setOnClickListener {
            val letter = letterInput.text.toString()
            if (letter.isNotEmpty()) {
                checkLetter(letter[0])
                letterInput.text.clear()
            }
        }
    }

    private fun startNewGame() {
        currentWord = words.random()
        displayedWord = StringBuilder("_".repeat(currentWord.length))
        attempts = 0
        updateUI()
    }

    private fun checkLetter(letter: Char) {
        var found = false
        for (i in currentWord.indices) {
            if (currentWord[i] == letter) {
                displayedWord[i] = letter
                found = true
            }
        }

        if (!found) {
            attempts++
        }

        if (displayedWord.toString() == currentWord) {
            messageTextView.text = "¡Felicidades! Has adivinado la palabra."
            startNewGame()
        } else if (attempts >= maxAttempts) {
            messageTextView.text = "Has perdido. La palabra era: $currentWord"
            startNewGame()
        }

        updateUI()
    }

    private fun updateUI() {
        wordTextView.text = displayedWord.toString()
        attemptsTextView.text = "Intentos: $attempts"
    }
}
