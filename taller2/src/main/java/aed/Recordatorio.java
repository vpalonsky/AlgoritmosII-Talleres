package aed;

public class Recordatorio {
    private String mensajeRecordatorio;
    private Fecha fechaRecordatorio;
    private Horario horarioRecordatorio;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        mensajeRecordatorio = mensaje;
        fechaRecordatorio = new Fecha(fecha);
        horarioRecordatorio = new Horario(horario.hora(), horario.minutos());
    }

    public Horario horario() {
        return new Horario(horarioRecordatorio.hora(), horarioRecordatorio.minutos());
    }

    public Fecha fecha() {
        return new Fecha(fechaRecordatorio);
    }

    public String mensaje() {
        return mensajeRecordatorio;
    }

    @Override
    public String toString() {
        // Implementar
        return mensajeRecordatorio+" @ "+fechaRecordatorio.toString()+" "+horarioRecordatorio.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean esNull = otro==null;
        boolean claseDistinta = otro.getClass()!=this.getClass();
        
        if (esNull||claseDistinta) return false;

        Recordatorio otroRecordatorio = (Recordatorio) otro;

        return (otroRecordatorio.mensajeRecordatorio==mensajeRecordatorio && fechaRecordatorio.equals(otroRecordatorio.fecha()) && horarioRecordatorio.equals(otroRecordatorio.horario()));
    }

}
