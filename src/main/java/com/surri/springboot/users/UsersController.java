package com.surri.springboot.users;

import com.surri.springboot.users.Entities.User;

import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping
    public ResponseEntity<Page<User>> findAll() {
        try {
            Page<User> users = usersService.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> findById(@PathVariable Long id) {
        Optional<User> user = usersService.findById(id);

        if( !user.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<User> entityModel = EntityModel.of(user.get(),
            linkTo(methodOn(UsersController.class).findById(id)).withSelfRel(),
            linkTo(methodOn(UsersController.class).findAll()).withRel("users"));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
