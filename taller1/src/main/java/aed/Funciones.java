package aed;

class Funciones {
    int cuadrado(int x) {
        // COMPLETAR
        return x*x;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        return Math.sqrt(x*x+y*y);
    }

    boolean esPar(int n) {
        // COMPLETAR
        return n%2==0;
    }

    boolean esBisiesto(int n) {
        // COMPLETAR
        return (n%4==0 && n%100!=0) || n%400==0;
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        if (n==0) return 1;

        int res = 1;
        for (int i=1; i<=n; i++) {
            res *= i;
        }
        return res;
    }
    
    int factorialRecursivo(int n) {
        // COMPLETAR
        if (n==0) return 1;
        return n*factorialRecursivo(n-1);
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        if (n==0 || n==1) return false;
        
        boolean res = true;
        for (int i=2; i<n; i++) {
            if (n%i==0) {
                res = false;
            }
        }
        return res;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        int res = 0;
        for (int i : numeros) {
            res += i;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i]==buscado) return i;
        }
        return 0;
    }

    boolean tienePrimo(int[] numeros) {
        // COMPLETAR
        for (int i : numeros) {
            if (esPrimo(i)) return true;
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        for (int i : numeros) {
            if (i%2!=0) return false;
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        if (s1.length()>s2.length()) return false;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i)!=s2.charAt(i)) return false;   
        }
        
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        // COMPLETAR
        String new_s1 = "";
        String new_s2 = "";
        
        if (s1.length()>s2.length()) return false;

        for (int i = 0; i < s1.length(); i++) {
            new_s1 += s1.charAt(s1.length()-1-i);
        }

        for (int j = 0; j < s2.length(); j++) {
            new_s2 += s2.charAt(s2.length()-1-j);
        }

        return esPrefijo(new_s1, new_s2);
    }
}
