package com.duykypaul.painthouse.api;

import com.duykypaul.painthouse.dto.JwtDTO;
import com.duykypaul.painthouse.dto.UserDTO;
import com.duykypaul.painthouse.dto.request.LoginReq;
import com.duykypaul.painthouse.service.CommonService;
import com.duykypaul.painthouse.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class UserController {
    final UserService userService;
    final CommonService commonService;

    @GetMapping(value = "/{id}")
    public UserDTO findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/pagination")
    public List<UserDTO> findAllPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return userService.findAll(pageNo, pageSize, sortBy);
    }

    @PostMapping("/login")
    public JwtDTO signIn(@Valid @RequestBody LoginReq loginReq) {
        return userService.signIn(loginReq);
    }

    @GetMapping("/me")
    public UserDTO getUserInfo() {
        return commonService.getUser();
    }
}
