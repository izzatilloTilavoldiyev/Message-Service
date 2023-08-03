package uz.pdp.messageservice.service.message;

import uz.pdp.messageservice.dtos.MessageCreateDTO;
import uz.pdp.messageservice.dtos.MessageUpdateDTO;
import uz.pdp.messageservice.dtos.UserMessageDTO;
import uz.pdp.messageservice.entity.user.Message;

import java.util.List;

public interface MessageService {

    Message create(MessageCreateDTO createDTO);

    Message update(MessageUpdateDTO updateDTO);

    List<Message> getMessages(UserMessageDTO userMessageDTO);

    void delete(Long messageId);
}
