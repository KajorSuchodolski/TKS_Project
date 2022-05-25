package org.ias.tks.appports.infrastructure;

import org.ias.tks.appports.infrastructure.user.CreateUserPort;
import org.ias.tks.appports.infrastructure.user.GetUserPort;
import org.ias.tks.appports.infrastructure.user.UpdateUserPort;

public interface UserCRUDPorts extends CreateUserPort, GetUserPort, UpdateUserPort {
}
