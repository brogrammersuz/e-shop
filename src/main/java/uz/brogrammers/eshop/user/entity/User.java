package uz.brogrammers.eshop.user.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created", nullable = false)
    private ZonedDateTime created;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;

    @NaturalId
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_roles",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();





}
