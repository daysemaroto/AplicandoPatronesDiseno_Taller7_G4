/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfResponsability;

import Singleton.AtmEC;

public class ManejadorDinero implements Manejador {

    private Manejador next;
    protected int cantidad;
    protected double denominacion;

    public ManejadorDinero(int n, double denominacion) {
        cantidad = n;
        this.denominacion = denominacion;
        next = null;
    }

    @Override
    public void setNext(Manejador manejador) {
        next = manejador;
    }

    @Override
    public boolean retirar(double monto) {
        int cantRetiro = ((int) Math.round(monto * 100)) / ((int) Math.round(denominacion * 100));

        if (cantRetiro > cantidad && next != null) {
            return next.retirar(monto);
        } else if (cantRetiro > cantidad) {
            return false;
        }

        double faltante = monto - (denominacion * cantRetiro);
        boolean isValid = true;
        if (faltante != 0.0 && next != null) {
            isValid = next.retirar(faltante);
        }

        if (isValid && cantRetiro > 0) {
            retiro(cantRetiro);
        }
        if (next == null && !isValid) {
            return false; // no se pudo hacer la transaccion
        }
        return isValid;
    }

    private void retiro(int dinero) {
        cantidad -= dinero;
        System.out.printf("Withdraw: %d de $%.2f.\n", dinero, denominacion);
    }

    @Override
    public boolean depositar(int n, double denominacion) {
        if (this.denominacion == denominacion) {
            cantidad += n;
            System.out.printf("Deposit: %d de $%.2f.\n", n, this.denominacion);
            return true;
        } else if (next == null) {
            return false; // no existe el tipo de moneda o billete ingresado
        } else {
            return next.depositar(n, denominacion);
        }
    }

    @Override
    public Manejador getNext() {
        return next;
    }

    public double getMoney() {
        return cantidad * denominacion;
    }
    
    public int getCantidad(){
        return this.cantidad;
    }
    
    public double getDenominacion(){
        return this.denominacion;
    }

}
