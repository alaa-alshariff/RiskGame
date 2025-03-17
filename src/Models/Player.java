package Models;

import Resources.Cards;
import Resources.Commands;

import java.util.ArrayList;
import java.util.List;

import static Controller.GameEngine.SCANNER;

/**
 * This class describes information about each player and the order that were issued using the logic
 * present in the same class.
 */
public class Player {

    /**
     * List of Countries that the player controls.
     */
    List<Country> d_playerCountries;
    /**
     * List of Continents that the player controls.
     */
    List<Continent> d_playerContinents;
    /**
     * number of reinforcements given to the player at the start of every round.
     */
    Integer d_numOfReinforcements;
    /**
     * List of player's orders for execution.
     */
    List<Orders> d_playerOrders;
    /**
     * List of Cards the player holds.
     */
    List<Cards> d_playerCards;
    /**
     * The name of the player taken by the user.
     */
    private String d_playerName;

    /**
     * This is the constructor method of the Models.Player class
     *
     * @param p_playerName is player's name.
     */
    public Player(String p_playerName) {
        this.d_playerName = p_playerName;
        this.d_numOfReinforcements = Integer.valueOf(0);
        this.d_playerOrders = new ArrayList<Orders>();
        this.d_playerCountries = new ArrayList<Country>();
        this.d_playerContinents = new ArrayList<Continent>();
    }

    /**
     * @return the player name
     */
    public String get_playerName() {
        return d_playerName;
    }

    /**
     * @param p_name the player name
     */
    public void set_playerName(String p_name) {
        this.d_playerName = p_name;
    }

    /**
     * @return a list of the player's countries
     */
    public List<Country> get_playerCountries() {
        return d_playerCountries;
    }

    /**
     * @param p_playerCountries a list of the player's countries
     */
    public void set_playerCountries(List<Country> p_playerCountries) {
        this.d_playerCountries = p_playerCountries;
    }

    /**
     * @return a list of the player's continents
     */
    public List<Continent> get_playerContinents() {
        return d_playerContinents;
    }
    /**
     * @param p_playerCards a list of the player's countries
     */
    public void set_playerCards(List<Cards> p_playerCards) {
        this.d_playerCards = p_playerCards;
    }

    /**
     * @return a list of the player's continents
     */
    public List<Cards> get_playerCards() {
        return d_playerCards;
    }

    /**
     * @param p_playerContinents a list of the player's continents
     */
    public void set_playerContinents(List<Continent> p_playerContinents) {
        this.d_playerContinents = p_playerContinents;
    }

    /**
     * @return a list of the player's orders
     */
    public List<Orders> get_playerOrder() {
        return d_playerOrders;
    }

    /**
     * @param p_playerOrder a list of the player's orders
     */
    public void set_playerOrder(List<Orders> p_playerOrder) {
        this.d_playerOrders = p_playerOrder;
    }

    /**
     * @return the number of reinforcements the player should get
     */
    public Integer get_numOfReinforcements() {
        return d_numOfReinforcements;
    }

    /**
     * @param p_armiesNumber the number of reinforcements the player should get
     */
    public void set_numOfReinforcements(Integer p_armiesNumber) {
        this.d_numOfReinforcements = p_armiesNumber;
    }

    /**
     * “issue_order()” (no parameters, no return value) whose function is
     * to add an order to the list of orders held by the
     * player when the game engine calls it during the issue orders phase.
     *
     * @param commands The following param is for the testing class only. Set to null under normal conditions.
     */
    public void issue_order(String[] commands) {
        deployOrder(commands);
        String command = SCANNER.nextLine();
        switch (command.split(" ")[0]){
            case Commands.ADVANCE_ORDER:
                //TODO: Advance order handling
                break;
            case Commands.BOMB_ORDER:
                //TODO: Bomb order handling after checking if player does holds bomb card
                break;
            case Commands.BLOCKADE_ORDER:
                //TODO: Blockade order handling after checking if player does holds blockade card
                break;
            case Commands.AIRLIFT_ORDER:
                //TODO: Airlift order handling after checking if player does holds airlift card
                break;
            case Commands.DIPLOMACY_ORDER:
                //TODO: Diplomacy order handling after checking if player does holds diplomacy card
                break;
        }
    }

    private void deployOrder(String[] commands) {
        int iterations = 0;
        while (d_numOfReinforcements != 0) {
            int countryID;
            int numOfArmies;
            System.out.println("Please issue deploy order command for Player " + d_playerName + "\nSyntax: deploy <countryID> <num>");
            System.out.println("Remaining reinforcements: " + d_numOfReinforcements);
            String command = SCANNER == null ? commands[iterations++] : SCANNER.nextLine();
            String[] commandTokens = command.split(" ");
            if (commandTokens.length != 3 || !commandTokens[0].equals(Commands.DEPLOY_COMMAND)) {
                System.out.println("Please give the command in format: " + Commands.DEPLOY_COMMAND_SYNTAX);
                continue;
            }
            try {
                countryID = Integer.parseInt(commandTokens[1]);
                numOfArmies = Integer.parseInt(commandTokens[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid CountryID or Number of Reinforcements");
                continue;
            }
            if (numOfArmies > d_numOfReinforcements) {
                System.out.println("Specified number of reinforcements exceed the available.");
                continue;
            }
            boolean countryExists = false;
            for (Country country : d_playerCountries)
                if (country.get_countryID() == countryID) {
                    countryExists = true;
                    break;
                }
            if (!countryExists) {
                System.out.println("The given CountryID is not under your control.");
                continue;
            }
            Orders order = new Orders(numOfArmies, countryID, -1, null);
            d_playerOrders.add(order);
            d_numOfReinforcements = d_numOfReinforcements - numOfArmies;
            System.out.println("Deployed All Reinforcements Successfully.");
        }
    }


    /**
     * This method is called by the GameEngine during executing order phase and
     * returns the first order in the player’s list of orders, then removes it from the list.
     */
    public Orders next_order() {
        if (d_playerOrders.isEmpty()) {
            return null; // or throw an exception if desired
        }

        Orders firstOrder = d_playerOrders.get(0);
        d_playerOrders.remove(0);
        return firstOrder;
    }
}