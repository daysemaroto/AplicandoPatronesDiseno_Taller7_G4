/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Patrones.Account;
import ChainOfResponsability.ManejadorDinero;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class AtmEC {
    
    public static Scanner in = new Scanner(System.in);
    public AtmEC instance = null;
    private final Currency currency =Currency.getInstance(Locale.US);
    private double dinero = 0;
    private ManejadorDinero manejador; // Cada manejador puede entregar dinero de una sola denominación

    // -----------------
    private AtmEC() {
      
    }
    // -----------------
    public AtmEC getInstace(){ 
        if(instance == null)
            instance = new AtmEC();
        return instance;
    }
    //------------------
    public double getTotal() {
        return this.dinero;
    }

    // -----------------
    public void sacarDinero(double dinero) {
        this.dinero -= dinero;
        // Todo: realizar el proceso de sacar de cada manejador la cantidad requerida
    }

    // -----------------
    public void ingresarDinero(double dinero, int denominacion) {
        this.dinero += dinero;
        // Todo: Sólo se puede depositar billetes de una sola denominación y agregarse al manejador correspondiente
    }

    public void addManejador(ManejadorDinero m){
        
    }
    public ManejadorDinero removeManejador(int i){
        return null;
    }

    //Dentro de las transacciones se debe llamar al ATM para hacer el retiro o deposito de la cuenta correspondiente
    public static void transaction(Account cuenta){
        // here is where most of the work is
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice = in.nextInt();
        switch(choice){
            case 1:
                float amount; 
                System.out.println("Please enter amount to withdraw: "); 
                amount = in.nextFloat();
                if(amount > cuenta.getAmount() || amount == 0){
                    System.out.println("You have insufficient funds\n\n"); 
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    // Todo: verificar que se puede realizar el retiro del atm

                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // cuenta.retirar(amount);
                    // AtmUK.sacarDinero(amount);

                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    anotherTransaction(cuenta); 
                }
            break; 
            case 2:
                    // option number 2 is depositing 
                    float deposit; 
                    System.out.println("Please enter amount you would wish to deposit: "); 
                    deposit = in.nextFloat();
                    // Todo: actualizar tanto la cuenta como el atm
                    
                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    anotherTransaction(cuenta);
            break; 
            case 3:
                    // Todo: mostrar el balance de la cuenta
                    // "Your balance is "+balance
                    anotherTransaction(cuenta); 
            break;
            case 4:
                    // Todo: mostrar el balance del ATM con los billetes en cada manejador
                    anotherTransaction(cuenta); 
            break;
            default:
                    System.out.println("Invalid option:\n\n"); 
                    anotherTransaction(cuenta);
            break;
        }
    }
    public static void anotherTransaction(Account cuenta){
        System.out.println("Do you want another transaction?\n\nPress 1 for another transaction\n2 To exit");
        int anotherTransaction = in.nextInt();
        switch (anotherTransaction) {
            case 1:
                transaction(cuenta); // call transaction method
                break;
            case 2:
                System.out.println("Thanks for choosing us. Good Bye!");
                break;
            default:
                System.out.println("Invalid choice\n\n");
                anotherTransaction(cuenta);
                break;
        }
    }

    
}
