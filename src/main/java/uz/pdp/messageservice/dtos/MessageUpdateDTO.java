package uz.pdp.messageservice.dtos;

import jakarta.validation.constraints.NotNull;

public record MessageUpdateDTO(
        String text,

        @NotNull(message = "id must not be null")
        Long id
) {
}
