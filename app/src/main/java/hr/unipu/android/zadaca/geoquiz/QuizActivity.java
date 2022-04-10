package hr.unipu.android.zadaca.geoquiz;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hr.unipu.android.zadaca.R;

public class QuizActivity extends AppCompatActivity {

    private final Question[] quizQuestions = new Question[]{
            new Question(R.string.question1, false),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true),
            new Question(R.string.question4, false),
            new Question(R.string.question5, true)
    };

    private int currentQuestionIndex = 0;

    // UI Widgets
    private Button buttonNext;
    private Button buttonPrevious;

    private Button buttonTrueAnswer;
    private Button buttonFalseAnswer;

    private TextView currentQuestion;

    private boolean quizEnded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        buttonNext = findViewById(R.id.button_next);
        buttonPrevious = findViewById(R.id.button_previous);
        buttonTrueAnswer = findViewById(R.id.true_button);
        buttonFalseAnswer = findViewById(R.id.false_button);
        currentQuestion = findViewById(R.id.current_question_text);

        buttonNext.setOnClickListener(view -> nextQuestion());
        buttonPrevious.setOnClickListener(view -> previousQuestion());

        buttonTrueAnswer.setOnClickListener(view -> answerCurrentQuestion(true));
        buttonFalseAnswer.setOnClickListener(view -> answerCurrentQuestion(false));

        updateQuestion();
    }

    private void nextQuestion() {
        if (quizEnded) return;

        if (currentQuestionIndex == quizQuestions.length - 1) {
            endQuiz();
            return;
        }

        currentQuestionIndex = currentQuestionIndex + 1;
        updateQuestion();
    }

    private void previousQuestion() {
        if (quizEnded) return;

        if (currentQuestionIndex == 0) return;

        currentQuestionIndex = quizQuestions.length - 1;
        updateQuestion();
    }

    private void updateQuestion() {
        currentQuestion.setText(quizQuestions[currentQuestionIndex].getTextStringRes());
    }

    private void answerCurrentQuestion(final boolean answer) {
        quizQuestions[currentQuestionIndex].setAnswer(answer);
        nextQuestion();
    }

    private void endQuiz() {
        quizEnded = true;
        int correctAnswersCount = 0;
        for (Question question : quizQuestions) {
            if (question.expectedAnswer() == question.getAnswer()) {
                correctAnswersCount = correctAnswersCount + 1;
            }
        }

        final String message = "Broj točno odgovorenih pitanja: " + correctAnswersCount;
        currentQuestion.setText(message);
        Log.i("QUIZ", message);
    }
}
