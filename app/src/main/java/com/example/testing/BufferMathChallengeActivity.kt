package com.example.testing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class BufferMathChallengeActivity : AppCompatActivity() {
    private lateinit var problemText: TextView
    private lateinit var problemNumberText: TextView
    private lateinit var answerInput: EditText
    private lateinit var submitButton: Button
    private lateinit var newProblemButton: Button
    private lateinit var titleText: TextView
    private lateinit var messageText: TextView
    private var currentAnswer: Int = 0
    private var problemsSolved: Int = 0
    private val requiredProblems = 3 // User must solve 3 problems to get buffer
    private var targetPackageName: String = ""
    private var targetAppName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer_math_challenge)

        problemText = findViewById(R.id.problemText)
        problemNumberText = findViewById(R.id.problemNumberText)
        answerInput = findViewById(R.id.answerInput)
        submitButton = findViewById(R.id.submitButton)
        newProblemButton = findViewById(R.id.newProblemButton)
        titleText = findViewById(R.id.titleText)
        messageText = findViewById(R.id.messageText)
        
        // Get target app info from intent
        targetPackageName = intent.getStringExtra("package_name") ?: ""
        targetAppName = intent.getStringExtra("app_name") ?: "this app"

        // Update UI text
        titleText.text = "Time Limit Reached"
        messageText.text = "You've reached your time limit for $targetAppName.\nSolve 3 math problems to get 5 minutes of extra time."

        generateNewProblem()

        submitButton.setOnClickListener {
            checkAnswer()
        }

        newProblemButton.setOnClickListener {
            generateNewProblem()
        }
    }

    private fun generateNewProblem() {
        val num1 = Random.nextInt(1, 50)
        val num2 = Random.nextInt(1, 50)
        val operation = Random.nextInt(0, 4) // 0: +, 1: -, 2: *, 3: /

        val problem: String
        val answer: Int
        
        when (operation) {
            0 -> {
                problem = "$num1 + $num2 = ?"
                answer = num1 + num2
            }
            1 -> {
                problem = "$num1 - $num2 = ?"
                answer = num1 - num2
            }
            2 -> {
                problem = "$num1 × $num2 = ?"
                answer = num1 * num2
            }
            3 -> {
                val result = num1 * num2
                problem = "$result ÷ $num2 = ?"
                answer = num1
            }
            else -> {
                problem = "$num1 + $num2 = ?"
                answer = num1 + num2
            }
        }

        problemNumberText.text = "Problem ${problemsSolved + 1} of $requiredProblems"
        problemText.text = problem
        currentAnswer = answer
        answerInput.text.clear()
        answerInput.requestFocus()
    }

    private fun checkAnswer() {
        val userAnswer = answerInput.text.toString().toIntOrNull()
        
        if (userAnswer == null) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            return
        }

        if (userAnswer == currentAnswer) {
            problemsSolved++
            Toast.makeText(this, "Correct! ${requiredProblems - problemsSolved} more to go", Toast.LENGTH_SHORT).show()
            
            if (problemsSolved >= requiredProblems) {
                // Grant buffer time
                grantBufferTime()
            } else {
                generateNewProblem()
            }
        } else {
            Toast.makeText(this, "Incorrect! Try again", Toast.LENGTH_SHORT).show()
            answerInput.text.clear()
            answerInput.requestFocus()
        }
    }
    
    private fun grantBufferTime() {
        if (targetPackageName.isNotEmpty()) {
            val prefs = getSharedPreferences("blocked_apps", Context.MODE_PRIVATE)
            
            // Get current buffer times
            val bufferTimes = prefs.getString("buffer_times", null)
                ?.split("|")
                ?.mapNotNull {
                    val parts = it.split(",")
                    if (parts.size == 2) parts[0] to (parts[1].toLongOrNull() ?: 0L) else null
                }?.toMap()?.toMutableMap() ?: mutableMapOf()
            
            // Set buffer time for this app (5 minutes = 300 seconds)
            val bufferEndTime = System.currentTimeMillis() + (5 * 60 * 1000) // 5 minutes from now
            bufferTimes[targetPackageName] = bufferEndTime
            
            // Save updated buffer times
            val bufferTimesString = bufferTimes.map { "${it.key},${it.value}" }.joinToString("|")
            prefs.edit().putString("buffer_times", bufferTimesString).apply()
            
            Toast.makeText(this, "Buffer granted! You have 5 minutes to use $targetAppName", Toast.LENGTH_LONG).show()
        }
        
        // Redirect to the target app
        redirectToTargetApp()
    }
    
    private fun redirectToTargetApp() {
        try {
            if (targetPackageName.isNotEmpty()) {
                val intent = packageManager.getLaunchIntentForPackage(targetPackageName)
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    android.util.Log.d("BufferMathChallengeActivity", "Redirected to target app: $targetPackageName")
                } else {
                    android.util.Log.e("BufferMathChallengeActivity", "Could not get launch intent for: $targetPackageName")
                    goToHome()
                }
            } else {
                android.util.Log.e("BufferMathChallengeActivity", "No target package name available")
                goToHome()
            }
        } catch (e: Exception) {
            android.util.Log.e("BufferMathChallengeActivity", "Failed to redirect to target app: ${e.message}")
            goToHome()
        }
        finish()
    }
    
    private fun goToHome() {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
    }
} 