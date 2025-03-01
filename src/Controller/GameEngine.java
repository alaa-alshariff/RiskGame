package Controller;
import java.util.Scanner;
import Resources.Commands;


/**
 * The GameEngine class represents the startup phase of the game. It serves as the central
 * component responsible for redirecting user requests to relevant functionality of the game.
 * This class acts as the core of the game's execution and coordinates the various components
 * to provide an interactive gaming experience.
 */
public class GameEngine {
    public static Scanner SCANNER;
    public void start_game()
    {
        SCANNER = new Scanner(System.in);
        try {
            System.out.print("Welcome to the WarZone Game!\n");
            while (true)
            {
                System.out.print("Enter a Command to proceed: ");
                String userInput = SCANNER.nextLine();

                if (userInput.toLowerCase().startsWith(Commands.LOAD_MAP_COMMAND))
                {
                    System.out.print("You're in: LOAD_MAP_COMMAND");

                    // Write code here

                    System.out.print("Write command to add/remove players: ");
                    while (true)
                    {
                        userInput = SCANNER.nextLine();
                        if (userInput.toLowerCase().startsWith(Commands.PLAYER_ADD_REMOVE_COMMAND)) {
                        System.out.print("You're in: PLAYER_ADD_REMOVE_COMMAND");

                        // Write code here

                        break;
                        }
                    }

                    System.out.print("Write command to assign countries to each player: ");
                    while (true)
                    {
                        userInput = SCANNER.nextLine();
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
                    MapEditor editor = new MapEditor();
                    editor.editMapEntry();
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
