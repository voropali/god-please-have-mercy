package cz.cvut.ear.clubevidence.rest;

import cz.cvut.ear.clubevidence.model.User;
import cz.cvut.ear.clubevidence.model.enums.Role;
import cz.cvut.ear.clubevidence.rest.util.RestUtils;
import cz.cvut.ear.clubevidence.security.model.UserDetails;
import cz.cvut.ear.clubevidence.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rest/users")
@Validated
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @PreAuthorize("(!#user.isAdmin() && anonymous) || hasRole('ROLE_ADMIN')")
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> register(@RequestBody User user) {
//        userService.persist(user);
//        LOG.debug("User {} successfully registered.", user);
//        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
@PreAuthorize("(!#user.isAdmin() && anonymous) || hasRole('ROLE_ADMIN')")
@PostMapping(value ="/members",consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Void> register(@RequestBody User user) {
    userService.persist(user);
    final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
}


    // @PreAuthorize("hasRole('ROLE_ADMIN')")
   // @PostMapping(value ="/members", consumes = MediaType.APPLICATION_JSON_VALUE)
   // public ResponseEntity<Void> registerMember(@RequestBody User newMember) {
   //     userService.persist(newMember);
   //     //userService.setRole(userService.findById(newMember.getId()), Role.USER);
   //     LOG.debug("Member {} successfully registered.", newMember.getUsername());
   //     final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
   //     return new ResponseEntity<>(headers, HttpStatus.CREATED);
   // }

    @PostMapping(value ="/premiumMembers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerPremiumMember(@RequestBody User newMember) {
        userService.persist(newMember);
        userService.setRole(userService.findById(newMember.getId()), Role.PREMIUM_USER);
        LOG.debug("Premium Member {} successfully registered.", newMember.getUsername());
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_GUEST')")
    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getCurrent(Authentication auth) {
        assert auth.getPrincipal() instanceof UserDetails;
        return ((UserDetails) auth.getPrincipal()).getUser();
    }
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_GUEST')")
    @GetMapping(value = "/allUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllUsers() {
        return userService.findAll();
    }


}
