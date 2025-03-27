package Models.Orders;

import java.util.Collection;

import Models.Country;
import Models.Player;
import Models.WarMap;
import logging.LogEntryBuffer;

/**
 * This class is used to implement the data and logic of how to execute bomb order given by a player.
 *
 */
public class BombOrder implements Order{

    private Player d_player;
    /**
     * The destination countryID for this instance of order.
     */
    private int d_destCountryID;
    /**
     * This is a fully parametrized constructor for the Models.Orders class.
     *
     * @param p_destcountryID ID of the country on which to deploy the specified number of armies.
     * @param p_player
     */
    public BombOrder(int p_destcountryID, Player p_player) {
        this.d_destCountryID = p_destcountryID;
        this.d_player = p_player;
     }
    /**
     * @return the country ID to be used in the order
     */
    public int getDestCountryID() {
        return d_destCountryID;
    }

    /**
     * @param p_newCountry the country ID to be used in the order.
     */
    public void setDestCountryID(int p_newCountry) {
        this.d_destCountryID = p_newCountry;
    }
    /**
     * Execution of the logic of bombing to the specified Models.Country.
     *
     * @param p_warmap Details of values inside List Country
     */
    @Override
    public void execute(WarMap p_warmap) {
        System.out.println("\n_");
        Collection<Country> l_countryInfo = p_warmap.get_countries().values();
        for (Country country : l_countryInfo) {
            if (country.get_countryID() == d_destCountryID) {
                if(country.getD_ownerPlayer().get_diplomacy_list().contains(d_player.get_playerName())){
                    System.out.println("\n_");
                    System.out.println("Can't Bomb, since Negotiate found");
                    return;
                }
                int currentNumOfArmies = country.get_numOfArmies();
                int newNumOfArmies = currentNumOfArmies < 2 ? 0 : Math.floorDiv(currentNumOfArmies, 2);
                country.set_numOfArmies(newNumOfArmies);
                System.out.println(newNumOfArmies + " armies are left in country " + country.get_countryName());
            }
        }
        System.out.println("\n_");
        System.out.println("Country Bombed Successfully");
        LogEntryBuffer.getInstance().writeLog(this.d_player+" Bomb order "+this.toString()+" executed successfully.");
    }
}