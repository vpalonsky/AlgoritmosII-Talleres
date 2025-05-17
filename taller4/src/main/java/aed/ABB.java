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

        public Nodo(T valor, Nodo padre) {
            _valor = valor;
            _padre = padre;
        }
    }

    public ABB() {
        _raiz = new Nodo(null, null);
        _raiz._izq = new Nodo(null, _raiz);
        _raiz._der = new Nodo(null, _raiz);
    }

    public int cardinal() {
        ABB_Iterador iterador = new ABB_Iterador();

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

    public T maximo() {
        Nodo actual = _raiz;
        while (actual._der._valor != null) {
            actual = actual._der;
        }
        return actual._valor;
    }

    public void insertar(T elem) {
        Nodo actual = _raiz;
        Nodo anterior = null;

        while (actual._valor != null) {
            int comparacion = actual._valor.compareTo(elem);
            anterior = actual;
            if (comparacion > 0) {
                actual = actual._izq;
            } else if (comparacion == 0) {
                return;
            } else {
                actual = actual._der;
            }
        }

        actual._valor = elem;
        actual._padre = anterior;
        actual._izq = new Nodo(null, actual);
        actual._der = new Nodo(null, actual);
    }

    public boolean pertenece(T elem) {
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

    public void eliminar(T elem) {
        if (!pertenece(elem))
            return;

        if (_raiz._valor.compareTo(elem) == 0) {
            if (_raiz._izq._valor == null && _raiz._der._valor == null) {
                _raiz._valor = null;
            } else if (_raiz._izq._valor != null && _raiz._der._valor != null) {
                Nodo reemplazo = _raiz._izq;

                while (reemplazo._der._valor != null) {
                    reemplazo = reemplazo._der;
                }

                T val = reemplazo._valor;
                eliminar(reemplazo._valor);
                _raiz._valor = val;
            } else if (_raiz._izq._valor != null) {
                _raiz = _raiz._izq;
            } else {
                _raiz = _raiz._der;
            }

            return;
        }

        ABB_Iterador iterador = new ABB_Iterador();

        while (iterador.haySiguiente()) {
            Nodo actual = iterador._actual;

            if (actual._valor.compareTo(elem) == 0) {
                if (actual._izq._valor == null && actual._der._valor == null) {
                    actual._valor = null;
                    actual._izq = null;
                    actual._der = null;
                } else if (actual._izq._valor != null && actual._der._valor != null) {
                    Nodo reemplazo = actual._izq;

                    while (reemplazo._der._valor != null) {
                        reemplazo = reemplazo._der;
                    }

                    T val = reemplazo._valor;
                    eliminar(reemplazo._valor);
                    actual._valor = val;
                } else {
                    Nodo reemplazo = actual._der;

                    if (actual._izq._valor != null)
                        reemplazo = actual._izq;

                    reemplazo._padre = actual._padre;
                    if (reemplazo._padre._valor.compareTo(reemplazo._valor) > 0) {
                        reemplazo._padre._izq = reemplazo;
                    } else {
                        reemplazo._padre._der = reemplazo;
                    }
                }

                return;
            } else {
                iterador.siguiente();
            }
        }
    }

    public String toString() {
        ABB_Iterador iterador = new ABB_Iterador();

        String res = "{";

        while (iterador.haySiguiente()) {
            T sig = iterador.siguiente();
            res += sig + ",";
        }

        if (res.length() > 1)
            res = res.substring(0, res.length() - 1);
        return res += "}";
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

            if (_actual._valor == max) {
                _actual = new Nodo(null, null);
                return max;
            } else {
                if (_actual._der._valor != null) {
                    siguiente = _actual._der;
                    while (siguiente._izq._valor != null)
                        siguiente = siguiente._izq;
                } else {
                    siguiente = _actual._padre;
                    int comparacion = siguiente._valor.compareTo(_actual._valor);
                    while (comparacion < 0) {
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
