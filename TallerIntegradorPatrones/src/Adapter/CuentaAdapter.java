/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import Patrones.Account;
import Patrones.Main;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author JordyVillao
 */
public class CuentaAdapter implements Cuenta {

    protected Account cuenta;
    protected Currency moneda;
    public Account getCuenta() {
        return cuenta;
    }

    public Currency getMoneda() {
        return moneda;
    }
    public CuentaAdapter(int id, double monto) {
        //cuenta = Main.getAccountById(id);
        cuenta = new Account(id, monto);
        moneda = Currency.getInstance(Locale.US);
    }

    @Override
    public double balance() {
        return cuenta.getAmount();
    }

    @Override
    public boolean retiro(double monto) {
        String response = cuenta.withdraw(monto);
        if (response.contains("Error")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean depositar(int n, double denominacion) {
        System.out.println("Depositando");
        cuenta.deposit(n*denominacion);
        return true;
    }

    @Override
    public int getId() {

        return cuenta.getId();
    }

}
