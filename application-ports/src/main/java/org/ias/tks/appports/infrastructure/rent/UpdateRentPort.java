package org.ias.tks.appports.infrastructure.rent;

import java.util.UUID;

public interface UpdateRentPort {
    void endRent(String date, UUID rentId);
}
