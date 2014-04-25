/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{  //ejercicio0111 para que los atributos de room sean privados
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    //nueva salida para ej 0110
    private Room suresteExit; 

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }
    
    public Room getExit(String direction )//ej 0111, para acceder a los atributos room que pusimos como private.
    {         //le pasamos un String y devuelve el objeto Room asociado.
       Room rumbo = null;
        if(direction.equals("north")) {
            rumbo = northExit;
        }else if(direction.equals("south")) {
            rumbo = southExit;
        }else if(direction.equals("est")) {
            rumbo = eastExit;
        }else if(direction.equals("west")) {
            rumbo = westExit;
        }else if(direction.equals("sureste")) {
            rumbo = suresteExit;
        }//else if (direction == null) {
         //    rumbo = null;
        //} ***este if lo saltamos pq por defecto rumbo ya contiene null
        return rumbo;
    }
    
   
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    //añadido un parametro en la cabecera para 0110
    public void setExits(Room north, Room east, Room south, Room west, Room sureste) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;

        //if añadido para 0110    
        if(sureste != null)
            suresteExit = sureste;    
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
