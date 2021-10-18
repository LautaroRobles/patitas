package com.patitas.seguridad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patitas.modelo.Organizacion;
import com.patitas.modelo.Persona;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@Entity
@Table
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @OneToOne
    private Persona persona;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    // IGNORAR
    @Transient
    @JsonIgnore
    protected boolean accountNonExpired = true;

    @Transient
    @JsonIgnore
    protected boolean accountNonLocked = true;

    @Transient
    @JsonIgnore
    protected boolean credentialsNonExpired = true;

    @Transient
    @JsonIgnore
    protected boolean enabled = true;

    @Transient
    @JsonIgnore
    protected Collection<? extends GrantedAuthority> authorities;
}
