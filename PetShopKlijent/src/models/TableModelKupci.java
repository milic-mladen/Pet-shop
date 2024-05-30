/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domen.Kupac;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class TableModelKupci extends AbstractTableModel implements Runnable {

    private ArrayList<Kupac> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Kontakt", "Adresa", "Status"};
    private String parametar = "";

    public TableModelKupci() {
        try {
            lista = ClientController.getInstance().getAllKupac();
        } catch (Exception ex) {
            Logger.getLogger(TableModelKupci.class.getName()).log(Level.SEVERE, null, ex);
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
        Kupac k = lista.get(row);

        switch (column) {
            case 0:
                return k.getKupacID();
            case 1:
                return k.getImeKupca();
            case 2:
                return k.getPrezimeKupca();
            case 3:
                return k.getKontaktTelefon();
            case 4:
                return k.getAdresa();
            case 5:
                return k.getStatus();

            default:
                return null;
        }
    }

    public Kupac getSelectedKupac(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelKupci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllKupac();
            if (!parametar.equals("")) {
                ArrayList<Kupac> novaLista = new ArrayList<>();
                for (Kupac k : lista) {
                    if (k.getImeKupca().toLowerCase().contains(parametar.toLowerCase())
                            || k.getPrezimeKupca().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(k);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
