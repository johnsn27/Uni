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
  int count = 1;
  Boolean correct;
  for(int i = 1; i<2; i++)
  {

    correct = createAndPrintQuestion();

    while(!correct && count <= 3)
    {
      count++;
      println("Wrong");

    }
    prInt(score);

  }

}
public static Boolean createAndPrintQuestion()
{

   Question q1 = initQuestion("Who won the Premier League in 2017?", "Chelsea");
   int questionNumber = 1;
   String answer = input(questionToString(q1));
   Boolean b = marking(q1, answer);
   return b;

}
public static Boolean marking(Question q, String a) //this is how to pass
{

  Boolean correct = true;
  String userAnswer = a;
  Question correctAnswer = q;
  if(answerToString(q).equalsIgnoreCase(a))
  {

    println("Woo");

  }
  else
  {

    correct = false;

  }
  return correct;

}
public static int score()
{

  int score;
  score = (int)(Math.random()*6) + 1;
  System.out.println("Score: " + score);
  return score;

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
