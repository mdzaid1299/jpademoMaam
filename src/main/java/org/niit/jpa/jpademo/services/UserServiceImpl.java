package org.niit.jpa.jpademo.services;

import org.niit.jpa.jpademo.domain.User;
import org.niit.jpa.jpademo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService
{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository= userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //http://localhost:8080/userdata/v1/risha@.niit.com
    @Override
    public User updateUser(User user, String email) {
        //optional it is predefined Spring COntainer Object
        //we are storing the data based on email. if the data is their it will store in optional object
        //or else it store null value
        //isEMpty
        Optional<User> opuser = userRepository.findById(email);
        if(opuser.isEmpty())
        {
            return null;

        }
        User existinguser = opuser.get();// get the value

        if(user.getFirstname()!=null)
        {
            existinguser.setFirstname(user.getFirstname());

        }

        if(user.getLastname()!=null)
        {
            existinguser.setLastname(user.getLastname());

        }

        if(user.getPassword()!=null)
        {
            existinguser.setPassword(user.getPassword());
        }

        return userRepository.save(existinguser);
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        userRepository.deleteById(email);
        return  true;
    }

    @Override
    public List<User> getUserByFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }
}
