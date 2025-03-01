package View;
import java.util.Scanner;
import Resources.Commands;


/**
 * The GameEngine class represents the startup phase of the game. It serves as the central
 * component responsible for redirecting user requests to relevant functionality of the game.
 * This class acts as the core of the game's execution and coordinates the various components
 * to provide an interactive gaming experience.
 */
public class GameEngine {
    public void start_game()
    {
        try {

            System.out.print("Welcome to the WarZone Game!\n");
            System.out.print("Enter a command to proceed: \n");
            System.out.print("Possible commands are: \n");
            System.out.print("- editmap [filename]\n");
            System.out.print("- loadmap [filename]\n");
            System.out.print("- showmap all\n");
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                String userInput = scanner.nextLine();
                String[] words = userInput.split("\\s+");

                if (userInput.toLowerCase().contains(Commands.LOAD_MAP_COMMAND))
                {
                    if (words.length == 2 && words[0].equalsIgnoreCase(Commands.LOAD_MAP_COMMAND) && words[1].matches("(?i).+\\\\.map"))
                    {
                        System.out.print( words[1] + " loaded successfully!\nEnter a command to proceed:\nPossible commands are:\n");
                        System.out.print("- gameplayer -add [playername]\n");
                        System.out.print("- gameplayer -remove [playername]\n");
                        System.out.print("- assigncountries\n");
                        System.out.print("- showmap\n");

                        // Write code here
                        userInput = scanner.nextLine();
                        words = userInput.split("\\s+");

                        if (userInput.toLowerCase().contains("gameplayer"))
                        {
                            while (true)
                            {
                                if (userInput.equalsIgnoreCase(Commands.PLAYER_ADD_COMMAND))
                                {
                                    System.out.print("You're in: PLAYER_ADD_COMMAND");

                                    // Write code here

                                    break;
                                }
                                else if (userInput.equalsIgnoreCase(Commands.PLAYER_REMOVE_COMMAND))
                                {
                                    System.out.print("You're in: PLAYER_REMOVE_COMMAND");

                                    // Write code here

                                    break;
                                }
                                else
                                    System.out.print("Invalid Command! Correct syntax: gameplayer -add [playername] -remove [playername]");
                            }
                        }


                        System.out.print("Write command to assign countries to each player: ");
                        while (true)
                        {
                            userInput = scanner.nextLine();
                            if (userInput.equalsIgnoreCase(Commands.ASSIGN_COUNTRIES_COMMAND))
                            {
                                System.out.print("You're in: ASSIGN_COUNTRIES_COMMAND");

                                // Write code here

                                break;
                            }
                            else
                                System.out.print("Invalid Command! Correct syntax: assigncountries");

                        }
                        break;

                    }
                    else
                        System.out.print("Invalid Command! Correct syntax: loadmap [filename]");
                }
                else if (words[0].equalsIgnoreCase(Commands.SHOW_MAP_COMMAND))
                {
                    System.out.print("You're in: SHOW_MAP_COMMAND");
                    break;

                } else if (words.length == 2 && words[0].equalsIgnoreCase(Commands.EDIT_MAP_COMMAND) && words[1].matches("(?i).+\\\\.map"))
                {
                    System.out.print("You're in: EDIT_MAP_COMMAND");
                    break;

                } else
                {
                    System.out.print("Sorry, I couldn't understand the command you entered. Type Again!");
                }
            }

        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
}