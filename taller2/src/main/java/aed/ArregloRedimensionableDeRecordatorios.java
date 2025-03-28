package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        recordatorios = new Recordatorio[0];
    }

    public int longitud() {
        return recordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevosRecordatorios = new Recordatorio[recordatorios.length+1];
        for (int j = 0; j < recordatorios.length; j++) {
            nuevosRecordatorios[j] = recordatorios[j];
        }
        nuevosRecordatorios[nuevosRecordatorios.length-1] = new Recordatorio(i.mensaje(), i.fecha(), i.horario());
        recordatorios = nuevosRecordatorios;
    }

    public Recordatorio obtener(int i) {
        return recordatorios[i];
    }

    public void quitarAtras() {
        Recordatorio[] nuevosRecordatorios = new Recordatorio[recordatorios.length-1];
        for (int j = 0; j < nuevosRecordatorios.length; j++) {
            nuevosRecordatorios[j] = recordatorios[j];
        }
        recordatorios = nuevosRecordatorios;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        recordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        recordatorios = new Recordatorio[vector.longitud()];
        for (int i = 0; i < vector.longitud(); i++) {
            Recordatorio recordatorioParaCopiar = vector.obtener(i);
            recordatorios[i] = new Recordatorio(recordatorioParaCopiar.mensaje(), recordatorioParaCopiar.fecha(), recordatorioParaCopiar.horario());
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        return new ArregloRedimensionableDeRecordatorios(this);
    }
}
