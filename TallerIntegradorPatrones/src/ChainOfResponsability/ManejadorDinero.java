/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfResponsability;

public class ManejadorDinero implements Manejador
{
    protected int monto;
    protected double denominacion;

    public ManejadorDinero(int monto, int denominacion){
        this.monto = monto; // Total de billetes/moneda
        this.denominacion = denominacion; // Valor de cada billete/moneda
    }

    public int getMonto(){ return monto; }
    public double getDenominacion(){ return denominacion; }
    public void setMonto(int monto){ this.monto = monto; }
    
    @Override
    public void setNext(Manejador m){
        
    }

    @Override
    public boolean retirar(double monto){
        // Implementar
        return false;
    }
    
    @Override
    public boolean depositar(int monto, double denominacion){
        // Implementar
        return false;
    }

 
}