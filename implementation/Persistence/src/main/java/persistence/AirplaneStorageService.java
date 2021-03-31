/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import businessentitiesapi.Airplane;
import java.util.List;

/**
 *
 * @author Rachel
 */
public interface AirplaneStorageService {

    void add(Airplane a);
    List<Airplane> getAll();
    void delete(Airplane a);
}
