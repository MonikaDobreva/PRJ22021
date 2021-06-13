package businesslogic;

import businessentitiesapi.SeatType;
import businessentitiesapi.SeatTypeManager;
import genericdao.dao.DAOFactory;
import persistence.SeatTypeStorageService;

import java.util.List;

public class SeatTypeManagerImpl implements SeatTypeManager {

    private SeatTypeStorageService seatTypeStorageService;
    private DAOFactory daoF;

    public void setSeatTypeStorageService(SeatTypeStorageService seatTypeStorageService, DAOFactory daof) {
        this.seatTypeStorageService = seatTypeStorageService;
        this.daoF = daof;
    }

    @Override
    public SeatType createSeatType(String name, double extraPrice) {
        //The 0 is just a placeholder
        return new SeatType(0, name, extraPrice);
    }

    @Override
    public SeatType add(SeatType s) {
        return seatTypeStorageService.add(s);
    }

    @Override
    public List<SeatType> getSeatTypes() {
        return seatTypeStorageService.getAll();
    }
}
