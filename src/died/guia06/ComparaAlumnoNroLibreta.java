package died.guia06;

import java.util.Comparator;

public class ComparaAlumnoNroLibreta implements Comparator<Alumno>{

    @Override
    public int compare(Alumno o1, Alumno o2) {
        return o1.getNroLibreta().compareTo(o2.getNroLibreta());
    }
}
