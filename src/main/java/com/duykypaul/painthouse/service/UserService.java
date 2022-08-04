package com.duykypaul.painthouse.service;


import com.duykypaul.painthouse.dto.JwtDTO;
import com.duykypaul.painthouse.dto.UserDTO;
import com.duykypaul.painthouse.dto.request.LoginReq;
import com.duykypaul.painthouse.model.User;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface UserService extends GenericService<User, Long, UserDTO> {
    JwtDTO signIn(@Valid LoginReq loginReq);

    UserDTO findById(Long id);

    User findByUsername(String username);

    List<UserDTO> findAll(Integer pageNo, Integer pageSize, String sortBy);
}
