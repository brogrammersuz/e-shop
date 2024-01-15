package uz.brogrammers.eshop.user.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class UserSummary {


    private Integer id;

    private String name;

    private String surname;

    private Collection<? extends GrantedAuthority> roles;

    public UserSummary(Integer id, String name, String surname, Collection<? extends GrantedAuthority> roles) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }
}
