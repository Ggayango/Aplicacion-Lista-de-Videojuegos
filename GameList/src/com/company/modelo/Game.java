package com.company.modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by Guille on 22/05/2017.
 */
public class Game implements Comparable<Game>,Comparator<Game>,Serializable {

    private static final long serialVersionUID = 5871901937447754432L;
    /**
     * Inicializacion de las variables
     */

    private String nombre;
    private String genero;
    private String desarrollador;
    private int lanzamiento;
    private String plataforma;
    private int jugadores;
    private int pegi;


    //MetodosComparators

    /**
     * Metodos Comparator encargados de la comparacion mediante dos parametros diferentes
     */

    public static Comparator<Game> comparadorGeneroDesrrollador = new Comparator<Game>() {
        @Override
        public int compare(Game g1, Game g2) {
            int res = g1.getGenero().compareToIgnoreCase(g2.getGenero());
            if( res != 0) { return res; }

            return g1.getDesarrollador().compareToIgnoreCase(g2.getDesarrollador());
        }
    };

    public static Comparator<Game> comparaPlataformaNombre = new Comparator<Game>() {
        @Override
        public int compare(Game g1, Game g2) {
            int res = g1.getPlataforma().compareToIgnoreCase(g2.getPlataforma());
            if (res !=0){return res;}
            return g1.getNombre().compareToIgnoreCase(g2.getNombre());
        }
    };

    public static Comparator<Game> comparadorLanzamiento = new Comparator<Game>() {
        @Override
        public int compare(Game g1, Game g2) {
            return g1.getLanzamiento() - g2.getLanzamiento();
        }
    };

    public static Comparator<Game> comparaPegi = new Comparator<Game>() {
        @Override
        public int compare(Game g1, Game g2) {
            return g1.getPegi() - g2.getPegi();
        }
    };

    //Constructors

    /**
     * Constructor sin parametros
     */

    public Game() {

    }

    public Game(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Constructor con todas los parametros inicializadas anteriormente
     *
     * Los parametros de tipo String estan controlados por una exepcion llamada CadenaVaciaException.
     *
     * @param nombre
     * @param genero
     * @param desarrollador
     * @param lanzamiento
     * @param plataforma
     * @param jugadores
     * @param pegi
     */

    public Game(String nombre, String genero, String desarrollador, int lanzamiento, String plataforma, int jugadores, int pegi) {

        try {
            this.setNombre(nombre);
        } catch (CadenaVaciaException e) {
            e.printStackTrace();
        }
        try {
            this.setGenero( genero );
        } catch (CadenaVaciaException e) {
            e.printStackTrace();
        }
        try {
            this.setDesarrollador(desarrollador);
        } catch (CadenaVaciaException e) {
            e.printStackTrace();
        }

        this.setLanzamiento(lanzamiento);

        try {
            this.setPlataforma(plataforma);
        } catch (CadenaVaciaException e) {
            e.printStackTrace();
        }

        this.setJugadores(jugadores);

        this.setPegi(pegi);

    }

    // Getters and Setters

    //parametros de tipo String

    /**
     * Los Getters y los Setters de las variables inicializadas al inicio de la clase.
     * @return
     */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws CadenaVaciaException {

        if ( nombre.equals("")){
            throw new CadenaVaciaException("Este campo no puede estar vacio");
        }else {
            this.nombre = nombre;
        }
    }
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) throws CadenaVaciaException {

        if( genero.equals("")){
            throw new  CadenaVaciaException("La cadena no puede ser vacía");
        }else{
            this.genero = genero;
        }
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) throws CadenaVaciaException {

        if( desarrollador.equals("")){
            throw new  CadenaVaciaException("La cadena no puede ser vacía");
        }else{
            this.desarrollador = desarrollador;
        }
    }

    public int getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(int lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) throws CadenaVaciaException {

        if( plataforma.equals("")){
            throw new CadenaVaciaException("La cadena no puede ser vacía");
        }else{
            this.plataforma = plataforma;
        }
    }

    //Parametros de tipo int

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {

        this.jugadores = jugadores;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    //Method toString para mostrar por pantalla

    /**
     * Metodo toString() para mostrar por pantalla el conjunto de todas la variables.
     * @return
     */

    @Override
    public String toString() {
        return "Juego:  "  + "Nombre: " + getNombre() + "\n" +
                "        " + "Genero: " + getGenero() + "\n" +
                "        " + "Desarrollador: " + getDesarrollador() + "\n" +
                "        " + "Año de Lanzamiento: " + getLanzamiento() + "\n" +
                "        " + "Plataforma: " + getPlataforma() + "\n" +
                "        " + "Numero de Jugadores: " + getJugadores() + "\n" +
                "        " + "PEGI: " + "+" + getPegi() + "\n";
    }

    //compareTo

    /**
     * Metodo compareTo que nos ordena los juegos por nombre
     * @param game
     * @return
     */

    @Override
    public int compareTo(Game game) {
        return this.getNombre().compareToIgnoreCase(game.getNombre());
    }

    //compare

    /**
     * Metodo compara para ordenar por nombre
     * @param g1
     * @param g2
     * @return
     */

    @Override
    public int compare(Game g1, Game g2) {
        int res;

        res = g1.getNombre().compareToIgnoreCase(g2.getNombre()) ;

        if (res != 0){
            return res;
        }
        return g1.getLanzamiento() - g2.getLanzamiento();
    }

    /**
     * Metodo equals para realizar las busquedas por medio de indexOf
     * @param obj
     * @return
     */

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        }
        if (obj == null){
            return false;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }
        Game game = (Game) obj;

        return Objects.equals( this.getNombre(), game.getNombre());
    }
}

//Exceptions

/**
 * Excepcion para controloar que el usuario no introduzca una cadena
 * este metodo esta implemetado pero no tendria ningun efecto
 * ya que controlo ento en la clase GameListApp con cada uno de los parametros.
 *
 */

class CadenaVaciaException extends IOException {

    public CadenaVaciaException(String message) {
        super(message);
    }
}