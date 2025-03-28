package aed;

public class Agenda {
    Fecha fechaAgenda;
    ArregloRedimensionableDeRecordatorios arregloRecordatorios;

    public Agenda(Fecha fechaActual) {
        fechaAgenda = new Fecha(fechaActual);
        arregloRecordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        arregloRecordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String recordatoriosHoy = "";
        for (int i = 0; i < arregloRecordatorios.longitud(); i++) {
            Recordatorio recordatorio = arregloRecordatorios.obtener(i);

            if (recordatorio.fecha().equals(fechaAgenda)) {
                recordatoriosHoy += recordatorio.toString() + "\n";
            }
        }

        recordatoriosHoy = fechaAgenda.toString() + "\n=====\n" + recordatoriosHoy;
        return recordatoriosHoy;
    }

    public void incrementarDia() {
        fechaAgenda.incrementarDia();
    }

    public Fecha fechaActual() {
        return new Fecha(fechaAgenda);
    }

}
