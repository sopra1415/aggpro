/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Heiko Geppert
 */
public class AggTableModel extends AbstractTableModel {

    ArrayList<ArrayList<String>> table;
    int width;
    
    public AggTableModel(int width){
        table = new ArrayList<ArrayList<String>>();
        this.width = width;
    }
    
    @Override
    public int getRowCount() {
        return table.size();
    }

    @Override
    public int getColumnCount() {
        return this.width;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return table.get(i).get(i1);
    }
    
    public void clear(){
        table.clear();
    }
    
    /**
     *
     * @param content holds all contents for an new row
     */
    public void addEntry(String[] content){
        // TODO delete Syso
        System.out.println("addEntry");
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < width; i++){
            temp.add(content[i]);
        }
    }
    
}
