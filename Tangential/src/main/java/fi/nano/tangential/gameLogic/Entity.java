package fi.nano.tangential.gameLogic;

import static fi.nano.tangential.gameLogic.enums.TileType.*;

/**
 * Luokka, joka toimii pohjana Item- ja Actor-tyyppisille olioille. Sisältää
 * entiteetin nimen, symbolin ja sijainnin.
 *
 * @author Nanofus
 */
public class Entity {

    private String name;
    private Character symbol;

    private Level level;

    private Position position;

    /**
     * Luo entiteetin halutuilla parametreillä
     * @param x X-koordinaatti
     * @param y Y-koordinaatti
     * @param name Nimi
     * @param level Taso
     */
    public Entity(int x, int y, String name, Level level) {
        position = new Position(x, y);

        this.level = level;
        this.name = name;

        switch (name) {

            //Actor names
            case "Player":
                symbol = '@';
                break;
            case "Skeleton":
                symbol = 'S';
                break;
            case "Troll":
                symbol = 'T';
                break;
            case "Lizard Man":
                symbol = 'L';
                break;
            case "Dragon":
                symbol = 'D';
                break;

            //Item names
            case "Sword":
                symbol = 's';
                break;
            case "Spear":
                symbol = 'r';
                break;
            case "Mace":
                symbol = 'm';
                break;
            case "Pyrospell":
                symbol = 'p';
                break;
            case "Ice Staff":
                symbol = 'i';
                break;
            case "Wand":
                symbol = 'w';
                break;

            //NPC weapons
            case "Dragonfire":
                symbol = 'd';
                break;
                
            //Usable items
            case "Health Potion":
                symbol = 'h';
                break;
            case "Buff Potion":
                symbol = 'n';
                break;
        }

    }

    /**
     * Metodi, jolla Entity liikkuu.
     * 
     * @param x Liike x-akselilla.
     * @param y Liike y-akselilla.
     */
    public void Move(int x, int y) {
        if (level.GetTile(position.x + x, position.y + y).GetType().is(PASSABLE) && level.GetActorInTile(position.x + x, position.y + y) == null) {
            position.x = position.x + x;
            position.y = position.y + y;
        }
    }

    public Character GetSymbol() {
        return symbol;
    }

    public String GetName() {
        return name;
    }

    public Position GetPosition() {
        return position;
    }
    
    public Level GetLevel() {
        return level;
    }

    public void SetPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
