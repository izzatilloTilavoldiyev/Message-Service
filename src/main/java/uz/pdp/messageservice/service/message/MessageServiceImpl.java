package uz.pdp.messageservice.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.messageservice.dtos.MessageCreateDTO;
import uz.pdp.messageservice.dtos.MessageUpdateDTO;
import uz.pdp.messageservice.dtos.UserMessageDTO;
import uz.pdp.messageservice.entity.user.Message;
import uz.pdp.messageservice.entity.user.User;
import uz.pdp.messageservice.exception.DataNotFoundException;
import uz.pdp.messageservice.repository.MessageRepository;
import uz.pdp.messageservice.service.user.UserServiceImpl;

import java.util.List;

import static uz.pdp.messageservice.mappers.MessageMapper.MESSAGE_MAPPER;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    private final UserServiceImpl userService;

    @Override
    public Message create(MessageCreateDTO createDTO) {
        User sender = userService.getUserById(createDTO.senderId());
        User receiver = userService.getUserById(createDTO.receiverId());
        Message message = MESSAGE_MAPPER.toEntity(createDTO);
        message.setSender(sender);
        message.setReceiver(receiver);
        return messageRepository.save(message);
    }

    @Override
    public Message update(MessageUpdateDTO updateDTO) {
        Message message = getMessageById(updateDTO.id());
        Message update = MESSAGE_MAPPER.partialUpdate(updateDTO, message);
        return messageRepository.save(update);
    }

    @Override
    public List<Message> getMessages(UserMessageDTO userMessageDTO) {
        User sender = userService.getUserById(userMessageDTO.senderId());
        User receiver = userService.getUserById(userMessageDTO.receiverId());
        return messageRepository.findAllBySenderAndReceiver(sender, receiver);
    }

    @Override
    public void delete(Long messageId) {
        if (!messageRepository.existsById(messageId))
            throw new DataNotFoundException("Message not found with '" + messageId + "' id");
        messageRepository.deleteById(messageId);
    }

    private Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(
                () -> new DataNotFoundException("Message not found with '" + messageId + " id")
        );
    }

}
