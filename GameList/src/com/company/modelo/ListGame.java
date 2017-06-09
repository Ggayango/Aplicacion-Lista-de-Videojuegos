package com.company.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
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
     */

    public void addGame(){
        Game game = askGame();
        if (game != null){
            games.add(game);
            saveGames();
        }
    }

    /**
     * El metodo askGame() nos pregunta sobre el videojuego que queramos
     * çañadir y sus diferentes campos.
     *
     * Di cho metodo tiene un funcion try/catch para prevenir que no se puedan
     * introducir letras u otro carateres no deseados que pueden llevar a error la aplicacion.
     *
     * en dicho metodo tambien tiene una llamada al metodo saveGames() el cual guarda cada juego introducido
     * para prevenir la perdida de informacion.
     * @return
     */

    public Game askGame(){
        Scanner scanner = new Scanner(System.in);
        String nombre, genero, desarrollador, plataforma;
        int lanzamiento, jugadores, pegi;

        Scanner confirmacion = new Scanner(System.in);

        System.out.println(" Quiere añadir u nuevo Vieojuego. ");
        String introducir = confirmacion.nextLine();

        String SI = "si";
        String NO = "no";

        if (introducir.equalsIgnoreCase(SI)) {

            try {

                do {
                    System.out.println("Videojuego: ");
                    nombre = scanner.nextLine().trim().replaceAll("\\s+", " ");
                } while (nombre.equals(""));

                do {
                    System.out.println("Genero: ");
                    genero = scanner.nextLine().trim().replaceAll("\\s+", " ");
                } while (genero.equals(""));

                do {
                    System.out.println("DesarroLladora: ");
                    desarrollador = scanner.nextLine().trim().replaceAll("\\s+", " ");
                } while (desarrollador.equals(""));

                do {
                    System.out.println("Año de Lanzamiento: ");
                    lanzamiento = scanner.nextInt();
                } while (lanzamiento <= 1950 && lanzamiento >= 3000);

                do {
                    System.out.println("Numero de Jugadores");
                    jugadores = scanner.nextInt();
                } while (jugadores == 0);

                do {
                    System.out.println("PEGI:");

                    pegi = scanner.nextInt();
                } while (pegi != 3 && pegi != 7 && pegi != 12 && pegi != 16 && pegi != 18);

                do {
                    scanner.nextLine();
                    System.out.println("Plataforma:");
                    plataforma = scanner.nextLine().trim().replaceAll("\\s+", " ");

                } while (plataforma.equals(""));

                return new Game(nombre, genero, desarrollador, lanzamiento, plataforma, jugadores, pegi);

            } catch (InputMismatchException e) {
                System.out.println("No has introducido un numero");

            }
        }else if (introducir.equalsIgnoreCase(NO)){
            System.out.println(" Añadir Viideojuego CANCELADA. ");
        }
        return null;
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
     * El metodo deleteGames() se encarga de eliminar un elemento del ArrayList
     * de Game llamado games, se le solicita al usuario el nombre del Videojuego
     * que desea borrar, tiene que dar su confirmacion para eliminar.
     * Si el juego no se encuentra saltaun mensaje.
     */

    public  void deleteGames(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Introduzca el nombre del Videojuego que desea ELIMINAR. ");
        String nombre = scanner.nextLine();

        int index = games.indexOf( new Game(nombre));

        if ( index != -1 ){
            System.out.println("Seguro que quiere eliminar el Videojuego Si/No");
            Scanner scanner2 = new Scanner(System.in);
            String eliminar = scanner2.nextLine();

            String SI = "si";
            String NO = "no";

            if (eliminar.equalsIgnoreCase(SI)) {
                games.get(index);
                games.remove(index);
                System.out.println(" Videojuego ELIMINADO");
            }else if (eliminar.equalsIgnoreCase(NO)){
                System.out.println(" Operacion cancelada");
            }
        }
        saveGames();
    }

    /**
     * El metodo editGame() es el encargado de modificar un juego introducido ya en el
     * ArraiList de Game llamado games, este pide el nombre al usuario borra dicho
     * juego y pide que introduzcas todos los campos para añadir un nuevo juego.
     */

    public  void editGames(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Introduzca el nombre del Videojuego que desea EDITAR. ");
        String nombre = scanner.nextLine();

        int index = games.indexOf( new Game(nombre));

        if ( index != -1 ){
            System.out.println(" Seguro que quiere EDITAR el Videojuego Si/No. ");
            Scanner scanner2 = new Scanner(System.in);
            String eliminar = scanner2.nextLine();

            String SI = "si";
            String NO = "no";

            if (eliminar.equalsIgnoreCase(SI)) {

                games.set(index,askGame());

            }else if (eliminar.equalsIgnoreCase(NO)){
                System.out.println(" Operacion cancelada");
            }
        }
        saveGames();
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
