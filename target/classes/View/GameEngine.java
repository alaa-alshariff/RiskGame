package View;
import java.util.Scanner;
import Resources.Commands;
import java.util.regex.*;

/**
 * The GameEngine class represents the startup phase of the game. It serves as the central
 * component responsible for redirecting user requests to relevant functionality of the game.
 * This class acts as the core of the game's execution and coordinates the various components
 * to provide an interactive gaming experience.
 * */
public class GameEngine {
    public void start_game()
    {
        try {

            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║      Welcome to the WarZone Game!      ║");
            System.out.println("╚════════════════════════════════════════╝");

            System.out.print("Enter a command to proceed: \n");
            System.out.print("Possible commands are: \n");
            System.out.print("- editmap [filename]\n");
            System.out.print("- loadmap [filename]\n");
            System.out.print("- showmap all\n");
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                System.out.print("Enter a Command to proceed: ");
                String userInput = scanner.nextLine();

                if (userInput.toLowerCase().contains(Commands.LOAD_MAP_COMMAND))
                {
                    System.out.print("You're in: LOAD_MAP_COMMAND");

                    // Write code here

                    System.out.print("Write command to add/remove players: ");
                    while (true)
                    {
                        userInput = scanner.nextLine();
                        if (userInput.toLowerCase().startsWith(Commands.PLAYER_ADD_REMOVE_COMMAND)) {
                        System.out.print("You're in: PLAYER_ADD_REMOVE_COMMAND");

                        // Write code here

                        break;
                        }
                    }

                    System.out.print("Write command to assign countries to each player: ");
                    while (true)
                    {
                        userInput = scanner.nextLine();
                        if (userInput.toLowerCase().startsWith(Commands.ASSIGN_COUNTRIES_COMMAND)) {
                            System.out.print("You're in: ASSIGN_COUNTRIES_COMMAND");

                            // Write code here

                            break;
                        }
                    }
                    break;

                } else if (userInput.toLowerCase().startsWith(Commands.SHOW_MAP_COMMAND)) {
                    System.out.print("You're in: SHOW_MAP_COMMAND");
                    break;

                } else if (userInput.toLowerCase().startsWith(Commands.VALIDATE_MAP_COMMAND)) {
                    System.out.print("You're in: VALIDATE_MAP_COMMAND");
                    break;

                } else if (userInput.toLowerCase().startsWith(Commands.EDIT_MAP_COMMAND)) {
                    System.out.print("You're in: EDIT_MAP_COMMAND");

                    // Write code here

                    break;

                } else  {
                    System.out.print("Invalid Command!");

                }
            }

        } catch (Exception e) {
            // Handle the exception if the user enters something that's not an integer
            System.out.println("Invalid input. ERRORRRRR!!!!!!!!!!!!");
        }
    }
}
