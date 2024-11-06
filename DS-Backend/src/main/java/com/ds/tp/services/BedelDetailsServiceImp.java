package com.ds.tp.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ds.tp.models.usuario.Bedel;
import com.ds.tp.models.usuario.SecurityBedel;
import com.ds.tp.repositories.BedelRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class BedelDetailsServiceImp implements UserDetailsService{
    private BedelRepository bedelRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Bedel> bedel= bedelRepository.findByUsuario(username);
       if(bedel==null) {
        throw new UsernameNotFoundException("Usuario no encontrado");
       }
        return new SecurityBedel(bedel);
    }

}
