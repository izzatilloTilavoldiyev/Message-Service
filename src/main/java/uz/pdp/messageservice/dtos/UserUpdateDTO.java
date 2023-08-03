package uz.pdp.messageservice.dtos;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
        String name,
        String username,
        String image_path,
        @NotNull(message = "Id must not be null")
        Long id
) {
}
