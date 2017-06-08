package com.company.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Guille on 22/05/2017.
 */
public class ListGame {

    /**
     * Creamos una ArrayList de tipo Game
     */

    private ArrayList<Game> games;

    public ListGame(){
        games = new ArrayList<>();
    }

    /**
     * El metodo addGame() es el cual nos permite añadir un juego,
     * en el tambien se hace la comprobacion de que el objeto no sea un NULL
     *
     * Se llama al metodo saveGames() para otro medio de seguridad a la hora de no perder informacion.
     * @param game
     */

    public void addGame(Game game){
        if (game != null){
            games.add(game);
        }
        saveGames();
    }

    /**
     * El metodo saveGames() nos crea un archivo con el nombre que le asignemos nosotros
     * y se almacenara la informacion del ArrayList de games.
     */

    public void saveGames() {
        try {
            ObjectOutputStream listaJuegos = new ObjectOutputStream(
                    new FileOutputStream("archivos/listaJuegos.txt")
            );
            listaJuegos.writeObject( games );
            listaJuegos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * El metodo loadGames es el encargado de cargar elfichero para porder mostrar
     * la informacion por pantalla dandole la uvicacion de donde se encuentre.
     */

    public void loadGames() {

        try {
            ObjectInputStream listaJuegos = new ObjectInputStream(
                    new FileInputStream("archivos/listaJuegos.txt")
            );
            games = (ArrayList<Game>) listaJuegos.readObject();
            listaJuegos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * El metodo showGameList() es el encargado de mostrarnos la lista
     * de videojuegos añadidos.
     */

    public void showGameList(){
        for (Game game :
                games){
            System.out.println(game);
        }
    }


    /**
     * El metodo buscarGames() se encarga dela busqueda de un juego mediante indexOf al
     * introducir elnombe de dicho juego y nos lo muestra por pantalla.
     *
     * Este es otro sistema de busqueda mediante indexOf.
     *
     * public Game buscarGame(Game game){
     *  int index = this.games.indexOf(game);
     *  if (index != -1){
     *  return this.games.get(index);
     *  }
     *  return null;
     * }
     *
     * @return
     */

    public Game buscarGames(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el nombre del Videojuego");
        String nombre = scanner.nextLine();

        int index = games.indexOf( new Game(nombre) );

        if (  index != -1 ){
            return games.get( index );
        }
        System.out.println("Videojuego no encontrado");
        return null;
    }

    /**
     * El metodo deleteGames
     * @return
     */

    public Game deleteGames(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el nombre del Videojuego que desea ELIMINAR");
        String nombre = scanner.nextLine();

        int index = games.indexOf( new Game(nombre));

        if ( index != -1 ){
            System.out.println("Seguro que quiere eliminar el Videojuego Si/No");
            Scanner scanner2 = new Scanner(System.in);
            String eliminar = scanner2.nextLine();
            boolean SI = true;
            boolean NO = false;
            if (eliminar.equalsIgnoreCase(String.valueOf(SI))) {
                games.remove(index);
                return games.get(index);
            }else if (eliminar.equalsIgnoreCase(String.valueOf(NO))){
                System.out.println("Operacion cancelada");
            }
        }
        System.out.println("Videojuego no encontrado");
        return null;
    }

    /**
     * Todos los metodos Comparable siguientes son los encargados de los dierentes
     * tipos de ordenacion del programa.
     */

    public void ordenarGenero(){

        Collections.sort( games, Game.comparadorGeneroDesrrollador );

        showGameList();
    }
    public void ordenarPlataforma(){

        Collections.sort( games, Game.comparaPlataformaNombre );

        showGameList();
    }
    public void ordenarLanzamiento(){

        Collections.sort( games, Game.comparadorLanzamiento);

        showGameList();
    }
    public void ordenarPegi(){

        Collections.sort(games, Game.comparaPegi);

        showGameList();
    }

    /**
     * Este metodo compare se encarga de la ordenacion de otra manera
     * dentro de nueno programa.
     */

    public void ordenarNombre(){
        Collections.sort( games, new Game());

        showGameList();
    }
}
