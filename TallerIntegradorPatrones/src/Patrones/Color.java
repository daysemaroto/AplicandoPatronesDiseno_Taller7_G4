/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

/**
 *
 * @author Grupo 7 (Pedro Conforme,David Llerena,Ariel Velez)
 */
public class Color {

  /**
   * 
   */
   public static final String ANSI_RESET = "\u001B[0m";
  /**
   */
   public static final String ANSI_BLACK = "\u001B[30m";
  /**
   */
   public static final String ANSI_RED = "\u001B[31m";
  /**
   */
   public static final String ANSI_GREEN = "\u001B[32m";
  /**
   */
   public static final String ANSI_YELLOW = "\u001B[33m";
  /**
   */
   public static final String ANSI_BLUE = "\u001B[34m";
  /**
   */
   public static final String ANSI_PURPLE = "\u001B[35m";
  /**
   */
   public static final String ANSI_CYAN = "\u001B[36m";
  /**
   */
   public static final String ANSI_WHITE = "\u001B[37m";

  /**
 
      */
   public static void print(String color, String txt){
     System.out.print(color+txt);
   }

   public static void println(String color, String txt){
     System.out.println(color+txt);
   }

  /**
   */
   public static String getStringInColor(String color, String txt){
     return color+txt;
   }

}
