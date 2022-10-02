package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class TicketMachineTest {
    
    @ParameterizedTest
    @ValueSource(ints = {-2, -5, -10, -20, -50, -100})
    public void Inserir_DeveLancarExcecaoPapelMoedaInvalida_QuandoInformarPepelMoedaNegativa() 
    {
        var sut = new TicketMachine(Mockito.anyInt());
        
        var thrown  = Assertions.catchThrowable(() -> sut.inserir(-2));

        Assertions.assertThat(thrown).isInstanceOf(PapelMoedaInvalidaException.class);
    }
    
    @ParameterizedTest
    @CsvSource({
        "2, 5",
        "5, 10",
        "10, 20"
    })
    public void Imprimir_DeveLancarExcecaoSaldoInsuficiente_QuandoSaldoMenorQueValor(int saldo, int valor) 
    {
        var sut = new TicketMachine(valor);
        sut.saldo = saldo;

        var thrown  = Assertions.catchThrowable(() -> sut.imprimir());

        Assertions.assertThat(thrown).isInstanceOf(SaldoInsuficienteException.class);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 20, 50, 100})
    public void Imprimir_DeveObterSaldoSemCasasDecimais(int saldo) throws SaldoInsuficienteException
    {
        var sut = new TicketMachine(0);
        sut.saldo = saldo;

        var resultSaldo = sut.imprimir();
        
        var expectedResult = "*****************\n";
        expectedResult += "*** R$ " + sut.getSaldo() + " ****\n";
        expectedResult += "*****************\n";
        Assertions.assertThat(resultSaldo).isEqualTo(expectedResult);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 10, 20, 50, 100})
    public void Imprimir_DeveAtualizarSaldo_AposInserirQuantia(int quantia) throws PapelMoedaInvalidaException, SaldoInsuficienteException
    {
        var valorBilhete = 2;
        var sut = new TicketMachine(valorBilhete);
        
        sut.inserir(quantia);
        sut.imprimir();
        
        Assertions.assertThat(sut.getSaldo()).isEqualTo(quantia - valorBilhete);
    }
}
