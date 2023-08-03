package uz.pdp.messageservice.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.messageservice.dtos.UserCreateDTO;
import uz.pdp.messageservice.dtos.UserUpdateDTO;
import uz.pdp.messageservice.entity.user.User;
import uz.pdp.messageservice.exception.DataNotFoundException;
import uz.pdp.messageservice.exception.DuplicateValueException;
import uz.pdp.messageservice.repository.UserRepository;

import java.util.List;

import static uz.pdp.messageservice.mappers.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserCreateDTO create(UserCreateDTO userCreateDTO) {
        userExistsByUsername(userCreateDTO.username());
        User user = USER_MAPPER.toEntity(userCreateDTO);
        return USER_MAPPER.toDto(userRepository.save(user));
    }

    @Override
    public UserCreateDTO update(UserUpdateDTO updateDTO) {
        User user = getUserById(updateDTO.id());
        if(updateDTO.username() != null)
            userExistsByUsername(updateDTO.username());
        User updatedUser = USER_MAPPER.partialUpdate(updateDTO, user);
        return USER_MAPPER.toDto(updatedUser);
    }

    @Override
    public UserCreateDTO getById(Long userId) {
        User user = getUserById(userId);
        return USER_MAPPER.toDto(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id))
            throw new DataNotFoundException("User not found with '" + id + "' id");
        userRepository.deleteById(id);
    }
    
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("User not found with '" + userId + "' id")
        );
    }

    private void userExistsByUsername(String username) {
        if (userRepository.existsUserByUsername(username))
            throw new DuplicateValueException(
                    "This user with '" + username + "' username already exists");
    }
}
