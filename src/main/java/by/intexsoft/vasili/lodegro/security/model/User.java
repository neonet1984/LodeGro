package by.intexsoft.vasili.lodegro.security.model;

import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor for {@link CustomUserDetails}
     */
    public User(User user) {
        this.username = user.username;
        this.password = user.password;
        this.enabled = user.enabled;
        this.authorities = user.authorities;
    }

    /**
     * Identification for {@link User}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * username
     */
    @Column(nullable = false)
    public String username;

    /**
     * password
     */
    @Column(nullable = false)
    public String password;

    /**
     * Enable account
     */
    @Column
    public boolean enabled;

    /**
     * List of roles for {@link User}
     */
    @ManyToMany(targetEntity = Authority.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    public Set<Authority> authorities;

    /**
     * Convenient for logging view
     */
    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", username: " + username +
                ", authorities: " + AuthorityUtils.authorityListToSet(authorities).toString() +
                ", enabled: " + enabled +
                "}";
    }
}
