package org.ias.tks.restadapters.ports.user;


import org.ias.tks.restadapters.dto.user.UserInputDto;

public interface CreateUserRestPort {
    void addUser(UserInputDto user);
}
