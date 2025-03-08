## Risk Warzone Game

This repository consists of the **Risk Warzone Game** built in **Java**. The developed program follows the basic structure of the **“Warzone”** version of Risk. A game setup consists of a connected graph map representing a world map, where each node is a country and each edge represents adjacency between countries. Two or more players can play by placing armies on countries they own, from which they can attack adjacent countries to conquer them. The objective of the game is to expand territories and manage reinforcements.

## Map

The game map is a connected graph where each node represents a country owned by one of the players. Edges between nodes represent adjacency between countries. The map is divided into continents, where each continent is a connected subgraph of the map graph, and every country belongs to one and only one continent. During gameplay, every country belongs to one and only one player and contains one or more armies that belong to the player owning the country. The game can be played on a predefined map, which is saved as a text file representation and loaded by the game.

## Game

The game consists of two phases:

**Startup Phase** - Players are assigned territories randomly.

**Main Play Phase** - Players take turns issuing basic orders.

## Issuing Orders

Each player, in round-robin turn order, gives one of their orders. Once all players have given their orders, the game engine executes them. The available orders are:

**Deploy**: Place armies on one of the current player’s territories.

## Executing Orders

When executing a deploy order, a number of army units are added to a territory. In the case of an attack:

The attacking player selects the number of armies to send.

If the defender has fewer armies, the attacker takes control.

The number of armies transferred is determined based on a simple calculation.

## End Condition

The game continues until a player conquers all territories or the game is manually ended.
 

## 🔗 References  

- [Warzone](https://www.warzone.com/)  
- [Domination Game Maps](https://domination.sourceforge.io/getmaps.shtml)  