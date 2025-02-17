import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Conversation {

  // Attributes 
  private Scanner scanner;
  private Random random;
  private List<String> transcript;
  private String[] cannedResponses;

  /**
   * Constructor 
   */
  public Conversation() {
    // To get user input
    scanner = new Scanner(System.in);  

    // A random object to pick the canned responses
    random = new Random();

    // A list to save the conversation transcript
    transcript = new ArrayList<>();

    // Set up the canned responses for the chatbot
    cannedResponses = new String[] {"uh huh", "okay", "interesting", "I see", "I understand"};
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    System.out.println("Please choose a number of rounds of conversation");
    int rounds = scanner.nextInt();
    scanner.nextLine();  

    System.out.println("Hi there!  What's on your mind?");

    // Save the start of the conversation.
    transcript.add("TRANSCRIPT:");
    transcript.add("Hi there! What's on your mind?");

    // Loop through the conversation rounds.
    for (int i = 0; i < rounds; i++) {
        // Prompt the user to enter a line of input.
        System.out.println(" Enter your response:");
        String userInput = scanner.nextLine();
        transcript.add(userInput);

        // Get the bot's response based on the user's input.
        String botResponse = respond(userInput);
        transcript.add(botResponse);

        // Print the bot's response.
        System.out.println(botResponse);
    }
  }

  /**
   * This method prints the full transcript of the conversation.
   */
  public void printTranscript() {
      System.out.println("\n--- Conversation Transcript ---");
      // Loop through each line in the transcript and print it.
      for (String line : transcript) {
          System.out.println(line);
      }
  }

  private String mirrorInput(String input) {
    String response = input.replaceAll("\\bI\\b", "you")
                           .replaceAll("\\bme\\b", "you")  
                           .replaceAll("\\bmy\\b", "your") 
                           .replaceAll("\\byour\\b", "my")
                           .replaceAll("\\bam\\b", "are"); 
    return response;
  }

  public String respond(String inputString) {
    String mirrored = mirrorInput(inputString);

    // If the mirrored text is the same as the original, use a canned response.
    if (mirrored.equals(inputString)) {
        return cannedResponses[random.nextInt(cannedResponses.length)];
    } else {
        // Otherwise, return the mirrored text.
        return mirrored;
    }
  }

  // Main method to start the chat
  public static void main(String[] args) {
      Conversation conversation = new Conversation();
      conversation.chat();
      conversation.printTranscript();
  }
}
