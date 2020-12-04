/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Linkers;

import java.util.List;

/**
 *
 * @author arthu
 */
public interface Linker<T> {
    
    public void Link(T obj);
    public void Link(List<T> obj);
}
