/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.calebe.ticketmachine.core;

import br.calebe.ticketmachine.core.TicketMachine;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import org.junit.Assert;

/**
 *
 * @author unifhcarvalho
 */
public class TicketMachineTest {
    
    @Test
    public void Insrir_QuantiaNegativa_LancarExcecaoTroco()
    {
        // arrange
        var ticketMachine = new TicketMachine(0);
        
        // act
        
        
        // assert
        Assert.assertThrows(SaldoInsuficienteException.class, () -> ticketMachine.inserir(-1));
    }
}
