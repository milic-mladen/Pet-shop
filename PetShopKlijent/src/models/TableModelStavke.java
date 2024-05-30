/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domen.Porudzbina;
import domen.StavkaPorudzbine;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class TableModelStavke extends AbstractTableModel {

    private ArrayList<StavkaPorudzbine> lista;
    private String[] kolone = {"Rb", "Proizvod", "Kolicina", "Cena"};
    private int rb = 0;

    public TableModelStavke() {
        lista = new ArrayList<>();
    }

    public TableModelStavke(Porudzbina p) {
        try {
            lista = ClientController.getInstance().getAllStavkaPorudzbine(p);
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaPorudzbine sp = lista.get(row);

        switch (column) {
            case 0:
                return sp.getRbStavke();
            case 1:
                return sp.getProizvod().getNazivProizvoda();
            case 2:
                return sp.getKolicina();
            case 3:
                return sp.getCenaStavke() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaPorudzbine sp) {

        for (StavkaPorudzbine stavkaPorudzbine : lista) {
            if (stavkaPorudzbine.getProizvod().getProizvodID().equals(sp.getProizvod().getProizvodID())) {
                stavkaPorudzbine.setKolicina(stavkaPorudzbine.getKolicina() + sp.getKolicina());
                stavkaPorudzbine.setCenaStavke(stavkaPorudzbine.getCenaStavke() + sp.getCenaStavke());
                fireTableDataChanged();
                return;
            }
        }

        rb = lista.size();
        sp.setRbStavke(++rb);
        lista.add(sp);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaPorudzbine stavkaPorudzbine : lista) {
            stavkaPorudzbine.setRbStavke(++rb);
        }

        fireTableDataChanged();
    }

    public StavkaPorudzbine getStavka(int row) {
        return lista.get(row);
    }

    public ArrayList<StavkaPorudzbine> getLista() {
        return lista;
    }
}
