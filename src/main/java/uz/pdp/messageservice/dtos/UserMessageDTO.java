package uz.pdp.messageservice.dtos;

import jakarta.validation.constraints.NotNull;

public record UserMessageDTO(
        @NotNull(message = "sender id must not be null")
        Long senderId,

        @NotNull(message = "receiver id must not be null")
        Long receiverId
) {
}
