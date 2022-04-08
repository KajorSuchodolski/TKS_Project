package org.ias.tks.restadapters.ports;

import org.ias.tks.restadapters.ports.user.CreateUserRestPort;
import org.ias.tks.restadapters.ports.user.GetUserRestPort;
import org.ias.tks.restadapters.ports.user.UpdateUserRestPort;

public interface UserRestPorts extends GetUserRestPort, CreateUserRestPort, UpdateUserRestPort {
}
