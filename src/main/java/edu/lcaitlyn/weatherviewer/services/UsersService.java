package edu.lcaitlyn.weatherviewer.services;

public interface UsersService {
    void signUp(String email, String password);
    boolean signIn(String email, String password);
}
