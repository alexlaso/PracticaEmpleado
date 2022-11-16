import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Empleado {
    String DNI, nombre;

    public Empleado() {
    }

    public Empleado(String DNI, String nombre) throws SQLException {
        this.DNI = DNI;
        this.nombre = nombre;
    }

    public static void introducirEmpleado(Connection con, Scanner sc) throws SQLException {
        Empleado empleado = new Empleado();

        String query = "INSERT INTO empleados VALUES (?,?) ON DUPLICATE KEY UPDATE";
        PreparedStatement ps = con.prepareStatement(query);

        System.out.println("DNI del empleado");
        empleado.setDNI(sc.next());
        ps.setString(1, empleado.getDNI());
        System.out.println("Nombre del empleado");
        empleado.setNombre(sc.next());
        ps.setString(2, empleado.getNombre());

        ps.executeUpdate();
        con.commit();
        Main.eleccion2ElectricBoogaloo(con, sc);
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
