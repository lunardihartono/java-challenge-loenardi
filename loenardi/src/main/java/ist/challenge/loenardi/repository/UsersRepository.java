package ist.challenge.loenardi.repository;

import ist.challenge.loenardi.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel,Integer> {
    Optional<UsersModel> findByUsername(String username);
    Optional<UsersModel> findByUsernameAndPassword(String username, String password);

}
