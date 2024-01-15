package uz.brogrammers.eshop.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.brogrammers.eshop.security.utils.Utils;
import uz.brogrammers.eshop.user.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Builder
@Data
public class UserPrincipal implements UserDetails {

    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private Collection authorities;

    public static UserPrincipal build(User user) {

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName().name())
                ).toList();

        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getName())
                .lastName(user.getSurname())
                .isAdmin(Utils.isAdmin(user))
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }
}
