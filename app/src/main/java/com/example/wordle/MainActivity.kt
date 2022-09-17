package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import FourLetterWordList
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var randomWord = FourLetterWordList.getRandomFourLetterWord()
        val correctWordView = findViewById<TextView>(R.id.correctWordText)
        val button = findViewById<Button>(R.id.guessButton)
        val textGuess1 = findViewById<TextView>(R.id.textGuess1)
        val textCheck1 = findViewById<TextView>(R.id.textCheck1)
        val textGuess2 = findViewById<TextView>(R.id.textGuess2)
        val textCheck2 = findViewById<TextView>(R.id.textCheck2)
        val textGuess3 = findViewById<TextView>(R.id.textGuess3)
        val textCheck3 = findViewById<TextView>(R.id.textCheck3)

        // setting random word as the answer to be displayed
        correctWordView.text = randomWord

        var numOfGuesses = 3

        var userInput = findViewById<EditText>(R.id.userGuessInput)


        button.setOnClickListener {
            val userText = userInput.text.toString().uppercase()
            if (numOfGuesses == 3) {
                numOfGuesses -= 1

                textGuess1.text = userText
                textCheck1.text = checkGuess(userText, randomWord)

            } else if (numOfGuesses == 2) {
                numOfGuesses -= 1

                textGuess2.text = userText
                textCheck2.text = checkGuess(userText, randomWord)
            } else if (numOfGuesses == 1) {
                numOfGuesses -= 1

                textGuess3.text = userText
                textCheck3.text = checkGuess(userText, randomWord)
            }

            userInput.setText("")

            if (randomWord == userText || numOfGuesses == 0) {
                button.isEnabled = false
                correctWordView.isVisible = true

                var endMessage = "Sorry, you lost"

                if (randomWord == userText) {
                   var endMessage = "That's correct!"
                }
                Toast.makeText(it.context, endMessage, Toast.LENGTH_LONG).show()
            }

        }



    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            print("MyGuess: ${guess[i]}")
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}