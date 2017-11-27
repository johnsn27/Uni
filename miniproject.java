import java.util.*;
import java.io.*;
class miniproject
{

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
public static void askToResume() throws IOException
{

  BufferedReader inStream = new BufferedReader(new FileReader("mydata2.txt"));
  String[][] names = new String[5][2];
  String totalScore;
  String questionNumber;
  names[0][0] = inStream.readLine();
  names[0][1] = inStream.readLine();
  totalScore = names[0][0];
  questionNumber = names[0][1];
  println(totalScore);
  println(questionNumber);
  int intTotalScore = Integer.parseInt(totalScore);
  int intQuestionNumber = Integer.parseInt(questionNumber);
  inStream.close();
  playGame(intTotalScore, intQuestionNumber);

}
public static void playGame(int totalScore, int questionsAsked)
{

  int questionScore = 0;
  int questions = 5;
  Boolean correct;
  int[][] scoreAndQuestion = new int[questions][2];

  for(questionsAsked = questionsAsked; questionsAsked <questions; questionsAsked++)
  {

    createQuestion(questions);
    questionScore = score(questions);
    totalScore = totalScore + questionScore;
    scoreAndQuestion[questionsAsked][0] = questionScore;
    scoreAndQuestion[questionsAsked][1] = questionsAsked + 1;
    try{
      writeFile(Integer.toString(totalScore), Integer.toString(questionsAsked+1));
    }catch(IOException e){
      e.printStackTrace();
    }
    prInt(scoreAndQuestion[questionsAsked][0]);
    prInt(scoreAndQuestion[questionsAsked][1]);

  }
  System.out.print("Score: ");
  quicksort(scoreAndQuestion,0,4);
  report("Result is: " + printarray(scoreAndQuestion, questionsAsked));
  System.out.print("Total Score: ");
  prInt(totalScore);


}
public static void createQuestion(int questions)
{

   Question q1 = initQuestion("Who won the Premier League in 2017?", "Chelsea");
   Question q2 = initQuestion("Who won the World Cup in 2014?", "Germany");
   Question q3 = initQuestion("Who won the Ashes in 2015?", "England");
   Question q4 = initQuestion("Who won the mens singles at Wimbledon 2017?", "Roger Federer");
   Question q5 = initQuestion("Who won the 100m sprint at the 2017 World Championships?", "Justin Gatlin");
   initArray(q1,q2,q3,q4,q5,questions);

}
public static void userAnswer(String[] questionBank, String[] answerBank)
{

  int questionNumber = randomQuestionNumber();
  String question = questionBank[questionNumber];
  String answer = input(questionBank[questionNumber]);
  if(answer.equalsIgnoreCase("exit"))
  {

    System.exit(0);

  }
  else
  {

    marking(answerBank[questionNumber], question, answer);

  }

}
public static void marking(String correctAnswer, String question, String userAnswer)
{

  Boolean correct = true;
  if(userAnswer.equalsIgnoreCase(correctAnswer))
  {

    println("Correct");

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
public static int score(int questions)
{

  int score;
  score = (int)(Math.random()*6) + 1;  //set up array and store scores for each question in it
  return score; //look at main in quicksort and try and integrate them in

}
public static void initArray(Question q1, Question q2, Question q3, Question q4, Question q5, int questions)
{

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
public static void writeFile(String totalScore, String questionsAsked) throws IOException
{

  PrintWriter outputStream = new PrintWriter(new FileWriter("mydata2.txt"));
  BufferedReader inStream = new BufferedReader(new FileReader("mydata2.txt"));

  int NumberofNames = 3;

  String[][] names = new String[5][2];
  names[0][0] = totalScore;
  names[0][1] = questionsAsked;

  outputStream.println(names[0][0]);
  outputStream.println(names[0][1]);
  outputStream.close();

}
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

static void report (String txt)
{
   println(txt);
}


// quicksort: algorithm based no Gosling's variant from nist
static void quicksort (int[][] array, int from, int upto)
{
    // print details of call


    if (from < upto)
    {


      // make the pivot value middle of array
      int pivot = array[(from+upto)/2][0];

      // set up two pointers into the array
      int lower = from, upper = upto;

      while (lower <= upper)
      {
        // first move lower up over small elements
    while ((array[lower][0] < pivot) && (lower < upto)) { lower++; }

        // otherwise move upper down over large elements
    while ((array[upper][0] > pivot) && (upper > from)) { upper--; }

        // if pointers haven't crossed
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
}
class Question
{

String question; // The Questions full name
String answer;   // Their unique ID number

} // END class Question
