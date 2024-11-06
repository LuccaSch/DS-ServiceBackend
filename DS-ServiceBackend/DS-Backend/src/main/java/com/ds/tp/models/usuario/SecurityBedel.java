package com.ds.tp.models.usuario;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SecurityBedel implements UserDetails{

    private Bedel bedel;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
      return  bedel.getContrasenia();
    }

    @Override
    public String getUsername() {
       return bedel.getUsuario();
    }

    public SecurityBedel(Optional<Bedel> bedel2) {
        //TODO Auto-generated constructor stub
    }
    

}
