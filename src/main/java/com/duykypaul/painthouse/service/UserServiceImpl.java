package com.duykypaul.painthouse.service;


import com.duykypaul.painthouse.common.MessageUtils;
import com.duykypaul.painthouse.dto.JwtDTO;
import com.duykypaul.painthouse.dto.UserDTO;
import com.duykypaul.painthouse.dto.request.LoginReq;
import com.duykypaul.painthouse.exception.ApplicationException;
import com.duykypaul.painthouse.mapper.UserMapper;
import com.duykypaul.painthouse.model.User;
import com.duykypaul.painthouse.repository.RoleRepository;
import com.duykypaul.painthouse.repository.UserRepository;
import com.duykypaul.painthouse.security.jwt.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long, UserDTO> implements UserService {

    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;

    static final UserMapper userMapper = UserMapper.INSTANCE;

    final JwtUtils jwtUtils;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        super(userRepository);
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User toEntity(UserDTO element) {
        return userMapper.toEntity(element);
    }

    @Override
    public UserDTO toDTO(User element) {
        return userMapper.toDTO(element);
    }

    @Override
    public JwtDTO login(@Valid LoginReq loginReq) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword());
        Authentication authentication = authenticationManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        UserDTO userDTO = new UserDTO();
        if (user.isPresent()) {
            userDTO = userMapper.toDTO(user.get());
        }
        return new JwtDTO(HttpStatus.OK, jwtToken, userDTO);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        MessageUtils.getMessage("user.notfound", id))
                );
        return userMapper.toDTO(user);
    }

    @Override
    public User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User Id is not found"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    @Override
    public List<UserDTO> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<User> users = userRepository.findAll(paging).getContent();
        return userMapper.toListDTO(users);
    }
}
