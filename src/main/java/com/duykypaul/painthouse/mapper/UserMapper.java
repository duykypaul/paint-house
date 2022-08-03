package com.duykypaul.painthouse.mapper;

import com.duykypaul.painthouse.dto.UserDTO;
import com.duykypaul.painthouse.model.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @InheritConfiguration
    UserDTO toDTO(User user);

    @InheritConfiguration
    User toEntity(UserDTO userDTO);

    @InheritConfiguration
    List<UserDTO> toListDTO(List<User> user);

    @InheritConfiguration
    List<User> toListEntity(List<UserDTO> user);
}
