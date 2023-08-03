package uz.pdp.messageservice.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import uz.pdp.messageservice.dtos.MessageCreateDTO;
import uz.pdp.messageservice.dtos.MessageUpdateDTO;
import uz.pdp.messageservice.entity.user.Message;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {

    MessageMapper MESSAGE_MAPPER = Mappers.getMapper(MessageMapper.class);

    Message toEntity(MessageCreateDTO messageCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Message partialUpdate(MessageUpdateDTO messageUpdateDTO, @MappingTarget Message message);
}
