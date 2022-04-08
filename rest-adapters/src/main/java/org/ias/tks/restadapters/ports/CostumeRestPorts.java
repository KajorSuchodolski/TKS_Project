package org.ias.tks.restadapters.ports;

import org.ias.tks.restadapters.ports.costume.CreateCostumeRestPort;
import org.ias.tks.restadapters.ports.costume.GetCostumeRestPort;
import org.ias.tks.restadapters.ports.costume.RemoveCostumeRestPort;
import org.ias.tks.restadapters.ports.costume.UpdateCostumeRestPort;

public interface CostumeRestPorts extends CreateCostumeRestPort, GetCostumeRestPort, RemoveCostumeRestPort, UpdateCostumeRestPort {
}
