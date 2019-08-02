/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Adapter.Cuenta;
import ChainOfResponsability.Manejador;
import Patrones.Account;
import ChainOfResponsability.ManejadorDinero;
import Patrones.Color;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class AtmEC {

    private static AtmEC instance = new AtmEC();
    private Currency moneda;
    private static double dinero; //13057.5
    private Manejador manejador;
    private Cuenta cuentaAdap;

    private AtmEC() {
        moneda = Currency.getInstance(Locale.US);
        updateMoney();
        cuentaAdap = null;
    }

    public static AtmEC getInstance() {
        return instance;
    }

    public boolean sacarDinero(double dinero) {
        if (manejador != null) {
            return manejador.retirar(dinero);
        }
        return false;
    }

    public boolean ingresarDinero(int n, double denominacion) {
        if (manejador != null) {
            return manejador.depositar(n, denominacion);
        }
        return false;
    }

    public void addManejador(Manejador m) {
        if (manejador == null) {
            manejador = m;
        } else {
            for (Manejador m1 = manejador; m1 != null; m1 = m.getNext()) {
                if (manejador.getNext() == null) {
                    manejador.setNext(m);
                }
            }
        }
        updateMoney();
    }

    public Manejador removeManejador(Manejador m) {
        /*for(int i=0; i<=m.size();i++){
               return manejadores.remove(i);
           }*/
        return null;
    }

    public void transaction(Cuenta cuenta) {
        cuentaAdap = cuenta;
        // here is where most of the work is
        Scanner in = new Scanner(System.in);
        int choice;
        System.out.println(Color.ANSI_RED + "Please select an option");
        System.out.println(Color.ANSI_BLUE + "1. Withdraw");
        System.out.println(Color.ANSI_BLUE + "2. Deposit");
        System.out.println(Color.ANSI_BLUE + "3. Balance");
        System.out.println(Color.ANSI_BLUE + "4. Balance ATM");
        System.out.println(Color.ANSI_RED + "Ingrese opcion:");
        choice = in.nextInt();
        in.nextLine();
        switch (choice) {
            case 1:
                double amount;
                System.out.print("Enter the amount to withdraw: ");
                amount = in.nextDouble();
                in.nextLine();
                if (amount > cuenta.balance() || amount == 0) {
                    System.out.println("Your balance is not enought!!\n\n");

                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    // verificar que se puede realizar el retiro del atm
                    if (amount < cuenta.balance() && instance.sacarDinero(amount)) {

                        System.out.println("The withdraw was made succesfully");
                        cuenta.retiro(amount);
                        updateMoney();
                        System.out.printf("You have withdrawn $%.2f and your new balance is $%.2f\n", amount, cuenta.balance());
                    } else {
                        System.out.println("There is not enougth money at ATM to fullfil the transaction.");
                    }
                    anotherTransaction(cuenta);
                }
                break;
            case 2:
                // option number 2 is depositing 
                double n;
                int deposit;
                System.out.print("Enter the denomination to deposit: ");
                n = in.nextDouble();
                in.nextLine();
                System.out.print("Enter the amount of denomination to deposit: ");
                deposit = in.nextInt();
                in.nextLine();
                //if (instance.sacarDinero(deposit)) {
                if (instance.ingresarDinero(deposit, n)) {
                    cuenta.depositar(deposit, n);
                    this.addManejador(new ManejadorDinero(deposit, n));
                    updateMoney();
                    System.out.println("The deposit was made succesfully");
                    System.out.printf("You have deposited $%.2f and your new balance is $%.2f\n", (n * deposit), cuenta.balance());
                } else {
                    System.out.println("There was a problem while depositing the money.");
                }
                anotherTransaction(cuenta);
                break;
            case 3:
                System.out.printf("Your balance is $%.2f\n", cuenta.balance());
                anotherTransaction(cuenta);
                break;
            case 4:
                System.out.printf("The ATM balance is $%.2f\n", dinero);
                // Todo: mostrar el balance del ATM con los billetes en cada manejador
                ManejadorDinero next = (ManejadorDinero) this.manejador;
                while (next != null) {
//                    System.out.printf("Cantidad de billetes/monedas de $%d : %d\n",(int)next.getDenominacion(),next.getCantidad());
                    System.out.printf("Cantidad de billetes/monedas de $%.2f : %d\n", next.getDenominacion(), next.getCantidad());
                    next = (ManejadorDinero) next.getNext();
                }
                anotherTransaction(cuenta);
                break;
            default:
                System.out.println("Invalid option:\n\n");
                anotherTransaction(cuenta);
                break;
        }
    }

    public void anotherTransaction(Cuenta cuenta) {

        Scanner in = new Scanner(System.in);
        int op;
        System.out.println(Color.ANSI_RED + "Do you want another transaction?\nPress 1 for another transaction\nPress 2 To exit");
        op = in.nextInt();
        if (op == 1) {
            instance.transaction(cuenta); // call transaction method
        } else if (op == 2) {
            System.out.println("Thanks for choosing us. Good Bye!");
            cuentaAdap = null;
        } else {
            System.out.println("Invalid choice\n\n");
            anotherTransaction(cuenta);
        }
    }

    public void updateMoney() {
        double monto = 0.0;
        for (Manejador m = manejador; m != null; m = m.getNext()) {
            monto += m.getMoney();
        }
        dinero = monto;
    }

    public Cuenta getCuentaAdap() {
        return cuentaAdap;
    }

}
