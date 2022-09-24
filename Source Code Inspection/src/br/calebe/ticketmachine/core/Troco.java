package br.calebe.ticketmachine.core;

import java.util.Iterator;

/**
 *
 * @author Calebe de Paula Bianchini
 */
class Troco {

    protected PapelMoeda[] papeisMoeda;

    public Troco(int valor) {
        papeisMoeda = new PapelMoeda[6];

        int[] valoresPapelModa = { 2, 5, 10, 20, 50, 100 };
        int valorPapelMoeda, count;

        for (int i = valoresPapelModa.length - 1; i >= 0; i--) {
            valorPapelMoeda = valoresPapelModa[i];
            
            count = valor / valorPapelMoeda;
            valor %= valorPapelMoeda;

            papeisMoeda[i] = new PapelMoeda(valorPapelMoeda, count);
        }
    }

    public TrocoIterator getIterator() {
        return new TrocoIterator(this);
    }

    class TrocoIterator implements Iterator<PapelMoeda> {

        protected Troco troco;

        public TrocoIterator(Troco troco) {
            this.troco = troco;
        }

        @Override
        public boolean hasNext() {
            for (int i = papeisMoeda.length - 1; i >= 0; i--) {
                if (troco.papeisMoeda[i] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public PapelMoeda next() {
            PapelMoeda ret = null;
            for (int i = papeisMoeda.length - 1; i >= 0; i--) {
                if (troco.papeisMoeda[i] != null) {
                    ret = troco.papeisMoeda[i];
                    troco.papeisMoeda[i] = null;
                    return ret;
                }
            }
            return ret;
        }
    }
}
