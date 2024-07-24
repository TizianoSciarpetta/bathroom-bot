package com.telegram.poopbot.service;

import com.telegram.poopbot.model.PoopUser;
import com.telegram.poopbot.repository.PoopUserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class PoopUserService {

    private final PoopUserRepository repository;

    public PoopUserService(PoopUserRepository repository) {
        this.repository = repository;
    }//------------------------------------------------------------------------------------------------------------------------------------

    public boolean existsById(long id) {
        return repository.existsById(id);
    }//------------------------------------------------------------------------------------------------------------------------------------

    @Async
    public void checkAndSave(User user) {
        boolean exists = repository.existsByIdAndFirstNameAndLastNameAndUserName(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName());
        if(!exists) {
            PoopUser poopUser = new PoopUser(user);
            repository.save(poopUser);
        }
    }//------------------------------------------------------------------------------------------------------------------------------------

}