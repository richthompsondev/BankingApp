package se.emanuel.bank;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {

    Optional<Person> findByPersonid(int personid);
    Optional<Person> findPersonByCardnumber(int cardId);
    List<Person> findPersonByPersonid(int id);


}
