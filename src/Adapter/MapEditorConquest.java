package Adapter;

import Models.Continent;
import Models.Country;
import Models.WarMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * An adapter class used to read Conquest Map files instead of Domination map files
 */
public class MapEditorConquest {
    /**
     * Base path for where maps are saved
     */
    private static String d_base_path = System.getProperty("user.dir") + "\\Src\\Resources\\Maps";


    /**
     * ReadMap
     *
     * @param p_filename The file of the map
     * @return True if the map gets read, false otherwise
     * @throws IOException If file cannot be read
     */
    public WarMap readMap(String p_filename) throws IOException {
        WarMap p_map = new WarMap();
        BufferedReader l_bufferReader = new BufferedReader(new FileReader(d_base_path + "\\" + p_filename));
        String l_line = l_bufferReader.readLine();
        String l_readState = "";
        int l_continentCount = 0;
        List<String> l_neighborsToAdd = new ArrayList<>();
        p_map.set_mapName(p_filename);
        HashMap<String, Integer> l_continentIDmap = new HashMap<>();
        while (l_line != null) {

            if (l_line.equals("[Continents]")) { //Sets readstate to continents
                l_readState = "continents";
                l_line = l_bufferReader.readLine();
            }
            if (l_line.equals("[Territories]")) { //Sets readstate to countries
                l_readState = "countries";
                l_line = l_bufferReader.readLine();
            }

            if (l_readState.equals("continents") && l_line.length() > 0) { //Logic for adding a continent when readstate is continents
                l_continentCount++;
                List<String> l_splitLine = Arrays.asList(l_line.split("="));

                Continent l_continent = new Continent(l_continentCount, l_splitLine.get(0), Integer.parseInt(l_splitLine.get(1)));
                l_continentIDmap.put(l_splitLine.get(0), l_continentCount);
                p_map.addContinent(l_continent);
            }

            if (l_readState.equals("countries") && l_line.length() > 0) { //Logic for adding a country when readstate is countries
                l_neighborsToAdd.add(new String(l_line));
                List<String> l_splitLine = Arrays.asList(l_line.split(","));
                Country l_country = new Country(Integer.parseInt(l_splitLine.get(0)), "Country_" + l_splitLine.get(0), l_continentIDmap.get(l_splitLine.get(3)));
                p_map.addCountry(l_country);
            }
            if (l_readState.equals("borders") && l_line.length() > 0) { //Logic for adding neighbours when the readstate is borders
                List<String> l_splitLine = Arrays.asList(l_line.split(" "));
                for (int l_i = 1; l_i < l_splitLine.size(); l_i++) {
                    p_map.addNeighbour(Integer.parseInt(l_splitLine.get(0)), Integer.parseInt(l_splitLine.get(l_i)));
                }
            }

            l_line = l_bufferReader.readLine();
        }
        for (String l_s : l_neighborsToAdd) {
            List<String> l_splitLine = Arrays.asList(l_s.split(","));
            for (int l_i = 4; l_i < l_splitLine.size(); l_i++) {
                p_map.addNeighbour(Integer.parseInt(l_splitLine.get(0)), Integer.parseInt(l_splitLine.get(l_i)));
            }
        }

        return p_map;
    }

    /**
     * Save a conquest Map
     *
     * @param p_map_name The name of the map to save
     * @param p_warMap   The warmap to be saved
     */
    public void saveMap(String p_map_name, WarMap p_warMap) {

        // Checking if a map is valid before saving it.
        if (p_warMap.validateMap()) {
            try {
                FileWriter l_fstream = new FileWriter(d_base_path + "\\" + p_map_name);
                BufferedWriter l_info = new BufferedWriter(l_fstream);
                HashMap<Integer, String> l_continentStrings = new HashMap<>();
                // creating Continents Section in map File
                l_info.write("[Continents]");
                l_info.newLine();
                for (Map.Entry<Integer, Continent> l_continent : p_warMap.get_continents().entrySet()) {
                    l_info.write(l_continent.getValue().get_continentName() + "=" + l_continent.getValue().get_armyBonus());
                    l_continentStrings.put(l_continent.getKey(), l_continent.getValue().get_continentName());
                    l_info.newLine();
                }
                l_info.newLine();

                //creating Country Section in map File
                l_info.write("[Territories]");
                l_info.newLine();
                for (Map.Entry<Integer, Country> l_country : p_warMap.get_countries().entrySet()) {
                    l_info.write(l_country.getKey() + ",0,0," + l_continentStrings.get(l_country.getValue().getContinentID()));
                    HashMap<Integer, Integer> l_adjencyList = p_warMap.get_adjencyList().get(l_country.getKey());
                    for (int l_i : l_adjencyList.values()) {
                        l_info.write("," + l_i);
                    }
                    l_info.newLine();
                }

                l_info.close();

            } catch (Exception e) {
                System.out.println("Exception occured file saving a map file");
            }
        } else System.out.println("Invalid_Map");
    }

    /**
     * A function used to start editing an existing or new conquest map
     *
     * @param p_filename the location of the map to be edited
     * @return The warmap to be edited
     * @throws IOException if the file cannot be read.
     */
    public WarMap editMap(String p_filename) throws IOException {
        File l_f = new File(d_base_path, p_filename);
        if (l_f.exists()) {
            return readMap(p_filename);

        } else {
            l_f.createNewFile(); //fix this to to match where savemap links
            WarMap l_map = new WarMap();
            l_map.set_mapName(p_filename);
            return l_map;
        }

    }

}