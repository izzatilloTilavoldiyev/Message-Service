package uz.pdp.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.messageservice.entity.user.Message;
import uz.pdp.messageservice.entity.user.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("from messages m where m.sender = :sender and m.receiver = :receiver or m.receiver = :sender and m.sender = :receiver")
    List<Message> findAllBySenderAndReceiver(User sender, User receiver);
}
