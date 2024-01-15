package uz.brogrammers.eshop.security.utils;

import uz.brogrammers.eshop.user.entity.RoleName;
import uz.brogrammers.eshop.user.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public static boolean isAdmin(User user) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getName().name().equals(RoleName.ROLE_ADMIN.name()));
    }


    public static Set<RoleName> getUserRoles(final User user) {
        return user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet());
    }

}
