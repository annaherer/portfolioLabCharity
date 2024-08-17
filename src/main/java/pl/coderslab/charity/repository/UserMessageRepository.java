package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.UserMessage;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Integer> {
}