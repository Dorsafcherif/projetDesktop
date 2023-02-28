/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface OService<T> {
    void modifier(T o);
    void supprimer(int id);
    List<T> recuperer();
    T recuperer(int id);
    
}
