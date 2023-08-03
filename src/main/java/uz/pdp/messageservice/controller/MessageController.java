package uz.pdp.messageservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.messageservice.dtos.MessageCreateDTO;
import uz.pdp.messageservice.dtos.MessageUpdateDTO;
import uz.pdp.messageservice.dtos.UserMessageDTO;
import uz.pdp.messageservice.entity.user.Message;
import uz.pdp.messageservice.service.message.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<Message> create(
            @Valid @RequestBody MessageCreateDTO createDTO
    ) {
        Message message = messageService.create(createDTO);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(
            @Valid @RequestBody MessageUpdateDTO updateDTO
    ) {
        Message update = messageService.update(updateDTO);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<String> delete(
            @PathVariable Long messageId
    ) {
        messageService.delete(messageId);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/get-messages")
    public ResponseEntity<List<Message>> getMessages(
            @Valid @RequestBody UserMessageDTO userMessageDTO
            ) {
        List<Message> messages = messageService.getMessages(userMessageDTO);
        return ResponseEntity.ok(messages);
    }
}
