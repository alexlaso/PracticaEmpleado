import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    //  TODO Arreglar fallo de column out of index en los metadatos de las tres opciones
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "root","");
        con.setAutoCommit(false);
        Scanner sc = new Scanner(System.in);
        opciones(con, sc);
    }

    private static void opciones(Connection con, Scanner sc) throws SQLException {
        int eleccion;
        System.out.println("Introduce el número correspondiente a su elección");
        System.out.println("1: Introducir un nuevo empleado en la tabla");
        System.out.println("2: Crear un nuevo proyecto");
        System.out.println("3: Asignar un proyecto a un empleado");
        System.out.println("4: Obtener los metadatos de la BBDD");
        eleccion = sc.nextInt();
        switch (eleccion) {
            case 1 -> Empleado.introducirEmpleado(con, sc);
            case 2 -> Proyecto.introducirProyecto(con, sc);
            case 3 -> AsigProyEmpl.asignarProyecto(con, sc);
            case 4 -> obtenerMetadatos(con, sc);
            default -> {
                System.out.println("Eso es imposible, prueba de nuevo");
                eleccion = sc.nextInt();
            }
        }
    }

    private static void obtenerMetadatos(Connection con, Scanner sc) throws SQLException {
        String elegirTabla;
        System.out.println("Introduce el nombre de la tabla que quieres comprobar: empleados, proyectos, asig_proyectos");
        elegirTabla = sc.next();
        switch (elegirTabla){
            case "empleados": datosEmpleados(con); break;
            case "proyectos": datosProyectos(con); break;
            case "asig_proyectos": datosAsigProyectos(con); break;
        }

        eleccion2ElectricBoogaloo(con, sc);

    }

    private static void datosEmpleados(Connection con) throws SQLException {
        String query="SELECT * FROM empleados";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        //Current record details.
        System.out.println("Table name: "+rsmd.getTableName(1));
        int NUM_COLUMNS = rsmd.getColumnCount();
        for (int i = 1;i<=NUM_COLUMNS;i++) {
            System.out.println("Column name: " + rsmd.getColumnName(i));
            System.out.println("Column type: " + rsmd.getColumnTypeName(i));
            System.out.println();
        }
    }

    private static void datosProyectos(Connection con) throws SQLException {
        String query = "SELECT * FROM proyectos";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        /*while (rs.next()) {
            System.out.println("Número: " + rs.getInt(1)+", Nombre: " + rs.getString(2) +", DNI jefe: " + rs.getString(3)+", Fecha inicio: " + sdf.format(rs.getDate(4))+", Fecha fin: " + sdf.format(rs.getDate(5)));
        }*/
        ResultSetMetaData rsmd = rs.getMetaData();
        //Current record details.
        System.out.println("Table name: "+rsmd.getTableName(1));
        int NUM_COLUMNS = rsmd.getColumnCount();
        for (int i = 1;i<= NUM_COLUMNS;i++) {
            System.out.println("Column name: " + rsmd.getColumnName(i));
            System.out.println("Column type: " + rsmd.getColumnTypeName(i));
            System.out.println();
        }
    }

    private static void datosAsigProyectos(Connection con) throws SQLException {
        String query = "SELECT * FROM asig_proyectos";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        /*while (rs.next()) {
            System.out.println("DNI empleado: " + rs.getString(1)+", Número de proyecto: " + rs.getInt(2)+", Fecha inicio: " + sdf.format(rs.getDate(3))+", Fecha fin: " + sdf.format(rs.getDate(4)));
        }*/
        ResultSetMetaData rsmd = rs.getMetaData();
        //Current record details.
        System.out.println("Table name: "+rsmd.getTableName(1));
        int NUM_COLUMNS = rsmd.getColumnCount();
        for (int i = 1;i<= NUM_COLUMNS;i++) {
            System.out.println("Column name: " + rsmd.getColumnName(i));
            System.out.println("Column type: " + rsmd.getColumnTypeName(i));
            System.out.println();
        }
    }

    public static void eleccion2ElectricBoogaloo(Connection con, Scanner sc) throws SQLException {
        System.out.println("¿Quieres seguir haciendo cambios en la base?");
        System.out.println("1: Si \t2: No");
        int eleccion = sc.nextInt();
        switch (eleccion){
            case 1: opciones(con, sc); break;
            case 2:
                System.out.println("Adiós");
                con.close();
                System.exit(0);break;
            default:
                System.out.println("Número erróneo");
                eleccion = sc.nextInt();
                break;
        }
    }
}