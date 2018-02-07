package datos;

import static java.awt.image.ImageObserver.ALLBITS;
import static java.awt.image.ImageObserver.PROPERTIES;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdk.jfr.events.FileReadEvent;

public class Banco {

    static ArrayList<Cuenta> listaCuenta = new ArrayList<Cuenta>();
    static String archivo = "";

    public static void agregar(Cuenta cuenta) {
        listaCuenta.add(cuenta);
    }

    public static void seleccionarRuta() {
        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == jF1.APPROVE_OPTION) {
                archivo = jF1.getSelectedFile().getAbsolutePath();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void reirar(String cliente, double cantidad) {

        String linea = "";
        for (Cuenta cuenta : listaCuenta) {

            if (cuenta.getCliente().equalsIgnoreCase(cliente)) {
                if (cuenta.getDinero() >= cantidad) {
                    double dinero = cuenta.getDinero();
                    cuenta.setDinero(dinero - cantidad);

                    JOptionPane.showMessageDialog(null, "se descontó " + cantidad + "de la cuenta de: " + cuenta.getCliente());
                   
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                }
                break;
            }

        }

    }

    public static void depositar(String cliente, double cantidad) {

        for (Cuenta cuenta : listaCuenta) {
            JOptionPane.showMessageDialog(null, cuenta.getCliente());
            if (cuenta.getCliente().equalsIgnoreCase(cliente)) {
                double diner = cuenta.getDinero();
                cuenta.setDinero(cantidad+diner);
                JOptionPane.showMessageDialog(null, "se agregò " + cantidad + "a la cuenta de: " + cuenta.getCliente());
                break;
            }

        }

    }

    public static void grabar() {

        PrintWriter pw = null;
        Banco.seleccionarRuta();

        try {
            FileWriter fw = new FileWriter(archivo, true);
            pw = new PrintWriter(fw);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Cuenta cuenta : listaCuenta) {
            String linea = "";
            if (cuenta instanceof CuentaAhorro) {
                linea = "Cuenta Ahorro" + ";" + cuenta.toString();
            }
            if (cuenta instanceof CuentaHipoteca) {
                linea = "Cuenta Hipoteca" + ";" + cuenta.toString();
            }
            if (cuenta instanceof CuentaPrestamo) {
                linea = "Cuenta Prestamo" + ";" + cuenta.toString();
            }
            pw.println(linea);
        }

        pw.close();
    }

    public static String presentarLista() {

        String linea = "";
        for (Cuenta cuenta : listaCuenta) {

            if (cuenta instanceof CuentaAhorro) {
                linea += "Cuenta Ahorro" + ";" + cuenta.toString();
                linea.replace(";", " ");
            }
            if (cuenta instanceof CuentaHipoteca) {
                linea += "Cuenta Hipoteca" + ";" + cuenta.toString();
                linea.replace(";", " ");
            }
            if (cuenta instanceof CuentaPrestamo) {
                linea += "Cuenta Prestamo" + ";" + cuenta.toString();
                linea.replace(";", " ");
            }
        }

        return linea;
    }

    public static Cuenta buscarCuentaAhorro() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaAhorro cuentaAH = new CuentaAhorro(nombre);
        Cuenta c = (Cuenta) cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }

    public static Cuenta buscarCuentaHipoteca() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaHipoteca cuentaAH = new CuentaHipoteca(nombre);
        Cuenta c = (Cuenta) cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }

    public static void leerCuentas() {
        try {
            Banco.seleccionarRuta();
            FileReader fr = new FileReader(archivo);
            BufferedReader bf = new BufferedReader(fr);
            String clientes = "";
            String arreglo[] = new String[4];

            while (bf.readLine() != null) {

                arreglo = bf.readLine().split(";");

                if (arreglo[0].equalsIgnoreCase( "Cuenta Ahorro") ){
                    CuentaAhorro ch = new CuentaAhorro(arreglo[1], arreglo[2], ALLBITS, PROPERTIES);
                    Banco.agregar(ch);
                    JOptionPane.showMessageDialog(null, "Cuenta agregada nombre: " + arreglo[1]);

                } else if (arreglo[0].equalsIgnoreCase("Cuenta Prestamo")) {

                    CuentaPrestamo cp = new CuentaPrestamo(arreglo[1], arreglo[2], ALLBITS, PROPERTIES);
                    Banco.agregar(cp);
                    JOptionPane.showMessageDialog(null, "Cuenta agregada nombre: " + arreglo[1]);

                } else if (arreglo[0].equalsIgnoreCase("Cuenta Hipoteca")) {

                    CuentaHipoteca ch = new CuentaHipoteca(arreglo[1], arreglo[2], ALLBITS, PROPERTIES);
                    Banco.agregar(ch);
                    JOptionPane.showMessageDialog(null, "Cuenta agregada nombre: " + arreglo[1]);

                }

            }
        } catch (Exception e) {
        }

        /* 
	 * Lee los datos desde un archivo de texto, crea objetos 'Cuenta'
	 * y los almacena en la lista 'listaCuenta'
         */
        // Examen 002: Completar este metodo
    }
}
