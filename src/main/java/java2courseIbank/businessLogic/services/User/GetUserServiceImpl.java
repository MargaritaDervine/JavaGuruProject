package java2courseIbank.businessLogic.services.User;

import java2courseIbank.AppError;
import java2courseIbank.AppException;
import java2courseIbank.database.UserRepository;
import java2courseIbank.domain.User;
import java2courseIbank.web.DTOs.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class GetUserServiceImpl implements GetUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public GetUserResponse get(GetUserRequest request) {
        Optional<User> userOpt = userRepository.getUser(request.getUserName());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = new UserDTO(
                    user.getId(), user.getUsername(), user.getPassword()
            );
            return new GetUserResponse(userDTO);
        } else {
            AppError error = new AppError("username", " not found");
            throw new AppException(error);
        }
    }

}
