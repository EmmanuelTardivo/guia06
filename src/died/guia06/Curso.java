package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import died.guia06.exceptions.RegistroAuditoriaException;
import died.guia06.util.Registro;

/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su
 * nombre y ciclo lectivo.
 * Un curso guarda una lista de los inscriptos actuales que tienen.
 * Un curso, al aprobarlo, otorga una cantidad de creditos definidas en el
 * curso.
 * Un curso requiere que para inscribirnos tengamos al menos la cantidad de
 * creditos requeridas, y que haya cupo disponible
 * 
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer cupo;
	private List<Alumno> inscriptos;
	private Integer creditos;
	private Integer creditosRequeridos;

	private Registro log;

	public Curso() {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}

	public Curso(Integer id, String nombre, Integer cicloLectivo, Integer cupo, Integer creditos,
			Integer creditosRequeridos) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.cicloLectivo = cicloLectivo;
		this.cupo = cupo;
		this.creditos = creditos;
		this.creditosRequeridos = creditosRequeridos;
	}

	/**
	 * Este método, verifica si el alumno se puede inscribir y si es así lo agrega
	 * al curso,
	 * agrega el curso a la lista de cursos en los que está inscripto el alumno y
	 * retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos
	 * ni el curso a la lista
	 * de cursos en los que el alumno está inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * a) tener como minimo los creditos necesarios
	 * b) tener cupo disponibles
	 * c) puede estar inscripto en simultáneo a no más de 3 cursos del mismo ciclo
	 * lectivo.
	 * 
	 * @param a alumno
	 * @return Boolean
	 * @throws IOException
	 */

	public Boolean inscribir(Alumno a) throws Exception {
		if (cumpleCreditosRequeridos(a) && hayCupo() && cumpleCursosCicloLectivo(a)) {
			inscriptos.add(a);
			a.inscripcionAceptada(this);
			log.registrar(this, "inscribir ", a.toString());
			return true;
		} else {
			return false;
		}
	}

	public  void inscribirAlumno(Alumno a) throws Exception {
		try {
			inscribir(a);
		} catch (IOException e) {
			throw new RegistroAuditoriaException("Ocurrio un problema al registrar la inscripcion.");
		} catch (Exception e) {
			throw e;
		}
	}

	private Boolean cumpleCreditosRequeridos(Alumno a) throws Exception {
		if (a.creditosObtenidos() >= creditosRequeridos) {
			return true;
		} else {
			throw new Exception("El alumno no dispone de los creditos requeridos");
		}
	}

	private Boolean hayCupo() throws Exception {
		if (this.inscriptos.size() < this.cupo) {
			return true;
		} else {
			throw new Exception("No hay cupo en el cursor");
		}
	}

	private Boolean cumpleCursosCicloLectivo (Alumno a) throws Exception {
		if (a.cursosDelCicloLectivo(cicloLectivo) < 3) {
			return true;
		} else {
			throw new Exception("El alumno ya esta inscripto en 3 cursos del ciclo lectivo de este curso.");
		}
	}
	/**
	 * Imprime los inscriptos en orden alfabetico
	 * @throws IOException
	 */
	public void imprimirInscriptos() throws IOException {
		Collections.sort(inscriptos);
		imprimirInscriptosAux();
	}

	private void imprimirInscriptosAux() throws IOException {
		System.out.println(inscriptos.toString());
		log.registrar(this, "imprimir listado", this.inscriptos.size() + " registros ");
	}

	/**
	 * Imprime los inscriptos ordenados por el número de libreta
	 * @throws IOException
	 */

	public void imprimirInscriptosPorNroLibreta () throws IOException {
		Collections.sort(inscriptos, new ComparaAlumnoNroLibreta());
		imprimirInscriptosAux();
	}

	/**
	 * Imprime los inscriptos ordenados por la cantidad de créditos obtenidos
	 * @throws IOException
	 */

	public void imprimirInscriptosPorCreditos() throws IOException {
		Collections.sort(inscriptos, new ComparaAlumnoCreditos());
		imprimirInscriptosAux();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(Integer cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public List<Alumno> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<Alumno> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public Integer getCreditosRequeridos() {
		return creditosRequeridos;
	}

	public void setCreditosRequeridos(Integer creditosRequeridos) {
		this.creditosRequeridos = creditosRequeridos;
	}

	public Registro getLog() {
		return log;
	}

	public void setLog(Registro log) {
		this.log = log;
	}

}
