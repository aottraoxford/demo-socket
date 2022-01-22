package com.socket.security;

import com.socket.exception.Error;
import com.socket.model.User;
import com.socket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoadUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) throw new Error(404, "username " + s + " not found");
        if (user.getPassword() == null) throw new Error(403, "password not yet register");
        if (user.getUserRoles().isEmpty()) throw new Error( 403, "user need to assign role");
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new Error(404, "user id " + id + " not found");
        return UserPrincipal.create(user);
    }
}
