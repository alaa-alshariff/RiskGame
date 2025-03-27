package Phases;

import Controller.GameEngine;
import Models.Orders.Order;
import Models.Player;
import Resources.Cards;

public class OrderExecution extends Play {
    public OrderExecution(GameEngine p_ge) {
        super(p_ge);
        d_logentrybuffer.writeLog("Order Execution Phase");
    }

    @Override
    public void displayOptions() {
        for (Player player : d_ge.get_PlayersList()) {
        	d_logentrybuffer.writeLog(player+" order executing");
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
        d_logentrybuffer.writeLog("All orders executed successfully");
        this.next();
    }

    @Override
    public void loadMap() {
        printInvalidCommandMessage();
    }

    @Override
    public void showMap() {
        printInvalidCommandMessage();
    }

    @Override
    public void setPlayers() {
        printInvalidCommandMessage();
    }

    @Override
    public void assignCountries() {
        printInvalidCommandMessage();
    }

    @Override
    public void deploy() {
        printInvalidCommandMessage();
    }

    @Override
    public void endGame() {

    }

    @Override
    public void next() {
        Cards.assignRandomCardsToPlayers();
        d_logentrybuffer.writeLog("ASSIGNREINFORCEMENTS PHASE");
        System.out.println("Assigning Reinforcements....");
        System.out.println("_");
        for (Player player : d_ge.get_PlayersList()) {
            player.set_numOfReinforcements(d_ge.getNumOfReinforcements(player));
            System.out.println("Assigned " + player.get_numOfReinforcements() + " reinforcements to player: " + player.get_playerName());
            d_logentrybuffer.writeLog("assigned "+player.get_playerName()+" "+player.get_numOfReinforcements()+" no of reinforcement armies.");
        }
        System.out.println("\n_");
        System.out.println("Taking orders from each player....");
        System.out.println("_");
        System.out.println("Please issue commands for Player " + d_ge.getCurrentPlayer().get_playerName());
        System.out.println("Remaining reinforcements: " + d_ge.getCurrentPlayer().get_numOfReinforcements());
        d_ge.get_FinishedPlayers().clear();
        d_ge.setCurrentPlayer(d_ge.get_PlayersList().get(0));
        d_ge.setPhase(new AssignReinforcements(d_ge));
        d_logentrybuffer.writeLog("ISSUE ORDERS PHASE");
        Cards.clearPlayerAcquiredTerritory();


    }
}