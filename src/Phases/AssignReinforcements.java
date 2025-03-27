package Phases;

import java.io.IOException;
import static java.lang.System.exit;

import Controller.GameEngine;
import logging.LogWriter;

public class AssignReinforcements extends OrderPhase {
    public AssignReinforcements(GameEngine p_ge) {
        super(p_ge);
    }

    @Override
    public void displayOptions() {

        System.out.println("Please issue deploy orders for Player " + d_ge.getCurrentPlayer().get_playerName());
        System.out.println("Remaining reinforcements: " + d_ge.getCurrentPlayer().get_numOfReinforcements());


    }

    public void deploy() {
        d_ge.getCurrentPlayer().issue_order();

        if (d_ge.getCurrentPlayer().get_numOfReinforcements() == 0) {
            System.out.println("_");
            next();
        }
    }

    @Override
    public void issueOrder() {
        printInvalidCommandMessage();
    }

    @Override
    public void endGame() {
        printInvalidCommandMessage();
    }

    @Override
    public void next() {
        if (d_ge.getCurrentInput().toLowerCase().contains("quit")) {
            System.out.println("Exiting program");
    		try {
    	        LogWriter.getInstance().info.close();
    	        exit(0);
    		} catch (IOException e) {
    	        System.out.println("I/O exception closing BufferedWriter");
    	    }
        }
        if (d_ge.getCurrentPlayer().equals(d_ge.get_PlayersList().get(d_ge.get_PlayersList().size() - 1))) {
            d_ge.nextPlayer();
            d_ge.setPhase(new IssueOrders(d_ge));
        } else {
            d_ge.nextPlayer();

        }
    }
}