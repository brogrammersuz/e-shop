package uz.brogrammers.eshop.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.brogrammers.eshop.security.annotation.CurrentUser;
import uz.brogrammers.eshop.security.model.UserPrincipal;
import uz.brogrammers.eshop.user.dto.UserSummary;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {

        System.out.println(currentUser.getId());

        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getAuthorities());

        return userSummary;

    }

}
