package datos;

import java.util.Objects;

/**
 *
 * @author Jorge Pucha
 */
public abstract class Cuenta {

    private String cliente;
    private String tipoCliente;
    private double balance;
    private double tasaInteres;
    private double dinero;

    public Cuenta() {
    }

    public Cuenta(String cliente) {
        this.cliente = cliente;
    }

    public Cuenta(double dinero) {
        this.dinero = dinero;
    }

    public Cuenta(String cliente, String tipoCliente, double balance, double tasaInteres) {
        this.cliente = cliente;
        this.tipoCliente = tipoCliente;
        this.balance = balance;
        this.tasaInteres = tasaInteres;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public abstract double calcularInteres(int meses);

    public abstract double depositar(double monto);

    @Override
    public String toString() {
        return cliente + ";" + tipoCliente + ";" + balance + ";" + tasaInteres + ";" + dinero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.cliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }

}
