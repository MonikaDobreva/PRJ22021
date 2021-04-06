
package persistence;

import businessentitiesapi.Airplane;
import businessentitiesapi.AirplaneManager;
import businessentitiesapi.Flight;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rachel
 */
public class AirplaneStorageServiceImpl implements AirplaneStorageService{

    private final AirplaneManager airplaneManager;
    private static List<Airplane> airplanes = new ArrayList<>(); //data handling only through lists so far, DDB later
    
    
     public AirplaneStorageServiceImpl(AirplaneManager airplaneManager) {
        this.airplaneManager = airplaneManager;
    }
    
    @Override
    public void add(Airplane a) {
       try {
            FileWriter writer = new FileWriter("airplaneStorage.csv", true);
            String sb = "";
            sb = sb.concat(a.getName() + ",");
            sb = sb.concat(a.getCode() + ",");
            sb = sb.concat(a.getSeatAmount()+ "\n");
            if (Files.lines(Path.of("airplaneStorage.csv")).count() == 0){
                writer.write(sb);
            } else {
                writer.append(sb);
            }
            writer.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Airplane> getAll() {
       //List<Airplane> airplanes = new ArrayList<>();

        try{
            Files.lines(Path.of("airplaneStorage.csv"))
                        .map(line -> line.split(","))
                        .map(this::createAirplaneFromCSV)
                        .forEach(airplanes::add);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        
        return airplanes;
    }

    @Override
    public void delete(Airplane a) {
        // implement flight deletion
    }


    public Airplane createAirplaneFromCSV (String[] s) {
        return this.airplaneManager.createAirplane(s[0], s[1], Integer.parseInt(s[2]));
    }
}
