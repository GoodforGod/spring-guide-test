package io.spring.guide.controller;

import io.spring.guide.model.dto.UserTO;
import io.spring.guide.service.modelbased.IUserModelService;
import io.spring.guide.service.modelbased.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
@RestController
public class UserRestController {

    private final IUserModelService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/user")
    public ResponseEntity<UserTO> create(@RequestParam String email,
                                       @RequestParam String name) {
        final UserTO user = UserTO.of(userService.create(email, name));
        return (user != null)
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/api/user")
    public ResponseEntity<UserTO> find(@RequestParam String id) {
        final UserTO user = UserTO.of(userService.find(id));
        return (user != null)
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
