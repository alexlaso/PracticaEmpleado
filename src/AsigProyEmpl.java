import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AsigProyEmpl {
    String dniEmpleado, fechaString;
    int numProy;
    Date fechaInit, fechaEnd;

    public AsigProyEmpl() {
    }

    public AsigProyEmpl(String dniEmpleado, String fechaString, int numProy, Date fechaInit, Date fechaEnd) {
        this.dniEmpleado = dniEmpleado;
        this.fechaString = fechaString;
        this.numProy = numProy;
        this.fechaInit = fechaInit;
        this.fechaEnd = fechaEnd;
    }

    public static void asignarProyecto(Connection con, Scanner sc) throws SQLException {
        AsigProyEmpl ape = new AsigProyEmpl();

        String query = "INSERT INTO asig_proyectos VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE";
        PreparedStatement ps = con.prepareStatement(query);
        System.out.print("DNI del empleado: ");
        ape.setDniEmpleado(sc.next());
        ps.setString(1, ape.getDniEmpleado());
        System.out.print("Número del proyecto: ");
        ape.setNumProy(sc.nextInt());
        ps.setInt(2, ape.getNumProy());
        System.out.print("Fecha de inicio del proyecto (Formato yyyy-mm-dd 2000-01-31");
        ape.setFechaString(sc.next());
        ape.setFechaInit(Date.valueOf(ape.getFechaString()));
        ps.setDate(3, ape.getFechaInit());
        System.out.print("Fecha de fin del proyecto (Formato yyyy-mm-dd 2000-01-31");
        ape.setFechaString(sc.next());
        ape.setFechaEnd(Date.valueOf(ape.getFechaString()));
        ps.setDate(4, ape.getFechaEnd());

        ps.executeUpdate();
        con.commit();

        Main.eleccion2ElectricBoogaloo(con, sc);

    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    public int getNumProy() {
        return numProy;
    }

    public void setNumProy(int numProy) {
        this.numProy = numProy;
    }

    public Date getFechaInit() {
        return fechaInit;
    }

    public void setFechaInit(Date fechaInit) {
        this.fechaInit = fechaInit;
    }

    public Date getFechaEnd() {
        return fechaEnd;
    }

    public void setFechaEnd(Date fechaEnd) {
        this.fechaEnd = fechaEnd;
    }
}
