import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The Conversation class simulates a simple chatbot that engages in a conversation with a user.
 * The bot mirrors the user's input and responds with canned responses if the mirrored text is unchanged.
 * It also keeps track of the conversation transcript.
 */
public class Conversation {

  // Attributes 
  private Scanner scanner;  // Scanner to get user input
  private Random random;    // Random object to select canned responses
  private List<String> transcript;  // List to store the conversation transcript
  private String[] cannedResponses;  // Array to hold predefined responses for the bot

  /**
   * Constructor initializes the scanner, random object, and canned responses for the chatbot.
   */
  public Conversation() {
    scanner = new Scanner(System.in);  // To get user input
    random = new Random();  // Random object to select canned responses
    transcript = new ArrayList<>();  // List to store the conversation transcript
    cannedResponses = new String[] {"uh huh", "okay", "interesting", "I see", "I understand"};  // Predefined responses
  }

  /**
   * Starts the conversation with the user by asking for the number of conversation rounds.
   * It captures each user input, generates a bot response, and stores them in the conversation transcript.
   */
  public void chat() {
    System.out.println("Please choose a number of rounds of conversation");
    int rounds = scanner.nextInt();
    scanner.nextLine();  // Clear the buffer

    System.out.println("Hi there! What's on your mind?");

    // Save the start of the conversation
    transcript.add("TRANSCRIPT:");
    transcript.add("Hi there! What's on your mind?");

    // Loop through the conversation rounds
    for (int i = 0; i < rounds; i++) {
      System.out.println(" Enter your response:");
      String userInput = scanner.nextLine();
      transcript.add(userInput);

      // Get the bot's response based on the user's input
      String botResponse = respond(userInput);
      transcript.add(botResponse);

      // Print the bot's response
      System.out.println(botResponse);
    }
  }

  /**
   * This method prints the full transcript of the conversation.
   */
  public void printTranscript() {
    System.out.println("\n--- Conversation Transcript ---");
    // Loop through each line in the transcript and print it
    for (String line : transcript) {
      System.out.println(line);
    }
  }

  /**
   * This method mirrors the input by swapping certain words according to predefined rules.
   * For example, "I" becomes "you", "me" becomes "you", etc.
   * 
   * @param input The input string to be mirrored.
   * @return The mirrored version of the input string.
   */
  private String mirrorInput(String input) {
    String[] words = input.split(" ");  // Split the input into words
    StringBuilder mirrored = new StringBuilder();

    // Swap words based on the predefined rules
    for (String word : words) {
      switch (word) {
        case "I" -> word = "you";
        case "me" -> word = "you";
        case "my" -> word = "your";
        case "your" -> word = "my";
        case "am" -> word = "are";
        case "you" -> word = "I";
      }
      mirrored.append(word).append(" ");
    }

    return mirrored.toString().trim();  // Return the mirrored string without trailing space
  }

  /**
   * This method generates a response for the user based on the mirrored input.
   * If the mirrored input is identical to the original input, a canned response is used.
   * Otherwise, the mirrored text is returned.
   * 
   * @param inputString The user's input string.
   * @return A response from the bot.
   */
  public String respond(String inputString) {
    String mirrored = mirrorInput(inputString);

    // If the mirrored text is the same as the original, use a canned response
    if (mirrored.equals(inputString)) {
      return cannedResponses[random.nextInt(cannedResponses.length)];
    } else {
      // Otherwise, return the mirrored text
      return mirrored;
    }
  }

  /**
   * The main method that runs the chatbot by calling the chat method.
   * 
   * @param args Command-line arguments (not used in this case).
   */
  public static void main(String[] args) {
    Conversation conversation = new Conversation();
    conversation.chat();  // Start the conversation
    conversation.printTranscript();  // Print the conversation transcript
  }
}
