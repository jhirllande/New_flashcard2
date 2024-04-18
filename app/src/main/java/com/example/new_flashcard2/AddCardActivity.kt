package com.example.New_flashcard2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AddCardActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card) // Définit le layout de cette activité

        // Initialisation des vues
        val editTextField = findViewById<EditText>(R.id.editTextField) // Champ de texte pour la question
        val editTextField1 = findViewById<EditText>(R.id.editTextField1) // Champ de texte pour la réponse
        val showingAnswers = findViewById<ImageView>(R.id.icone_X) // Image pour afficher les réponses
        val saveAnswers = findViewById<ImageView>(R.id.icone_save) // Image pour enregistrer les réponses

        showingAnswers.setOnClickListener {
            // Action lorsque l'utilisateur clique sur l'image pour afficher les réponses
            // Retour à MainActivity
            finish() // Termine cette activité et retourne à l'activité précédente
        }

        saveAnswers.setOnClickListener {
            // Action lorsque l'utilisateur clique sur l'image pour enregistrer les réponses
            val question = editTextField.text.toString() // Récupère le texte de la question
            val answer = editTextField1.text.toString() // Récupère le texte de la réponse

            val intent = Intent()
            intent.putExtra("question", question) // Ajoute la question à l'intent
            intent.putExtra("answer", answer) // Ajoute la réponse à l'intent
            setResult(Activity.RESULT_OK, intent) // Définit le résultat de l'activité avec l'intent contenant les données
            finish() // Termine cette activité et retourne à l'activité précédente
        }
    }
}
