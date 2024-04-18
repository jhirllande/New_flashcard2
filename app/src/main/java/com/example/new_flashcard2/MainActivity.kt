package com.example.New_flashcard2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var flashcardQuestion: TextView
    private lateinit var flashcardResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Définit le layout de cette activité

        // Initialisation des vues
        flashcardQuestion = findViewById(R.id.flashcard_question) // TextView pour la question de la carte
        flashcardResponse = findViewById(R.id.flashcard_reponse) // TextView pour la réponse de la carte

        // Gestion du clic sur la question pour afficher la réponse
        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE // Masque la question
            flashcardResponse.visibility = View.VISIBLE // Affiche la réponse
        }

        // Gestion du clic sur la réponse pour revenir à la question
        flashcardResponse.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE // Affiche la question
            flashcardResponse.visibility = View.INVISIBLE // Masque la réponse
        }

        // Gestion du clic sur l'icône pour afficher les réponses
        val isShowingAnswers = findViewById<ImageView>(R.id.toggle123)
        isShowingAnswers.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java) // Crée un intent pour démarrer AddCardActivity
            resultLauncher.launch(intent) // Lance l'activité AddCardActivity et attend le résultat
        }
    }

    // Gestion du résultat retourné par AddCardActivity
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data // Récupère les données de l'intent retourné
        if (result.resultCode == Activity.RESULT_OK && data != null) {
            val question = data.getStringExtra("question") // Récupère la question de l'intent
            val answer = data.getStringExtra("answer") // Récupère la réponse de l'intent

            // Met à jour les TextView dans MainActivity avec les nouvelles données
            flashcardQuestion.text = question // Met à jour la question affichée
            flashcardResponse.text = answer // Met à jour la réponse affichée
        } else {
            Log.i("AddCardActivity", "Save operation cancelled or no data returned") // Enregistre un message dans les logs en cas d'annulation ou de données manquantes
        }
    }
}
