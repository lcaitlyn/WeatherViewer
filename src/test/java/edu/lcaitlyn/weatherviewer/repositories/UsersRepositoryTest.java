package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.User;
import org.junit.Assert;
import org.junit.Test;

public class UsersRepositoryTest {
    private final UsersRepository usersRepository = new UsersRepository();

    @Test
    public void isUserExists() throws Exception {
        Assert.assertTrue(usersRepository.findById(1L).isPresent());
    }

    @Test
    public void isUserNotExists() throws Exception {
        Assert.assertFalse(usersRepository.findById(100L).isPresent());
    }


    @Test
    public void getUser() throws Exception {
        Assert.assertEquals("email1@email.com", usersRepository.findById(1L).get().getEmail());
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User("valera1@qwe.ru", "qwe");

        usersRepository.save(user);

        Assert.assertNotNull(user.getId());
    }

    @Test
    public void updateUser() throws Exception {
        User user = usersRepository.findById(1L).get();

        user.setPassword("zxc");

        usersRepository.update(user);
    }
}
