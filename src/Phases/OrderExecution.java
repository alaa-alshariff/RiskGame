package Phases;

import Controller.GameEngine;
import Models.Orders.Order;
import Models.Player;
import Resources.Cards;

public class OrderExecution extends Play {
    /**
     * The constructor for the Order Execution phase
     *
     * @param p_ge The Game Engine
     */
    public OrderExecution(GameEngine p_ge) {
        super(p_ge);
    }

    /**
     * The option display for the order execution phase
     */
    @Override
    public void displayOptions() {
        for (Player player : d_ge.get_PlayersList()) {
            while (true) {
                Order order = player.next_order();
                if (order == null)
                    break;
                order.execute(d_ge.get_currentMap());
            }
        }
        System.out.println("\n_");
        System.out.println("All commands executed successfully..... ");
        System.out.println("_");
        this.next();
    }

    /**
     * Prints invalid state message
     */
    @Override
    public void loadMap() {
        printInvalidCommandMessage();
    }

    /**
     * Prints invalid state message
     */
    @Override
    public void showMap() {
        printInvalidCommandMessage();
    }

    /**
     * Prints invalid state message
     */
    @Override
    public void setPlayers() {
        printInvalidCommandMessage();
    }

    /**
     * Prints invalid state message
     */
    @Override
    public void assignCountries() {
        printInvalidCommandMessage();
    }

    /**
     * Prints invalid state message
     */
    @Override
    public void deploy() {
        printInvalidCommandMessage();
    }

    /**
     * Prints invalid state message
     */
    @Override
    public void endGame() {
        printInvalidCommandMessage();
    }

    /**
     * The next function for the order execution phase
     */
    @Override
    public void next() {
        Cards.assignRandomCardsToPlayers();
        System.out.println("Assigning Reinforcements....");
        System.out.println("_");
        for (Player player : d_ge.get_PlayersList()) {
            player.set_numOfReinforcements(d_ge.getNumOfReinforcements(player));
            System.out.println("Assigned " + player.get_numOfReinforcements() + " reinforcements to player: " + player.get_playerName());
        }
        System.out.println("\n_");
        System.out.println("Taking orders from each player....");
        System.out.println("_");
        System.out.println("Please issue commands for Player " + d_ge.getCurrentPlayer().get_playerName());
        System.out.println("Remaining reinforcements: " + d_ge.getCurrentPlayer().get_numOfReinforcements());
        d_ge.get_FinishedPlayers().clear();
        d_ge.setCurrentPlayer(d_ge.get_PlayersList().get(0));
        d_ge.setPhase(new AssignReinforcements(d_ge));
        Cards.clearPlayerAcquiredTerritory();


    }
}