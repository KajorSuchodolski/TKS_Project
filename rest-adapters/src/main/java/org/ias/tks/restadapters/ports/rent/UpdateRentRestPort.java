package org.ias.tks.restadapters.ports.rent;

import java.util.UUID;

public interface UpdateRentRestPort {
    void endRent(String date, UUID rentId);

}
