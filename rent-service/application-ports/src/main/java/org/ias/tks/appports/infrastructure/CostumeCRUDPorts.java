package org.ias.tks.appports.infrastructure;

import org.ias.tks.appports.infrastructure.costume.CreateCostumePort;
import org.ias.tks.appports.infrastructure.costume.GetCostumePort;
import org.ias.tks.appports.infrastructure.costume.RemoveCostumePort;
import org.ias.tks.appports.infrastructure.costume.UpdateCostumePort;

public interface CostumeCRUDPorts extends CreateCostumePort, GetCostumePort, RemoveCostumePort, UpdateCostumePort {
}
