package br.akd.svc.akadia.config.security;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ModulosEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String nomeUsuario;
    private final String senha;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserSS(Long id, String nomeUsuario, String senha, Set<ModulosEnum> perfis) {
        super();
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority("ROLE_" + x.getRole())).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nomeUsuario;
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
