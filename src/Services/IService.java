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
public interface IService<T> {
    void modifier(T c);
    void supprimer(int id);
    List<T> recuperer();
    T recuperer(int id);
    
}
