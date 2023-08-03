package uz.pdp.messageservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageCreateDTO(
        @NotBlank(message = "text must not be blank")
        String text,

        @NotNull(message = "sender id must not be null")
        Long senderId,

        @NotNull(message = "receiver id must not be null")
        Long receiverId
) {
}
