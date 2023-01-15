package edu.lcaitlyn.weatherviewer.services;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.models.UserSession;
import edu.lcaitlyn.weatherviewer.repositories.UserSessionsRepository;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UserSessionsServiceImpl implements UserSessionsService {
    private final UsersRepository usersRepository;
    private final UserSessionsRepository userSessionsRepository;

    public UserSessionsServiceImpl(UsersRepository usersRepository, UserSessionsRepository userSessionsRepository) {
        this.usersRepository = usersRepository;
        this.userSessionsRepository = userSessionsRepository;
    }

    @Override
    public Cookie createCookie(User user) {
        String id = UUID.randomUUID().toString();

        UserSession userSession = new UserSession(id, user, LocalDateTime.now().plusHours(24));

        userSessionsRepository.save(userSession);

        Cookie cookie = new Cookie("usersessionid", id);

        return cookie;
    }

    @Override
    public boolean isExists(String userSessionId) {
        return userSessionsRepository.findById(userSessionId).isPresent();
    }

    @Override
    public boolean isExpiredSession(String userSessionId) {
        Optional<UserSession> userSession = userSessionsRepository.findById(userSessionId);

        return userSession.isPresent() && userSession.get().getExpiresAt().isBefore(LocalDateTime.now());
    }

    @Override
    public Optional<User> getUser(String userSessionId) {
        Optional<UserSession> userSession = userSessionsRepository.findById(userSessionId);

        if (userSession.isPresent()) {
            return Optional.of(userSession.get().getUser());
        }

        return Optional.empty();
    }

    @Override
    public void deleteSession(String userSessionId) {
        userSessionsRepository.delete(userSessionId);
    }
}
