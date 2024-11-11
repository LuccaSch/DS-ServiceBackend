package com.ds.tp.models.usuario;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class BedelUserDetails implements UserDetails {

    private final Bedel bedel;

    public BedelUserDetails(Bedel bedel) {
        this.bedel = bedel;
    }

    @Override
    public String getPassword() {
        return bedel.getContrasenia(); 
    }

    @Override
    public String getUsername() {
        return bedel.getUsuario(); 
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return null;
    }
}
