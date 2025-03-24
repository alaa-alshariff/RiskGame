package Models.Orders;

import Models.WarMap;

import java.util.Collection;

/**
 * This class is used to implement the data and logic of how to execute orders given by a player.
 *
 */

public interface Order {
    void execute(WarMap warMap);
}