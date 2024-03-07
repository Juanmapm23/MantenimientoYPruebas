package bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {
    
    BankAccount bankAccount;

    @Before
    public void setup(){
        bankAccount= new BankAccount(750);
    }

    @Test
    public void buenaCreacion(){
        int credito = bankAccount.getBalance();
        assertEquals(750, credito);
    }

    @Test
    public void depositoCorrecto(){
        int deposito = 1000;
        int balanceInicial = bankAccount.getBalance();
        bankAccount.deposit(deposito);
        assertEquals(deposito + balanceInicial, bankAccount.getBalance());
    }

    @Test
    public void depositoNegativo(){
        try {
            bankAccount.deposit(-100);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        
    }

    @Test
    public void retiradaCorrecta(){
        int balanceInicial = bankAccount.getBalance();
        int retirada = 500;
        boolean correcta = bankAccount.withdraw(retirada);
        assertEquals(balanceInicial-retirada, bankAccount.getBalance());
        assertTrue(correcta);
    }

    @Test
    public void retiradaMayorQueSaldo(){
        int balanceInicial = bankAccount.getBalance();
        int retirada = balanceInicial + 100;
        boolean correcta = bankAccount.withdraw(retirada);
        assertFalse(correcta);
        assertEquals(balanceInicial, bankAccount.getBalance());
    }

    @Test
    public void pagoCorrecto(){
        double total = 1000.0;
        double interes = 0.05;
        int pagos = 12;
        double pagoEsperado = 112.82541;// Calculado manualmente
        double pagoReal = bankAccount.payment(total, interes, pagos);
        assertEquals(pagoEsperado, pagoReal, 0.01);
    }

    @Test
    public void pendingMesMayor() {
        double cantidad = 1000.0;
        double interes = 0.05;
        int pagos = 12;
        int meses = 6;
        double pendingEsperado = 572.66703; // Calculado manualmente
        double pendingReal = bankAccount.pending(cantidad, interes, pagos, meses);
        assertEquals(pendingEsperado, pendingReal, 0.01);
    }

    @Test
    public void pendingMesCero() {
        double cantidad = 1000.0;
        double interes = 0.05;
        int pagos = 12;
        int meses = 0;
        double pendingEsperado = cantidad; 
        double pendingReal = bankAccount.pending(cantidad, interes, pagos, meses);
        assertEquals(pendingEsperado, pendingReal, 0.01);
    }


}
 