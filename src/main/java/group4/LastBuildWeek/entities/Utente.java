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

import java.util.Collection;
import java.util.List;

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
    private Role role;

    public Utente(String username, String email, String password, String nome, String cognome, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
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