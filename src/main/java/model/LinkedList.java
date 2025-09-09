package model;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {
    List<Nodo<T>> nodos;


    public LinkedList() {
        nodos = new ArrayList<>();
    }

    public int getLength() {
        return nodos.size();
    }

    public void add(T valor) {
        Nodo<T> nodo = new Nodo<T>(valor, null);

        if (!nodos.isEmpty()) {
            nodos.getLast().setReferencia(nodo);
        }

        nodos.add(nodo);
    }

    public void cambiarReferencia(int indiceNodoACambiar, int indiceNodoApuntado) {
        nodos.get(indiceNodoACambiar).setReferencia(nodos.get(indiceNodoApuntado));
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < nodos.size() ; i++)  {
            string.append("Nodo ").append(nodos.get(i + 1)).append(":");
            string.append("\n\tValor: ").append(nodos.get(i).getValor().toString());
            string.append("\n\tReferencia: ").append(nodos.get(nodos.indexOf(nodos.get(i).getReferencia()))).append(1);
            string.append("\n");
        }

        return string.toString();
    }
}