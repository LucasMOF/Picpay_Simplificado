package pratica.picpaySimplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pratica.picpaySimplificado.domain.User;
import pratica.picpaySimplificado.domain.UserType;
import pratica.picpaySimplificado.dtos.RequestUserDTO;
import pratica.picpaySimplificado.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {

        if (sender.getUserType().equals(UserType.MERCHANT)) {
            throw new Exception("Usuário do tipo merchant, não está autorizado a fazer transações");
        }
        if (sender.getSaldo().compareTo(amount) <= 0) {
            throw new Exception("Não é possível realizar a transação, o valor da transação é maior que o seu saldo");
        }
    }

    public User findById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void save(User user) {
        this.userRepository.save(user);
    }

    public User createUser(@RequestBody RequestUserDTO requestUserDTO) {
        User user = new User();

        user.setName(requestUserDTO.name());
        user.setDocument(requestUserDTO.document());
        user.setEmail(requestUserDTO.email());
        user.setPassword(requestUserDTO.password());
        user.setSaldo(requestUserDTO.saldo());
        user.setUserType(requestUserDTO.userType());

        this.save(user);
        return user;
    }

    public List<User> allUsers() {
        return this.userRepository.findAll();
    }

}