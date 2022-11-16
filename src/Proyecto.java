import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Proyecto {
    int numProy;
    String nombreProy, dniJefeProy, fechaString;
    Date fechaInit, fechaEnd;

    public Proyecto() {
    }

    public Proyecto(int numProy, String nombreProy, String dniJefeProy, String fechaString, Date fechaInit, Date fechaEnd) throws SQLException{
        this.numProy = numProy;
        this.nombreProy = nombreProy;
        this.dniJefeProy = dniJefeProy;
        this.fechaString = fechaString;
        this.fechaInit = fechaInit;
        this.fechaEnd = fechaEnd;
    }

    public static void introducirProyecto(Connection con, Scanner sc) throws SQLException {
        Proyecto proyecto = new Proyecto();
        String query = "INSERT INTO proyectos VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE";
        PreparedStatement ps = con.prepareStatement(query);

        System.out.println("Número del proyecto");
        proyecto.setNumProy(sc.nextInt());
        ps.setInt(1, proyecto.getNumProy());
        System.out.println("Nombre del proyecto");
        proyecto.setNombreProy(sc.next());
        ps.setString(2, proyecto.getNombreProy());
        System.out.println("DNI del jefe del proyecto");
        proyecto.setDniJefeProy(sc.next());
        ps.setString(3, proyecto.getDniJefeProy());
        System.out.println("Fecha de inicio del proyecto (Formato yyyy-mm-dd, Ejemplo: 2000-01-31");
        proyecto.setFechaString(sc.next());
        proyecto.setFechaInit(Date.valueOf(proyecto.getFechaString()));
        ps.setDate(4, proyecto.getFechaInit());
        System.out.println("Fecha de fin del proyecto (Formato yyyy-mm-dd, Ejemplo: 2000-01-31");
        proyecto.setFechaString(sc.next());
        proyecto.setFechaEnd(Date.valueOf(proyecto.getFechaString()));
        ps.setDate(5, proyecto.getFechaEnd());

        // Para devolver la fecha formateada
        // String s = new SimpleDateFormat("dd/MM/yyyy").format(fechaInit);

        ps.executeUpdate();
        con.commit();

        Main.eleccion2ElectricBoogaloo(con, sc);
    }

    public int getNumProy() {
        return numProy;
    }

    public void setNumProy(int numProy) {
        this.numProy = numProy;
    }

    public String getNombreProy() {
        return nombreProy;
    }

    public void setNombreProy(String nombreProy) {
        this.nombreProy = nombreProy;
    }

    public String getDniJefeProy() {
        return dniJefeProy;
    }

    public void setDniJefeProy(String dniJefeProy) {
        this.dniJefeProy = dniJefeProy;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
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
