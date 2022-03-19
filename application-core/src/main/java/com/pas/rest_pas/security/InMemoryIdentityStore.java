package com.pas.rest_pas.security;


import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.UserByLoginNotFound;
import com.pas.rest_pas.managers.UserManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    @Inject
    UserManager userManager;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential usernamePasswordCredential) {
            try {
                User user = userManager.findByLoginPasswordActive(usernamePasswordCredential.getCaller(), usernamePasswordCredential.getPasswordAsString());
                return (null != user ? new CredentialValidationResult(user.getLogin(), new HashSet<>(List.of(user.getAccessLevel()))) : CredentialValidationResult.NOT_VALIDATED_RESULT);
            } catch (UserByLoginNotFound e) {
                return CredentialValidationResult.NOT_VALIDATED_RESULT;
            }
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        return IdentityStore.super.getCallerGroups(validationResult);
    }


}
