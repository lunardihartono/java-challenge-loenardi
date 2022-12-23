package ist.challenge.loenardi.service;

import ist.challenge.loenardi.model.UsersModel;
import ist.challenge.loenardi.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public Object registerUser(String username, String password){

        if (username ==null || password ==null){
            return null;
        } else {
            Optional<UsersModel> checkUsername = usersRepository.findByUsername(username);

            if (checkUsername != null){
                UsersModel usersModel = new UsersModel();
                usersModel.setUsername(username);
                usersModel.setPassword(password);
                return usersRepository.save(usersModel);
            } else
            return "409";
        }
    }

    public UsersModel authenticate(String username, String password){
        return usersRepository.findByUsernameAndPassword(username,password).orElse(null);
    }

}
