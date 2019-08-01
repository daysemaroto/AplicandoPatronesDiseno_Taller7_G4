/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfResponsability;

/**
 *
 * @author JordyVillao
 */
public interface Manejador {
    public void setNext(Manejador m);
    public boolean retirar(double monto);
    public boolean depositar(int n, double denominacion);
}
