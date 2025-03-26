package Phases;

import Controller.GameEngine;

import static java.lang.System.exit;

public class IssueOrders extends OrderPhase {
    public IssueOrders(GameEngine p_ge) {
        super(p_ge);
        p_ge.getCurrentPlayer().get_diplomacy_list().clear();
    }

    @Override
    public void displayOptions() {
        System.out.println("Taking commands for Player " + d_ge.getCurrentPlayer().get_playerName());
        System.out.println("Please provide a command to execute or type execute to finish giving commands");
    }

    public void deploy() {
        printInvalidCommandMessage();
    }

    @Override
    public void issueOrder() {
        d_ge.getCurrentPlayer().issue_order();
    }

    @Override
    public void endGame() {

    }
    @Override
    public void next() {
        if (d_ge.getCurrentInput().toLowerCase().contains("quit")) {
            System.out.println("Exiting program");
            exit(0);
        }
        if (d_ge.getCurrentInput().toLowerCase().contains("execute")) {
            if (d_ge.getCurrentPlayer().equals(d_ge.get_PlayersList().get(d_ge.get_PlayersList().size() - 1))) {
                d_ge.nextPlayer();
                d_ge.setPhase(new OrderExecution(d_ge));
            } else {
                d_ge.nextPlayer();
                d_ge.setPhase(new IssueOrders(d_ge));
            }
        }
    }
}