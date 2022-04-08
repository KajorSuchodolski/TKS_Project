package org.ias.tks.restadapters.ports.user;

import org.ias.tks.restadapters.dto.user.UserOutputDto;

import java.util.List;
import java.util.UUID;

public interface GetUserRestPort {
    UserOutputDto getUserById(UUID id);

    UserOutputDto getUserByLogin(String login);

    List<UserOutputDto> getAll();

    UserOutputDto findByLoginPasswordActive(String login, String password);

    List<UserOutputDto> searchUsersByLogin(String login);
}
