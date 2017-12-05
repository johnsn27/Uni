/*
Author Nathan Johnson
Program is a quiz game that can progress and reload it from a file
*/
import java.util.*;
import java.io.*;

class miniproject
{
// this is where the program starts
// this asks whether the user wants to load a previous game (saved in a file) or start new game
public static void main(String []p)
{

  String ans = input("Do you want to resume a previous game?");
  if(ans.equalsIgnoreCase("yes"))
  {

    try{
      askToResume();
    }catch(IOException e){
      e.printStackTrace();
    }

  }
  else
  {

   playGame(0, 0);

  }
   System.exit(0);

}
// this loads a saved game from a file
public static void askToResume() throws IOException
{

  BufferedReader inStream = new BufferedReader(new FileReader("mydata2.txt"));
  String[][] questionsAndScore = new String[5][2];
  String totalScore;
  String questionNumber;
  questionsAndScore[0][0] = inStream.readLine();
  questionsAndScore[0][1] = inStream.readLine();
  totalScore = questionsAndScore[0][0];
  questionNumber = questionsAndScore[0][1];
  int intTotalScore = Integer.parseInt(totalScore);
  int intQuestionNumber = Integer.parseInt(questionNumber);
  inStream.close();
  playGame(intTotalScore, intQuestionNumber);

}
// this asks a number of questions, tells the user if they are correct and keeps a score
public static void playGame(int totalScore, int questionsAsked)
{

  int questionScore = 0;
  final int questions = 5;
  Boolean correct;
  int[][] scoreAndQuestion = new int[questions][2];

  for(questionsAsked = questionsAsked; questionsAsked <questions; questionsAsked++)
  {

    String[][] questionBank = createQuestion(questions);
    int questionNumber = randomQuestionNumber(); //pass q num into different question and mark methods
    String answer = userAnswer(questionBank, questionNumber);

    if(marking(questionBank, questionNumber, answer))
    {

      questionScore = score();
      totalScore = totalScore + questionScore;

    }
    scoreAndQuestion[questionsAsked][0] = questionScore;
    scoreAndQuestion[questionsAsked][1] = questionsAsked + 1;
    try{
      writeFile(Integer.toString(totalScore), Integer.toString(questionsAsked+1));
    }catch(IOException e){
      e.printStackTrace();
    }

  }
  System.out.print("Score: ");
  quicksort(scoreAndQuestion,0,4);
  println("Result is: " + printarray(scoreAndQuestion, questionsAsked));
  System.out.print("Total Score: ");
  prInt(totalScore);


}
// setting a question and its corresponding answer and storing it in an array
public static String[][] createQuestion(int questions)
{

   Question q1 = initQuestion("Who won the Premier League in 2017?", "Chelsea");
   Question q2 = initQuestion("Who won the World Cup in 2014?", "Germany");
   Question q3 = initQuestion("Who won the Ashes in 2015?", "England");
   Question q4 = initQuestion("Who won the mens singles at Wimbledon 2017?", "Roger Federer");
   Question q5 = initQuestion("Who won the 100m sprint at the 2017 World Championships?", "Justin Gatlin");
   String[][] questionBank = new String[questions][2];
   questionBank[0][0] = questionToString(q1);
   questionBank[1][0] = questionToString(q2);
   questionBank[2][0] = questionToString(q3);
   questionBank[3][0] = questionToString(q4);
   questionBank[4][0] = questionToString(q5);
   questionBank[0][1] = answerToString(q1);
   questionBank[1][1] = answerToString(q2);
   questionBank[2][1] = answerToString(q3);
   questionBank[3][1] = answerToString(q4);
   questionBank[4][1] = answerToString(q5);
   return questionBank;

}
// getting the users answer to a question
public static String userAnswer(String[][] questionBank, int questionNumber)
{

  String question = questionBank[questionNumber][0];
  String answer = input(question);
  if(answer.equalsIgnoreCase("exit"))
  {

    System.exit(0);

  }
  return answer;

}
// this compares the users answer and the stored answer and if its correct it returns correct as true
// it also allows the user to attempt a question 3 times
public static Boolean marking(String[][] questionBank, int questionNumber, String userAnswer)
{

  Boolean correct = false;
  String correctAnswer = questionBank[questionNumber][1];

      if(userAnswer.equalsIgnoreCase(correctAnswer))
      {

        println("Correct");
        correct = true;

      }
      else
      {

        println("Wrong- try again");
        if(retryUserAnswer(questionBank, questionNumber).equalsIgnoreCase(correctAnswer))
        {

          println("Correct");
          correct = true;

        }
        else
        {

          println("Wrong- try again");
          if(retryUserAnswer(questionBank, questionNumber).equalsIgnoreCase(correctAnswer))
          {

            println("Correct");
            correct = true;

          }
          else
          {

            println("Wrong- try again");

          }
        }
      }

  return correct;
}
// the program goes here if the answer given by the user was incorrect and gets the users new answer
public static String retryUserAnswer(String[][] questionBank, int questionNumber)
{

  String question = questionBank[questionNumber][0];
  String answer = input(question);
  if(answer.equalsIgnoreCase("exit"))
  {

    System.exit(0);

  }

  return answer;
}
// this gets a random score between 1 to 6
public static int score()
{

  int score;
  score = (int)(Math.random()*6) + 1;
  return score;

}
// this get a random question number between 0 to 4
public static int randomQuestionNumber()
{

  Random rand = new Random();
  int value = rand.nextInt(4);
  return value;

}
// Convert a question record details to a String eg to print
public static String questionToString(Question q)
{

    String result = getQuestion(q);
    return result;

}
// Convert a answer record details to a String eg to print
public static String answerToString(Question q)
{

    String result = getAnswer(q);
    return result;

}
// create and initialise a question.
public static Question initQuestion(String question, String answer)
{
   Question q = new Question();

   q = setQuestion(q, question);
   q = setAnswer(q, answer);

   return q;
}
// return the question from a question record
public static String getQuestion (Question q)
{
 return q.question;
}
// return the answer from a question record
public static String getAnswer (Question q)
{
 return q.answer;
}
// set the question returning the updated record
public static Question setQuestion (Question q, String question)
{
 q.question = question;
 return q;
}
// set the answer returning the updated record
public static Question setAnswer (Question q, String answer)
{
 q.answer = answer;
 return q;
}
// general input method
public static String input(String message)
{

  Scanner scanner = new Scanner(System.in);
  println(message);
  String ans = scanner.nextLine();
  return ans;

}
// general print line method
public static void println(String m)
{

  System.out.println(m);

}
// general print integer method
public static void prInt(int i)
{

  println(Integer.toString(i));

}
// writes the amount of questions asked and the current total score to a file
public static void writeFile(String totalScore, String questionsAsked) throws IOException
{

  PrintWriter outputStream = new PrintWriter(new FileWriter("mydata2.txt"));
  BufferedReader inStream = new BufferedReader(new FileReader("mydata2.txt"));

  int NumberofNames = 3;

  String[][] questionsAndScore = new String[5][2];
  questionsAndScore[0][0] = totalScore;
  questionsAndScore[0][1] = questionsAsked;

  outputStream.println(questionsAndScore[0][0]);
  outputStream.println(questionsAndScore[0][1]);
  outputStream.close();

}
// prints the sorted score in decending order
static String printarray (int[][] array, int questionsAsked)
{
   String txt = "";
   int j;
   println("");
   for (int i=0; i < array.length; i++)
   {
     j = i+1;
     txt = txt+"Score: " + array[i][0] + " question number: " + array[i][1] + "\n";
 }
 if (array.length > 0) txt = txt+array[array.length - 1];

 return txt;

}
// quicksort algorithm that sorts scores in decending order with the question number next to each score
static void quicksort (int[][] array, int from, int upto)
{
    // print details of call


    if (from < upto)
    {

      int pivot = array[(from+upto)/2][0];
      int lower = from, upper = upto;

      while (lower <= upper)
      {
    while ((array[lower][0] < pivot) && (lower < upto)) { lower++; }
    while ((array[upper][0] > pivot) && (upper > from)) { upper--; }
        if (lower <= upper)
        {
      int tmp = array[upper][0];
      array[upper][0] = array[lower][0];
      array[lower][0] = tmp;
      tmp = array[upper][1];
      array[upper][1] = array[lower][1];
      array[lower][1] = tmp;
      lower++;
      upper--;
    }
    }

   if (from < upper) quicksort(array,from,upper);
   if (lower < upto) quicksort(array,lower,upto);

  }


 }
} //end class miniproject
class Question
{

String question;
String answer;

} // end class Question
