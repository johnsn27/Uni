import java.util.*;
class miniproject
{

public static void main(String []p)
{

   playGame();
   System.exit(0);

}

public static void playGame()
{

  int score = 0;
  Boolean correct;

  for(int questionsAsked = 0; questionsAsked <3; questionsAsked++)
  {

    createQuestion();
    score = score + score();

  }

  prInt(score);



}
public static void createQuestion()
{

   Question q1 = initQuestion("Who won the Premier League in 2017?", "Chelsea");
   Question q2 = initQuestion("Who won the World Cup in 2014?", "Germany");
   Question q3 = initQuestion("Who won the Ashes in 2015?", "England");
   Question q4 = initQuestion("Who won the mens singles at Wimbledon 2017?", "Roger Federer");
   Question q5 = initQuestion("Who won the 100m sprint at the 2017 World Championships?", "Justin Gatlin");
   initArray(q1,q2,q3,q4,q5);

}
public static void userAnswer(String[] questionBank, String[] answerBank)
{

  int questionNumber = randomQuestionNumber();
  String question = questionBank[questionNumber];
  String answer = input(questionBank[questionNumber]);
  marking(answerBank[questionNumber], question, answer);

}
public static void marking(String correctAnswer, String question, String userAnswer) //this is how to pass
{

  Boolean correct = true;
  if(userAnswer.equalsIgnoreCase(correctAnswer))
  {

    println("Woo");

  }
  else
  {

    println("Wrong- try again");
    retryUserAnswer(correctAnswer, question);

  }

}
public static void retryUserAnswer(String correctAnswer, String question)
{

  String answer = input(question);
  if(answer.equalsIgnoreCase(correctAnswer))
  {

    println("Correct");

  }
  else
  {

    println("Wrong- try again");
    answer = input(question);
    if(answer.equalsIgnoreCase(correctAnswer))
    {

      println("Correct");

    }
    else
    {

      println("Wrong- try again");

    }
  }

}
public static int score()
{

  int score;
  score = (int)(Math.random()*6) + 1;
  return score;

}
public static void initArray(Question q1, Question q2, Question q3, Question q4, Question q5)
{

  int questions = 5;
  String[] questionBank = new String[questions];
  String[] answerBank = new String[questions];
  questionBank[0] = questionToString(q1);
  questionBank[1] = questionToString(q2);
  questionBank[2] = questionToString(q3);
  questionBank[3] = questionToString(q4);
  questionBank[4] = questionToString(q5);
  answerBank[0] = answerToString(q1);
  answerBank[1] = answerToString(q2);
  answerBank[2] = answerToString(q3);
  answerBank[3] = answerToString(q4);
  answerBank[4] = answerToString(q5);
  userAnswer(questionBank, answerBank);

}
public static int randomQuestionNumber()
{

  Random rand = new Random();
  int value = rand.nextInt(4);
  return value;

}
public static String questionToString(Question q)
{

    String result = getQuestion(q);
    return result;

}
public static String answerToString(Question q)
{

    String result = getAnswer(q);
    return result;

}
public static Question initQuestion(String question, String answer)
{
   Question q = new Question();

   q = setQuestion(q, question);
   q = setAnswer(q, answer);

   return q;
}

public static String getQuestion (Question q)
{
 return q.question;
}

// Return the id from a student record
public static String getAnswer (Question q)
{
 return q.answer;
}

public static Question setQuestion (Question q, String question)
{
 q.question = question;
 return q;
}


// Set the name of a student returning the updated record
public static Question setAnswer (Question q, String answer)
{
 q.answer = answer;
 return q;
}

public static String input(String message)
{

  Scanner scanner = new Scanner(System.in);
  println(message);
  String ans = scanner.nextLine();
  return ans;

}
public static void println(String m)
{

  System.out.println(m);

}
public static void prInt(int i)
{

  println(Integer.toString(i));

}
}
class Question
{

String question; // The Questions full name
String answer;   // Their unique ID number

} // END class Question
