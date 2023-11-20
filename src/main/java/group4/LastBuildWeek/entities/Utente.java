package group4.LastBuildWeek.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import group4.LastBuildWeek.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="utenti")
@JsonIgnoreProperties({ "authorities", "enabled", "credentialsNonExpired", "accountNonExpired", "accountNonLocked"})
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="nome")
    private String nome;
    @Column(name="cognome")
    private String cognome;
    @Column(name="avatar")
    private String avatar;
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    public Utente(String username, String email, String password, String nome, String cognome, List<Role> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.role != null) {
            authorities.addAll(this.role.stream()
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .toList());
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
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
}