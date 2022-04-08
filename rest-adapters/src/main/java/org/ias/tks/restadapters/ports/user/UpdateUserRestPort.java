package org.ias.tks.restadapters.ports.user;

import org.ias.tks.restadapters.dto.user.UserInputDto;

public interface UpdateUserRestPort {
    void updateUser(String login, UserInputDto user);

    void activateUser(String login);

    void deactivateUser(String login);
}
