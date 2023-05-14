/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipobusquedaenanchura;

/**
 *
 * @author LoreyFaby
 */
import java.util.*;

class Nodo {
    String valor;
    List<Nodo> hijos;

    public Nodo(String valor) {
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }
}

public class PrototipoBusquedaEnAnchura {
    public static void main(String[] args) {
        Nodo raiz = construirArbol();
        List<List<Nodo>> rutas = buscarRutas(raiz);
        mostrarRutas(rutas);
    }

    private static Nodo construirArbol() {
        Nodo raiz = new Nodo("B");
        Nodo b1 = new Nodo("B1");
        Nodo b2 = new Nodo("B2");
        Nodo b3 = new Nodo("B3");
        Nodo b4 = new Nodo("B4");
        Nodo b5 = new Nodo("B5");
        Nodo b6 = new Nodo("B6");
        Nodo a = new Nodo("A");

        raiz.hijos.add(b1);
        raiz.hijos.add(b2);
        
        b1.hijos.add(b3);
        b2.hijos.add(b4);
        b2.hijos.add(a);
        b3.hijos.add(b5);
        b3.hijos.add(b6);
        b4.hijos.add(a);
        b5.hijos.add(a);
        b6.hijos.add(a);

        return raiz;
    }

    private static List<List<Nodo>> buscarRutas(Nodo raiz) {
        List<List<Nodo>> rutas = new ArrayList<>();
        Queue<List<Nodo>> cola = new LinkedList<>();
        List<Nodo> rutaInicial = new ArrayList<>();
        rutaInicial.add(raiz);
        cola.offer(rutaInicial);

        List<Nodo> mejorRuta = null;

        while (!cola.isEmpty()) {
            List<Nodo> rutaActual = cola.poll();
            Nodo nodoActual = rutaActual.get(rutaActual.size() - 1);

            if (nodoActual.valor.equals("A")) {
                rutas.add(rutaActual);
                
                if (mejorRuta == null || rutaActual.size() < mejorRuta.size()) {
                    mejorRuta = rutaActual;
                }
                
                continue;
            }

            for (Nodo hijo : nodoActual.hijos) {
                List<Nodo> nuevaRuta = new ArrayList<>(rutaActual);
                nuevaRuta.add(hijo);
                cola.offer(nuevaRuta);
            }
        }
        
        rutas.remove(mejorRuta);
        rutas.add(0, mejorRuta);

        return rutas;
    }

    private static void mostrarRutas(List<List<Nodo>> rutas) {
        if (rutas.isEmpty()) {
            System.out.println("No se encontraron movimientos posibles");
        } else {
            System.out.println("Mejor solucion encontrada para colocar la pieza con el metodo en anchura en A es :");
            mostrarRuta(rutas.get(0));

            if (rutas.size() > 1) {
                System.out.println("Otras posibles soluciones de  movimientos para la colocacion de la pieza:");
                for (int i = 1; i < rutas.size(); i++) {
                    mostrarRuta(rutas.get(i));
                }
            }
        }
    }

    private static void mostrarRuta(List<Nodo> ruta) {
        for (Nodo nodo : ruta) {
            System.out.print(nodo.valor + " ");
        }
        System.out.println();
    }
}
