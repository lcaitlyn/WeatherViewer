package edu.lcaitlyn.weatherviewer.services;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UsersServiceImpl implements UsersService{
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Deprecated
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void signUp(String email, String password) {
        usersRepository.save(new User(email, passwordEncoder.encode(password)));
    }

    @Override
    public boolean signIn(String email, String password) {
        Optional<User> user = usersRepository.findByEmail(email);

        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
}
