package org.ias.tks.appports.application.rent;

import java.util.UUID;

public interface FinishRentUseCase {
    void endRent(String date, UUID rentId);

}
