/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkePorudzbine;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.StavkaPorudzbine;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author User
 */
public class SOGetAllStavkaPorudzbine extends AbstractSO {

    private ArrayList<StavkaPorudzbine> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaPorudzbine)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaPorudzbine!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavkePorudzbine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaPorudzbine>) (ArrayList<?>) stavkePorudzbine;
    }

    public ArrayList<StavkaPorudzbine> getLista() {
        return lista;
    }

}
