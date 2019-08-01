/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import Adapter.Cuenta;
import Adapter.CuentaAdapter;
import ChainOfResponsability.ManejadorDinero;
import static Patrones.Validaciones.validarOpcion;
import Singleton.AtmEC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static ArrayList<Cuenta> accounts = new ArrayList();

    public static AtmEC cajero = AtmEC.getInstance();

    @Override
    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();

        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05
        ManejadorDinero billete20 = new ManejadorDinero(100, 20);
        ManejadorDinero billete10 = new ManejadorDinero(100, 10);
        ManejadorDinero moneda50 = new ManejadorDinero(10, 0.5);
        ManejadorDinero moneda25 = new ManejadorDinero(10, 0.25);
        ManejadorDinero moneda5 = new ManejadorDinero(1000, 0.05);

        billete20.setNext(billete10);
        billete10.setNext(moneda50);
        moneda50.setNext(moneda25);
        moneda25.setNext(moneda5);

        cajero.addManejador(billete20);
//        cajero.addManejador(billete10);
//        cajero.addManejador(moneda50);
//        cajero.addManejador(moneda25);
//        cajero.addManejador(moneda5);

        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
//        Account cuenta1 = new Account(1, 100);
//        Account cuenta2 = new Account(1, 200);
//        Account cuenta3 = new Account(1, 300);
//        Account cuenta4 = new Account(1, 400);
//        Account cuenta5 = new Account(1, 500);
//        Account cuenta6 = new Account(1, 600);
//        Account cuenta7 = new Account(1, 700);
//        Account cuenta8 = new Account(1, 800);
//        Account cuenta9 = new Account(1, 900);
//        Account cuenta10 = new Account(1, 1000);
//        Cuenta cuentaA1 = new CuentaAdapter(cuenta1);
//        Cuenta cuentaA2 = new CuentaAdapter(cuenta2);
//        Cuenta cuentaA3 = new CuentaAdapter(cuenta3);
//        Cuenta cuentaA4 = new CuentaAdapter(cuenta4);
//        Cuenta cuentaA5 = new CuentaAdapter(cuenta5);
//        Cuenta cuentaA6 = new CuentaAdapter(cuenta6);
//        Cuenta cuentaA7 = new CuentaAdapter(cuenta7);
//        Cuenta cuentaA8 = new CuentaAdapter(cuenta8);
//        Cuenta cuentaA9 = new CuentaAdapter(cuenta9);
//        Cuenta cuentaA10 = new CuentaAdapter(cuenta10);
        Cuenta cuentaA1 = new CuentaAdapter(1, 100);
        Cuenta cuentaA2 = new CuentaAdapter(2, 200);
        Cuenta cuentaA3 = new CuentaAdapter(3, 300);
        Cuenta cuentaA4 = new CuentaAdapter(4, 400);
        Cuenta cuentaA5 = new CuentaAdapter(5, 500);
        Cuenta cuentaA6 = new CuentaAdapter(6, 600);
        Cuenta cuentaA7 = new CuentaAdapter(7, 700);
        Cuenta cuentaA8 = new CuentaAdapter(8, 800);
        Cuenta cuentaA9 = new CuentaAdapter(9, 900);
        Cuenta cuentaA10 = new CuentaAdapter(10, 1000);

//        accounts.add(cuenta1);
//        accounts.add(cuenta2);
//        accounts.add(cuenta3);
//        accounts.add(cuenta4);
//        accounts.add(cuenta5);
//        accounts.add(cuenta6);
//        accounts.add(cuenta7);
//        accounts.add(cuenta8);
//        accounts.add(cuenta9);
//        accounts.add(cuenta10);
        accounts.add(cuentaA1);
        accounts.add(cuentaA2);
        accounts.add(cuentaA3);
        accounts.add(cuentaA4);
        accounts.add(cuentaA5);
        accounts.add(cuentaA6);
        accounts.add(cuentaA7);
        accounts.add(cuentaA8);
        accounts.add(cuentaA9);
        accounts.add(cuentaA10);

        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        // Mostrar el menú para realizar transacciones en el cajero automático
        menuPrincipal();
        System.exit(0); 

    }

    public static void main(String[] args) {
        launch(args);
        
        

    }

    public static Cuenta getAccountById(int id) {
        for (Cuenta a : accounts) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public static void menuPrincipal() {
        Scanner entradaPorTeclado = new Scanner(System.in);
        System.out.println("");
        String opcion = "";
        while (!"2".equals(opcion)) {
            System.out.println("  ____ _____ ______ _   ___      ________ _   _ _____ _____   ____  \n"
                    + " |  _ \\_   _|  ____| \\ | \\ \\    / /  ____| \\ | |_   _|  __ \\ / __ \\ \n"
                    + " | |_) || | | |__  |  \\| |\\ \\  / /| |__  |  \\| | | | | |  | | |  | |\n"
                    + " |  _ < | | |  __| | . ` | \\ \\/ / |  __| | . ` | | | | |  | | |  | |\n"
                    + " | |_) || |_| |____| |\\  |  \\  /  | |____| |\\  |_| |_| |__| | |__| |\n"
                    + " |____/_____|______|_| \\_|   \\/   |______|_| \\_|_____|_____/ \\____/ \n"
                    + "                                                                   ");
            System.out.println(Color.ANSI_RED + "Menu Principal");
            System.out.println(Color.ANSI_BLUE + "1. Ingresar con el id de una cuenta bancaria");
            System.out.println(Color.ANSI_BLUE + "2. Salir");
            System.out.println(Color.ANSI_RED + "Ingrese opcion:");
            opcion = entradaPorTeclado.nextLine();
            while (validarOpcion(opcion, 2)) {
                System.out.println(Color.ANSI_RED + "Ingrese una opcion correcta.");
                System.out.println(Color.ANSI_BLUE + "1. Ingresar con el id de una cuenta bancaria");
                System.out.println(Color.ANSI_BLUE + "2. Salir");
                System.out.println(Color.ANSI_RED + "Ingrese opcion:");
                opcion = entradaPorTeclado.nextLine();
            }
            switch (opcion) {
                case "1":
                    iniciarSesion();
                    break;
                case "2":
                    System.out.println(Color.ANSI_RED + "Saliendo del programa...");
                    break;

            }
           
        }
    }

    private static void iniciarSesion() {
        Scanner entradaPorTeclado = new Scanner(System.in);
        boolean validacion = true;
        System.out.println("Ingrese el id de su cuenta: ");
        String id = entradaPorTeclado.nextLine();
        if (Integer.parseInt(id) > 0 & Integer.parseInt(id) < 11) {
            Cuenta a = getAccountById(Integer.parseInt(id));
            //System.out.println(a);
            //System.out.println(accounts);
            cajero.transaction(a);

        }

        if (validacion) {
            System.out.println(Color.ANSI_RED + "Ingrese un id correcto");
        }

    }

}
