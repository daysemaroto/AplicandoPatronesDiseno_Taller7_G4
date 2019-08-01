/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import Patrones.Account;

/**
 *
 * @author JordyVillao
 */
public interface Cuenta {
    public double balance();
    public boolean retiro(double monto);
    public boolean depositar(int n, double denominacion);
    
  public int getId();

}
