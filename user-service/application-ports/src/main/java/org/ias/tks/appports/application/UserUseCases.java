package org.ias.tks.appports.application;

import org.ias.tks.appports.application.user.CreateUserUseCase;
import org.ias.tks.appports.application.user.GetUserUseCase;
import org.ias.tks.appports.application.user.UpdateUserUseCase;

public interface UserUseCases extends CreateUserUseCase, GetUserUseCase, UpdateUserUseCase {
}
