package aed;

public class Fecha {
    private int diaFecha;
    private int mesFecha;

    public Fecha(int dia, int mes) {
        diaFecha = dia;
        mesFecha = mes;
    }

    public Fecha(Fecha fecha) {
        diaFecha = fecha.dia();
        mesFecha = fecha.mes();
    }

    public Integer dia() {
        return diaFecha;
    }

    public Integer mes() {
        return mesFecha;
    }

    public String toString() {
        return diaFecha+"/"+mesFecha;
    }

    @Override
    public boolean equals(Object otra) {
        boolean esNull = otra==null;
        boolean claseDistinta = otra.getClass()!=this.getClass();
        
        if (esNull||claseDistinta) return false;

        Fecha otraFecha = (Fecha) otra;

        return (otraFecha.diaFecha==this.diaFecha && otraFecha.mesFecha==this.mesFecha);
    }

    public void incrementarDia() {
        diaFecha++;
        if (diaFecha>diasEnMes(mesFecha)) {
            diaFecha=1;
            mesFecha++;
        }

        if (mesFecha>12) mesFecha=1;
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
