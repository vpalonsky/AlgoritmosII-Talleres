package aed;

public class Horario {
    private int horaHorario;
    private int minutosHorario;

    public Horario(int hora, int minutos) {
        horaHorario = hora;
        minutosHorario = minutos;
    }

    public int hora() {
        return horaHorario;
    }

    public int minutos() {
        return minutosHorario;
    }

    @Override
    public String toString() {
        return horaHorario+":"+minutosHorario;
    }

    @Override
    public boolean equals(Object otro) {
        boolean esNull = otro==null;
        boolean claseDistinta = otro.getClass()!=this.getClass();
        
        if (esNull||claseDistinta) return false;

        Horario otroHorario = (Horario) otro;

        return (otroHorario.minutosHorario==this.minutosHorario && otroHorario.horaHorario==this.horaHorario);
    }

}
