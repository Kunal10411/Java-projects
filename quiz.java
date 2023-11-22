import java.util.ArrayList;
import java.util.Scanner;

class Question {
    private String questionText;
    private ArrayList<String> options;
    private int correctOption;

    public Question(String questionText, ArrayList<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctOption;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}

public class QuizGame {

    public static void main(String[] args) {
        ArrayList<Question> questions = new ArrayList<>();
        initializeQuestions(questions);

        int score = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Game!");

        for (Question question : questions) {
            question.displayQuestion();

            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();

            if (question.isCorrect(userAnswer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + question.options.get(question.correctOption - 1) + "\n");
            }
        }

        System.out.println("Quiz completed! Your final score is: " + score + " out of " + questions.size());

        scanner.close();
    }

    private static void initializeQuestions(ArrayList<Question> questions) {
        // Add your questions here
        // Example:
        ArrayList<String> options1 = new ArrayList<>();
        options1.add("Option A");
        options1.add("Option B");
        options1.add("Option C");
        options1.add("Option D");
        Question question1 = new Question("?", options1, 3);
        questions.add(question1);

        ArrayList<String> options2 = new ArrayList<>();
        options2.add("Option X");
        options2.add("Option Y");
        options2.add("Option Z");
        options2.add("Option W");
        Question question2 = new Question("What is the largest planet in our solar system?", options2, 1);
        questions.add(question2);

        // Add more questions as needed
    }
}
