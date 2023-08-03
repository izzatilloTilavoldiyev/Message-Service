package uz.pdp.messageservice.service.user;

import uz.pdp.messageservice.dtos.UserCreateDTO;
import uz.pdp.messageservice.dtos.UserUpdateDTO;
import uz.pdp.messageservice.entity.user.User;

import java.util.List;

public interface UserService {
    UserCreateDTO create(UserCreateDTO userCreateDTO);

    UserCreateDTO update(UserUpdateDTO userUpdateDTO);

    UserCreateDTO getById(Long userId);

    List<User> getAll();

    void deleteById(Long id);

}
