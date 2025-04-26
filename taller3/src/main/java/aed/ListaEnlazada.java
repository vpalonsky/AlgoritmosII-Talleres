package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private class Nodo {
        // Completar
        T valor;
        Nodo sig;
        Nodo prev;

        Nodo(T v) {
            valor = v;
        }
    }

    private Nodo primero;
    private Nodo ultimo;

    public ListaEnlazada() {
        primero = ultimo = null;
    }

    public int longitud() {
        if (primero == null)
            return 0;

        int res = 1;
        Nodo actual = primero;
        while (actual.sig != null) {
            res++;
            actual = actual.sig;
        }

        return res;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
            primero.prev = primero.sig = ultimo.prev = ultimo.sig = null;
        } else {
            primero.prev = nuevo;
            nuevo.sig = primero;
            nuevo.prev = null;
            primero = nuevo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (ultimo == null) {
            primero = nuevo;
            ultimo = nuevo;
            primero.prev = primero.sig = ultimo.prev = ultimo.sig = null;
        } else {
            ultimo.sig = nuevo;
            nuevo.prev = ultimo;
            nuevo.sig = null;
            ultimo = nuevo;
        }
    }

    public T obtener(int i) {
        if (i >= longitud())
            return null;

        Nodo res = null;
        if (i >= longitud() / 2) {
            res = ultimo;
            for (int j = longitud() - 1; j > i; j--) {
                res = res.prev;
            }
        } else {
            res = primero;
            for (int j = 0; j < i; j++) {
                res = res.sig;
            }
        }
        return res.valor;
    }

    public void eliminar(int i) {
        if (i >= longitud())
            return;

        if (longitud() == 1) {
            primero = ultimo = null;
            return;
        }

        if (i == 0) {
            primero.sig.prev = null;
            primero = primero.sig;
            return;
        }

        if (i == longitud() - 1) {
            ultimo.prev.sig = null;
            ultimo = ultimo.prev;
            return;
        }

        Nodo actual = primero;
        if (i >= longitud() / 2) {
            actual = ultimo;
            for (int j = longitud() - 1; j > i; j--) {
                actual = actual.prev;
            }
        } else {
            for (int j = 0; j < i; j++) {
                actual = actual.sig;
            }
        }

        actual.prev.sig = actual.sig;
        actual.sig.prev = actual.prev;
    }

    public void modificarPosicion(int i, T elem) {
        Nodo actual = primero;
        if (i >= longitud() / 2) {
            actual = ultimo;
            for (int j = longitud() - 1; j > i; j--) {
                actual = actual.prev;
            }
        } else {
            for (int j = 0; j < i; j++) {
                actual = actual.sig;
            }
        }
        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.primero;
        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.sig;
        }
    }

    @Override
    public String toString() {
        String res = "[";
        Nodo actual = primero;
        while (actual != ultimo) {
            res += actual.valor + ", ";
            actual = actual.sig;
        }
        return res + ultimo.valor + "]";
    }

    private class ListaIterador implements Iterador<T> {
        int pos = 0;

        public boolean haySiguiente() {
            return pos != longitud();
        }

        public boolean hayAnterior() {
            return pos != 0;
        }

        public T siguiente() {
            if (!haySiguiente())
                return null;

            T res = obtener(pos);
            pos++;
            return res;
        }

        public T anterior() {
            if (!hayAnterior())
                return null;

            pos--;
            T res = obtener(pos);
            return res;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
