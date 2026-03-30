package com.example.socialspark

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity for Social Spark App
 * This app provides social interaction suggestions based on time of day
 * Reference: Assignment IMAD5112 - Social Spark Application
 * Created for The Independent Institute of Education
 */

// Mohammed Nomaan Osman Notoria
//ST10508270

class MainActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var timeInput: EditText
    private lateinit var suggestionTextView: TextView
    private lateinit var getSuggestionButton: Button
    private lateinit var resetButton: Button

    // Tag for logging
    companion object {
        private const val TAG = "SocialSparkApp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: Application started")

        // Initialize UI components
        initializeViews()

        // Set up button click listeners
        setupClickListeners()

        Log.d(TAG, "onCreate: UI initialization complete")
    }

    /**
     * Initialize all UI components
     */
    private fun initializeViews() {
        timeInput = findViewById(R.id.timeInput)
        suggestionTextView = findViewById(R.id.suggestionTextView)
        getSuggestionButton = findViewById(R.id.getSuggestionButton)
        resetButton = findViewById(R.id.resetButton)

        Log.d(TAG, "initializeViews: UI components initialized")
    }

    /**
     * Set up click listeners for buttons
     */
    private fun setupClickListeners() {
        getSuggestionButton.setOnClickListener {
            Log.d(TAG, "getSuggestionButton: Button clicked")
            getSocialSpark()
        }

        resetButton.setOnClickListener {
            Log.d(TAG, "resetButton: Button clicked")
            resetApp()
        }

        Log.d(TAG, "setupClickListeners: Click listeners configured")
    }

    /**
     * Get social spark suggestion based on user input
     * Uses if statements to determine the appropriate suggestion
     */
    private fun getSocialSpark() {
        val userInput = timeInput.text.toString().trim()
        Log.d(TAG, "getSocialSpark: User input received - '$userInput'")

        // Validate input
        if (userInput.isEmpty()) {
            Log.w(TAG, "getSocialSpark: Empty input detected")
            showErrorMessage("Please enter a time of day to get a suggestion!")
            return
        }

        // Convert input to lowercase for case-insensitive comparison
        val inputLower = userInput.lowercase()
        Log.d(TAG, "getSocialSpark: Processing input - '$inputLower'")

        // Determine suggestion based on time of day using if statements
        val suggestion = when {
            inputLower.contains("morning") && !inputLower.contains("mid") -> {
                Log.i(TAG, "Morning time detected")
                "☀️ Morning Spark: Send a 'Good morning' text to a family member"
            }
            inputLower.contains("mid-morning") || (inputLower.contains("mid") && inputLower.contains("morning")) -> {
                Log.i(TAG, "Mid-morning detected")
                "💼 Mid-Morning Spark: Reach out to a colleague with a quick 'Thank you'"
            }
            inputLower.contains("afternoon") && !inputLower.contains("snack") -> {
                Log.i(TAG, "Afternoon detected")
                "😄 Afternoon Spark: Share a funny meme or interesting link with a friend"
            }
            inputLower.contains("snack") || inputLower.contains("afternoon snack") -> {
                Log.i(TAG, "Afternoon snack time detected")
                "💭 Snack Time Spark: Send a quick 'thinking of you' message"
            }
            inputLower.contains("dinner") -> {
                Log.i(TAG, "Dinner time detected")
                "📞 Dinner Spark: Call a friend or relative for a 5-minute catch-up"
            }
            inputLower.contains("night") || inputLower.contains("evening") ||
                    (inputLower.contains("after") && inputLower.contains("dinner")) -> {
                Log.i(TAG, "Night/Evening time detected")
                "🌙 Night Spark: Leave a thoughtful comment on a friend's post"
            }
            else -> {
                Log.w(TAG, "Invalid input: '$userInput'")
                showErrorMessage("Please enter a valid time: Morning, Mid-morning, Afternoon, Afternoon Snack, Dinner, or Night")
                return
            }
        }

        // Display the suggestion
        suggestionTextView.text = suggestion
        suggestionTextView.visibility = android.view.View.VISIBLE
        Log.d(TAG, "getSocialSpark: Suggestion displayed successfully")

        // Show success toast
        Toast.makeText(this, "Spark suggestion ready!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Display error message to user
     * @param message The error message to display
     */
    private fun showErrorMessage(message: String) {
        Log.e(TAG, "showErrorMessage: $message")
        suggestionTextView.text = message
        suggestionTextView.visibility = android.view.View.VISIBLE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Reset the app - clear input and suggestion
     */
    private fun resetApp() {
        Log.d(TAG, "resetApp: Resetting application")

        // Clear input field
        timeInput.text.clear()

        // Clear suggestion text
        suggestionTextView.text = getString(R.string.default_suggestion_text)
        suggestionTextView.visibility = android.view.View.VISIBLE

        // Show confirmation toast
        Toast.makeText(this, "App reset successfully!", Toast.LENGTH_SHORT).show()

        Log.d(TAG, "resetApp: Reset complete")
    }
}