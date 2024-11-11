package com.ds.tp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ds.tp.models.securityconfig.BedelUserDetails;
import com.ds.tp.models.usuario.Bedel;
import com.ds.tp.repositories.BedelRepository;

@Service
public class BedelServiceUserDetails  implements UserDetailsService {
private final BedelRepository bedelRepository;


    public BedelServiceUserDetails(BedelRepository bedelRepository) {
        this.bedelRepository = bedelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Bedel bedel = bedelRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        return new BedelUserDetails(bedel); 
    }
}
