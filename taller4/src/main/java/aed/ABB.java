package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;

    private class Nodo {
        private T _valor;
        private Nodo _izq;
        private Nodo _der;
        private Nodo _padre;


        public Nodo(T valor, Nodo izq, Nodo der, Nodo padre) {
            _valor = valor;
            _izq = izq;
            _der = der;
            _padre = padre;
        }
    }

    public ABB() {
        _raiz = new Nodo(null, null, null, null);
        _raiz._izq = new Nodo(null, null, null, _raiz);
        _raiz._der = new Nodo(null, null, null, _raiz);
    }

    public int cardinal() {
        ABB_Iterador iterador = new ABB_Iterador();
        
        // if (iterador._actual._valor == null) return 0;
        
        int cant = 0;
        while (iterador.haySiguiente()) {
            iterador.siguiente();
            cant += 1;
        }
        return cant;
    }

    public T minimo() {
        ABB_Iterador iterador = new ABB_Iterador();
        return iterador._actual._valor;
    }

    public T maximo(){
        Nodo actual = _raiz;
        while (actual._der._valor != null) {
            actual = actual._der;
        }
        return actual._valor;
    }

    public void insertar(T elem){
        Nodo actual = _raiz;

        while (actual._valor != null) {
            int comparacion = actual._valor.compareTo(elem);
            if (comparacion > 0) {
                actual = actual._izq;
            } else if (comparacion == 0) {
                return;
            } else {
                actual = actual._der;
            }
        }
        
        actual._valor = elem;
        actual._izq = new Nodo(null, null, null, actual);
        actual._der = new Nodo(null, null, null, actual);
    }
    
    public boolean pertenece(T elem){
        Nodo actual = _raiz;

        while (actual._valor != null) {
            int comparacion = actual._valor.compareTo(elem);
            if (comparacion > 0) {
                actual = actual._izq;
            } else if (comparacion == 0) {
                return true;
            } else {
                actual = actual._der;
            }
        }

        return false;
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public ABB_Iterador() {
            _actual = _raiz;
            
            while (_actual._izq._valor != null) {
                _actual = _actual._izq;
            }
        }

        public boolean haySiguiente() {
            return (_actual._valor != null);
        }
    
        public T siguiente() {
            Nodo siguiente = null;

            T max = maximo();
            if (_actual._valor == max){
                _actual = new Nodo(null, null, null, null);
                return max;
            }
            else {
                if (_actual._der._valor != null) {
                    siguiente = _actual._der;
                } else {
                    int comparacion = _actual._padre._valor.compareTo(_actual._valor);
                    siguiente = _actual._padre;
                    while (comparacion<0) {
                        comparacion = siguiente._padre._valor.compareTo(siguiente._valor);
                        siguiente = siguiente._padre;
                    }
                }

                T res = _actual._valor;
                _actual = siguiente;
                return res;
            }

        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
