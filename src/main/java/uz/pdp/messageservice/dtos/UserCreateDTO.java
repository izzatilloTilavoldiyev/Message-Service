package uz.pdp.messageservice.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
        @NotBlank(message = "Name must not be blank")
        String name,
        @NotBlank(message = "Username must not be blank")
        String username,
        String image_path
) {
}
