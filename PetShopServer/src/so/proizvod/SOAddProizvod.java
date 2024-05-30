/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.proizvod;

import so.kupac.*;
import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Kupac;
import domen.Proizvod;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author User
 */
public class SOAddProizvod extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Proizvod)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Proizvod!");
        }
        
        Proizvod p = (Proizvod) ado;
        
        ArrayList<Proizvod> proizvodi = (ArrayList<Proizvod>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Proizvod proizvod : proizvodi) {
            if(p.getNazivProizvoda().equals(proizvod.getNazivProizvoda())){
                throw new Exception("Vec postoji proizvod s tim nazivom!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
