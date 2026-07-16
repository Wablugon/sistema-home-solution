package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.*;

import entidades.Tupla;
import excepciones.DatosInvalidosException;
import excepciones.FormatoFechaInvalidoException;
import sistema.HomeSolution;
import sistema.IHomeSolution;

public class HomeSolutionTest {

    private IHomeSolution sistema;

    @BeforeEach
    void setUp() {
        sistema = new HomeSolution();
    }
    @Test
    void registrarEmpleadoContratado() {
        sistema.registrarEmpleado("Juan", 2500);

        List<Tupla<Integer, String>> empleados = sistema.empleados();

        assertEquals(1, empleados.size());
        assertEquals("Juan", empleados.get(0).getValor2());
    }

    @Test
    void registrarEmpleadoDePlanta() {
        sistema.registrarEmpleado("Pedro", 3000, "Inicial");

        assertEquals(1, sistema.empleados().size());
    }

    @Test
    void registrarEmpleadoNombreVacio() {
        assertThrows(
            DatosInvalidosException.class,
            () -> sistema.registrarEmpleado("", 2000)
        );
    }

    @Test
    void registrarEmpleadoValorNegativo() {
        assertThrows(
            DatosInvalidosException.class,
            () -> sistema.registrarEmpleado("Juan", -50)
        );
    }

    private String[] titulos() {
        return new String[]{"Electricidad"};
    }

    private String[] descripciones() {
        return new String[]{"Instalar luces"};
    }

    private double[] dias() {
        return new double[]{2};
    }

    private String[] cliente() {
        return new String[]{
            "Carlos",
            "mail@mail.com",
            "11223344"
        };
    }

    @Test
    void registrarProyectoCorrectamente() {

        sistema.registrarProyecto(
                titulos(),
                descripciones(),
                dias(),
                "Calle 123",
                cliente(),
                "2026-08-01",
                "2026-08-10");

        assertEquals(1, sistema.proyectosPendientes().size());
    }

    @Test
    void registrarProyectoConArreglosInvalidos() {

        assertThrows(
            DatosInvalidosException.class,
            () -> sistema.registrarProyecto(
                    new String[]{"A"},
                    new String[]{"Desc1","Desc2"},
                    new double[]{2},
                    "Casa",
                    cliente(),
                    "2026-08-01",
                    "2026-08-10"
            )
        );
    }

    @Test
    void registrarVariosEmpleados() {
        sistema.registrarEmpleado("Juan", 1000);
        sistema.registrarEmpleado("Pedro", 2000, "Inicial");

        assertEquals(2, sistema.empleados().size());
    }

    @Test
    void registrarEmpleadoCategoriaInvalida() {
        assertThrows(
            DatosInvalidosException.class,
            () -> sistema.registrarEmpleado("Pedro", 3000, "Astronauta")
        );
    }

    @Test
    void registrarProyectoSinTareas() {
        assertThrows(
            DatosInvalidosException.class,
            () -> sistema.registrarProyecto(
                    new String[] {},
                    new String[] {},
                    new double[] {},
                    "Casa",
                    cliente(),
                    "2026-08-01",
                    "2026-08-10")
        );
    }

    @Test
    void registrarProyectoClienteIncompleto() {
        assertThrows(
            DatosInvalidosException.class,
            () -> sistema.registrarProyecto(
                    titulos(),
                    descripciones(),
                    dias(),
                    "Casa",
                    new String[] {"Carlos"},
                    "2026-08-01",
                    "2026-08-10")
        );
    }

    @Test
    void registrarProyectoFechasInvalidas() {
        assertThrows(
            FormatoFechaInvalidoException.class,
            () -> sistema.registrarProyecto(
                    titulos(),
                    descripciones(),
                    dias(),
                    "Casa",
                    cliente(),
                    "2026-08-10",
                    "2026-08-01")
        );
    }

    @Test
    void registrarProyectoFormatoFechaInvalido() {
        assertThrows(
            FormatoFechaInvalidoException.class,
            () -> sistema.registrarProyecto(
                    titulos(),
                    descripciones(),
                    dias(),
                    "Casa",
                    cliente(),
                    "01/08/2026",
                    "10/08/2026")
        );
    }

    @Test
    void sistemaIniciaSinEmpleados() {
        assertEquals(0, sistema.empleados().size());
    }

    @Test
    void sistemaIniciaSinProyectos() {
        assertEquals(0, sistema.proyectosPendientes().size());
        assertEquals(0, sistema.proyectosActivos().size());
        assertEquals(0, sistema.proyectosFinalizados().size());
    }

    @Test
    void empleadoNuevoNoTieneRetrasos() {
        sistema.registrarEmpleado("Juan", 2500);

        assertEquals(0, sistema.consultarCantidadRetrasosEmpleado(1));
    }

    @Test
    void empleadoRecienRegistradoEstaLibre() {
        sistema.registrarEmpleado("Juan", 2000);

        assertEquals(1, sistema.empleadosNoAsignados().length);
    }
}
