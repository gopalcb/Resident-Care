package com.usermanagerkafkaproducer.service;

import com.usermanagerkafkaproducer.entity.User;
import com.usermanagerkafkaproducer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    // use user repository and resolve dependency using Autowired
    @Autowired
    private UserRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public User saveUser(User user){
        User existingUser = getUserByEmail(user.getEmail());
        if (existingUser != null){
            return existingUser;
        }

        user.setPassword(AES256.encrypt(user.getPassword()));
        return repository.save(user);
    }

    public List<User> saveUsers(List<User> users){
        return repository.saveAll(users);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(int id){
        return repository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email){
        List<User> users = repository.findByEmail(email);
        if (users.size() == 0){
            return null;
        }
        return users.get(0);
    }

    public boolean deleteUser(int id){
        repository.deleteById(id);
        return true;
    }

    public User authUser(String email, String password){
        User existingUser = getUserByEmail(email);
        if (existingUser == null){
            return null;
        }
        if (existingUser.getPassword() == password){
            return existingUser;
        }
        return null;
    }

    public boolean isAuthUser(String email, String password){
        User existingUser = getUserByEmail(email);
        if (existingUser == null){
            return false;
        }
        if (existingUser.getPassword() == password){
            return true;
        }
        return false;
    }

    public List<User> getUsersByPropertyType(int propertyId, String key){
        List<User> users = repository.getUsersByPropertyType(propertyId, key);
        return users;
    }

    public List<User> getTenants(){
        List<User> users = repository.getTenants();
        return users;
    }
}
