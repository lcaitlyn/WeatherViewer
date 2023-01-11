package edu.lcaitlyn.weatherviewer;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

public class Program {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepository();

        List<User> list = usersRepository.findAll();

        for (User u : list) {
            System.out.println(u);
        }
    }
}
