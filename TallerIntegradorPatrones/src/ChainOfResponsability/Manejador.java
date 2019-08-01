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

    void setNext(Manejador manejador);

    public double getMoney();

    public Manejador getNext();

    boolean retirar(double monto);

    boolean depositar(int n, double denominacion);
}
