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
    public Manejador next;

    public ManejadorDinero(int monto, int denominacion){
        this.monto = monto; // Total de billetes/moneda
        this.denominacion = denominacion; // Valor de cada billete/moneda
    }

    public int getMonto(){ return monto; }
    public double getDenominacion(){ return denominacion; }
    public void setMonto(int monto){ this.monto = monto; }
    
    @Override
    public void setNext(Manejador m){
        this.next=m;
    }

    @Override
    public boolean retirar(double monto){
        // Implementar
        return false;
    }
    
    @Override
    public boolean depositar(int monto, double denominacion){
        if(this.denominacion== denominacion)
        {
            this.monto = this.monto + monto;
	    return true;
        }
        else
        {
            if (this.next == null)
                return false;
            return (this.next.depositar(monto, denominacion));
        }
     
    }

 
}