import java.util.*;
class miniproject
{

public static void main(String []p)
{
   createAndPrintQuestion();
   System.exit(0);
}

// A simple test method setting a student record then printing it out
public static void createAndPrintQuestion()
{

   Question q1 = initQuestion("Who won the Premier League in 2017?", "Chelsea");
   int questionNumber = 1;
   String answer = input(questionToString(q1));
   if(questionNumber == 1)
   {

     if(answer.equalsIgnoreCase(getAnswer(q1)))
     {

       System.out.println("YAY");

     }
   }
   return;
}
public static String questionToString(Question q)
{
    String result = getQuestion(q);

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
}
class Question
{

String question; // The Questions full name
String answer;   // Their unique ID number

} // END class Question
