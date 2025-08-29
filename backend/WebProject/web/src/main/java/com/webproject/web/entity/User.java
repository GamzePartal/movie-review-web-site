package com.webproject.web.entity;

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
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;


    @ManyToOne(fetch = FetchType.EAGER , optional = false) //her kullanıcının mutlaka 1 rolu olacak
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }
    @Override public boolean isAccountNonExpired(){ return true; }
    @Override public boolean isAccountNonLocked() {return true;}
    @Override public boolean isCredentialsNonExpired() {return true;}
    @Override public boolean isEnabled() {return true;}



}
