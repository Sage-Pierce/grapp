package org.codegas.security.service_impl.api;

import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.security.domain.entity.Credential;
import org.codegas.security.domain.entity.User;
import org.codegas.security.domain.repository.CredentialRepository;
import org.codegas.security.domain.repository.UserRepository;
import org.codegas.security.service.api.AuthService;
import org.codegas.security.service.api.Authorization;
import org.codegas.security.service.api.AuthorizationException;
import org.codegas.security.service.dto.UserDto;
import org.codegas.security.service_impl.factory.UserDtoFactory;

@Transactional
public abstract class AbstractAuthService implements AuthService {

    protected final CredentialRepository credentialRepository;

    protected final UserRepository userRepository;

    public AbstractAuthService(CredentialRepository credentialRepository, UserRepository userRepository) {
        this.credentialRepository = credentialRepository;
        this.userRepository = userRepository;
    }

    @Override
    public final UserDto authenticate(Authorization<String> authorization) throws AuthorizationException {
        return credentialRepository.findByAccessToken(authorization.getToken())
            .filter(Credential::isFresh)
            .flatMap(userRepository::findByCredential)
            .map(UserDtoFactory::createDto)
            .orElseThrow(AuthorizationException::new);
    }

    @Override
    public final UserDto logOut(Authorization<String> authorization) throws AuthorizationException {
        User user = credentialRepository.findByAccessToken(authorization.getToken())
            .filter(Credential::isFresh)
            .flatMap(userRepository::findByCredential)
            .orElseThrow(AuthorizationException::new);
        user.logOut();
        return UserDtoFactory.createDto(user);
    }
}
