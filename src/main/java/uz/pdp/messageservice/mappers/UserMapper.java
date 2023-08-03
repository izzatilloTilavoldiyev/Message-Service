package uz.pdp.messageservice.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.messageservice.dtos.UserCreateDTO;
import uz.pdp.messageservice.dtos.UserUpdateDTO;
import uz.pdp.messageservice.entity.user.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User toEntity(UserCreateDTO userCreateDTO);

//    @InheritInverseConfiguration(name = "toEntity")
    UserCreateDTO toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserUpdateDTO userUpdateDTO, @MappingTarget User user);
}
