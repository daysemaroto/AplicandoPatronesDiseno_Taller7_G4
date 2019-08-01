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
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class AtmEC {
    
    private static AtmEC instance = new AtmEC();
    private Currency moneda;
    private static double dinero; //13057.5
    private static Manejador manejador;
    private static Cuenta cuentaAdap;
  
    
    
    private AtmEC() {
        moneda = Currency.getInstance(Locale.US);
        updateMoney();
        cuentaAdap = null;
    }



    public static AtmEC getInstance() {
        return instance;
    }

       public boolean sacarDinero(double dinero){
            if(manejador!= null){
                return manejador.retirar(dinero);
            }
            return false;
       }

       public boolean ingresarDinero(int n, double denominacion){
           if(manejador!= null){
                return manejador.depositar(n, denominacion);
            }
           return false;
       }

       public void addManejador(Manejador m){
           if(manejador == null){
               manejador = m;
           }else{
               for(Manejador m1 = manejador; m1 != null; m1 = m.getNext()){
                   if(manejador.getNext() == null){
                       manejador.setNext(m);
                   }
               }
           }
           updateMoney();
        }
    
       public Manejador removeManejador(Manejador m){
           /*for(int i=0; i<=m.size();i++){
               return manejadores.remove(i);
           }*/
           return null;
       }
       
       public static void transaction(Cuenta cuenta){
        cuentaAdap = cuenta;
        // here is where most of the work is
        Scanner in= new Scanner(System.in);
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        System.out.print("opcion: ");
        choice = in.nextInt();
        in.nextLine();
        switch(choice){
            case 1:
                double amount; 
                System.out.print("Enter the amount to withdraw: "); 
                amount = in.nextDouble();
                in.nextLine();
                if(amount > cuenta.balance()|| amount == 0){
                    System.out.println("Your balance is not enought!!\n\n"); 
  
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    // verificar que se puede realizar el retiro del atm
                    if(amount<cuenta.balance() && instance.sacarDinero(amount)){
                        System.out.println("The withdraw was made succesfully");
                        cuenta.retiro(amount);
                        updateMoney();
                        System.out.printf("You have withdrawn $%.2f and your new balance is $%.2f\n",amount,cuenta.balance());
                    }else{
                        System.out.println("There is not enougth money at ATM to fullfil the transaction.");
                    }
                    anotherTransaction(cuenta); 
                }
            break; 
            case 2:
                // option number 2 is depositing 
                int n;
                double deposit;
                System.out.print("Enter the denomination to deposit: "); 
                n = in.nextInt();
                in.nextLine();
                System.out.print("Enter the amount of denomination to deposit: "); 
                deposit = in.nextDouble();
                in.nextLine();
                if(instance.sacarDinero(deposit)){
                    cuenta.depositar(n, deposit);
                    updateMoney();
                    System.out.println("The deposit was made succesfully");
                    System.out.printf("You have withdrawn $%.2f and your new balance is $%.2f\n",(n*deposit),cuenta.balance());
                }else{
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
                    anotherTransaction(cuenta); 
            break;
            default:
                    System.out.println("Invalid option:\n\n"); 
                    anotherTransaction(cuenta);
            break;
        }
    }

       public static void anotherTransaction(Cuenta cuenta){

          Scanner in = new Scanner(System.in);
        int op;
        System.out.println("Do you want another transaction?\n\nPress 1 for another transaction\n2 To exit");
        op = in.nextInt();
        if (op == 1) {
            transaction(cuenta); // call transaction method
        } else if (op == 2) {
            System.out.println("Thanks for choosing us. Good Bye!");
            cuentaAdap = null;
        } else {
            System.out.println("Invalid choice\n\n");
            anotherTransaction(cuenta);
        }
    }
       
       public static void updateMoney(){
           double monto = 0.0;
           for(Manejador m = manejador; m!= null; m = m.getNext()){
               monto += m.getMoney();
           }
           dinero = monto;
       }
}
