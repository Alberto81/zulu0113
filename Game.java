/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Room plaza, zapateria, tiendaRopa, peluqueria, descansillo, servicios, salida;//para poder acceder a las habitaciones
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {

        // create the rooms
        plaza = new Room("una amplia plaza redonda en el medio del centro comercial");
        zapateria= new Room("tienda de zapatos");
        tiendaRopa = new Room("una tienda de ropa");
        peluqueria = new Room("la peluqueria del centro comercial");
        descansillo = new Room("un espacio amplio al sur del centro comercial");
        servicios = new Room("los WC del centro comercial");
        salida = new Room("encontraste la salida del centro comercial!");
        // initialise room exits***modificado para la 0110
        plaza.setExits(zapateria, peluqueria, descansillo, tiendaRopa, null, null);
        zapateria.setExits(null, null, plaza, null, peluqueria, null);
        tiendaRopa.setExits(null, plaza, null, null, null, null);
        peluqueria.setExits(null, null, null, plaza, null, zapateria);
        descansillo.setExits(plaza, servicios, salida, null, null, null);
        servicios.setExits(null, null, null, descansillo, null, null);
        salida.setExits(descansillo, null, null, null, null, null);

        currentRoom = plaza;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();//codigo remplazado por metodo
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } 
        else if (commandWord.equals("portal")) {
            abrePortal(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        Room nextRoom = currentRoom.getExit(command.getSecondWord());

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;//cambio de habitacion
            printLocationInfo();//codigo remplazado por metodo
        }
    }

    //0113 escoge room
    private Room selectRoom(Command command)
    {

        Room destino = null;
        String portalA =  command.getTerceraPalabra();
        if (portalA.equals("plaza")){
            destino=plaza ;
        }else if(portalA.equals("zapateria")){
            destino=zapateria;
        }else if(portalA.equals("tiendaRopa")){
            destino=tiendaRopa;
        }else if(portalA.equals("peluqueria")){
            destino= peluqueria;
        }else if(portalA.equals("descansillo")){
            destino=descansillo;
        }else if(portalA.equals("servicios")){
            destino=servicios;
        }else if(portalA.equals("salida")){
            destino=salida;
        }else{
            System.out.println("esa habitacion no existe");//si la habitacion no existe, destino valdra null.
        }

        return destino;

    }
    private boolean dirValida(Command command)
    {
        String dir = command.getSecondWord();
        boolean valid = false;
        if (dir.equals("north")||dir.equals("south")||dir.equals("east")||dir.equals("west")||dir.equals("sureste")||dir.equals("noroeste")){
            valid = true;

        }
        return valid;
    }

    private void abrePortal(Command command)
    {
        Room destino = currentRoom.getExit(command.getSecondWord());
        String rumbo = command.getSecondWord();
        if (destino== null){//si no hay ninguna habitacion en la salida escogida le asignaremos una

            if(dirValida(command)){//comprueva que la direccion es valida
                currentRoom.setExit(rumbo, selectRoom(command));//creo la nueva salida
            }
            else{
                System.out.println("direccion no valida");
            }
            
        }else{
            System.out.println("ya existe una salida en esa direccion");
        }
        
        printLocationInfo();//para clarificar.
    }
    // ejercicio 0108, cambiar codigo repetido por petodo privado
    private void printLocationInfo()//0111 adapto este metodo para que invoque getExitString y se adapte a los atributos privados de Room
    {
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print(currentRoom.getExitString());
        System.out.println(); 
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
