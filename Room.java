import java.util.HashMap;
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
    private HashMap<String, Room> direccion;

    private String description;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;

        direccion = new HashMap<String, Room>();//creamos el hashmap y lo llenamos(ej0112)

    }

    public String getExitString()//ej 0111- metodo que devuelve una String con las salidas de la habitación-0112-adaptado a un hashmap
    {
        String exits="Exits: ";

        if (direccion.get("north") != null){
            exits = exits+" north";
        }
        if (direccion.get("south") != null){
            exits = exits+" south";
        }
        if (direccion.get("east") != null){
            exits = exits+" east";
        }
        if (direccion.get("west") != null){
            exits = exits+" west";
        }
        if (direccion.get("sureste") != null){
            exits = exits+" sureste";
        }
        if (direccion.get("noroeste") != null){
            exits = exits+" noroeste";
        }
        return exits; 
    }

    public Room getExit(String direction )
    {        

        //funcionando con hashmap(ej 0112)
        Room rumbo = direccion.get(direction);

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
    public void setExits(Room north, Room east, Room south, Room west, Room sureste, Room noroeste) 
    {
        if(north != null)
            direccion.put("north",north) ;
        if(east != null)
            direccion.put("east", east) ;
        if(south != null)
            direccion.put("south", south) ;
        if(west != null)
            direccion.put("west", west) ;
        if(sureste != null)
            direccion.put("sureste", sureste);    
        if(noroeste != null)
            direccion.put("noroeste", noroeste) ;   
    }

    //del ejercicio 13
    public void setExit(String direction, Room neighbor)
    {
        if(neighbor!= null){
            direccion.put(direction, neighbor);
        }
    }

    // create the rooms

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
