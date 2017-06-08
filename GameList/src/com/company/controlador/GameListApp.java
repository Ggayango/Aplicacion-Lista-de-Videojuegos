package com.company.controlador;
import com.company.modelo.Game;
import com.company.modelo.ListGame;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Guille on 22/05/2017.
 */
public class GameListApp {

    private ListGame listGame;

    /**
     * El metodo GameListApp(), en el instaciamos listGame y
     * llamamos al metodo loadGames que se encarga de cargar el fichero con la informacion
     * de los juegos guardado anteriormente.
     */

    public GameListApp() {
        listGame = new ListGame();
        listGame.loadGames();
    }

    /**
     * El metodo start() es el encargado mediante un switch de gestionar el metodo
     * al que queramos llamar.
     */

    public void start() {
        int option;

        while ((option = showMenu()) != 0) {
            switch (option) {
                case 1:
                    listGame.addGame(askGame());
                    break;
                case 2:
                    listGame.showGameList();
                    break;
                case 3:
                    listGame.ordenarGenero();
                    break;
                case 4:
                    listGame.ordenarLanzamiento();
                    break;
                case 5:
                    listGame.ordenarPegi();
                    break;
                case 6:
                    listGame.ordenarPlataforma();
                    break;
                case 7:
                    listGame.ordenarNombre();
                    break;
                case 8:
                    Game game = listGame.buscarGames();
                    System.out.println( game );
                    break;
                case 9:
                    listGame.deleteGames();
                    break;
            }
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


    private Game askGame() {
        Scanner scanner = new Scanner(System.in);
        String nombre, genero, desarrollador, plataforma;
        int lanzamiento, jugadores, pegi;

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



            listGame.saveGames();

            return new Game(nombre, genero, desarrollador, lanzamiento, plataforma, jugadores, pegi);
        }catch ( InputMismatchException e){
            System.out.println("No has introducido un numero");
            return null;
        }
    }


    /**
     * El metodo showMenu() mueste una pequena interfaz y lee por tecaldo la
     * opcion selecionada haciendo referencia al metodo start().
     * @return
     */


    private int showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("*****************************************");
        System.out.println("*   1 - Añadir Juego                    *");
        System.out.println("*   2 - Mostrar Juegos                  *");
        System.out.println("*   3 - Ordenar Juegos por Genero       *");
        System.out.println("*   4 - Ordenar Juegos por Lanzamiento  *");
        System.out.println("*   5 - Ordenar Juegos por PEGI         *");
        System.out.println("*   6 - Ordenar Juegos por Plataforma   *");
        System.out.println("*   7 - Ordenar Juegos por Nombre       *");
        System.out.println("*   8 - Buscar Videojuego               *");
        System.out.println("*   9 - Eliminar Videojuego             *");
        System.out.println("*   0 - Salir                           *");
        System.out.println("*****************************************");
        System.out.println("Opción: ");

        option = scanner.nextInt();

        return option;
    }

}
