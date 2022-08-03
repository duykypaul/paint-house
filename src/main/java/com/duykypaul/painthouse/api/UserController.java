package com.duykypaul.painthouse.api;

import com.duykypaul.painthouse.dto.UserDTO;
import com.duykypaul.painthouse.dto.request.LoginReq;
import com.duykypaul.painthouse.model.User;
import com.duykypaul.painthouse.service.CommonService;
import com.duykypaul.painthouse.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;
    final CommonService commonService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> findAllPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        var users = userService.findAll(pageNo, pageSize, sortBy);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginReq loginReq) {
        return userService.signIn(loginReq);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo() {
        User user = commonService.getUser();
        if(user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
