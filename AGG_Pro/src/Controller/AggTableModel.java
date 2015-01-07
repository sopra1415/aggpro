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
public class AggTableModel<E> extends AbstractTableModel {

    ArrayList<ArrayList<E>> table;
    int width;
    
    public AggTableModel(int width){
        table = new ArrayList<ArrayList<E>>();
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
    
    public void addEntry(E[] content){
        ArrayList<E> temp = new ArrayList<E>();
        for(int i = 0; i < width; i++){
            temp.add(content[i]);
        }
    }
    
}
