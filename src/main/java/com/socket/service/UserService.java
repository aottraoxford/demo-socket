package com.socket.service;

import com.socket.base.BaseService;
import com.socket.model.Role;
import com.socket.model.User;
import com.socket.model.UserRole;
import com.socket.model.enums.RoleType;
import com.socket.payload.request.SignIn;
import com.socket.payload.response.JwtToken;
import com.socket.repository.RoleRepository;
import com.socket.repository.UserRepository;
import com.socket.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class UserService implements BaseService<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User add(User t) {
        if (t.getPassword()!=null) {
            t.setPassword(passwordEncoder.encode(t.getPassword()));
        }
        Role r = roleRepository.findByType(RoleType.USER);
        if (r != null) {
            UserRole userRole = new UserRole();
            userRole.setRole(r);
            userRole.setUser(t);
            t.getUserRoles().add(userRole);
        } else {
            Role newRole = new Role();
            newRole.setType(RoleType.USER);

            UserRole userRole = new UserRole();
            userRole.setRole(newRole);
            userRole.setUser(t);
            t.getUserRoles().add(userRole);
        }
        return userRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteBatch(Long[] listId) {
        List<User> listUser = userRepository.findAllInId(Arrays.asList(listId));
        userRepository.deleteAll(listUser);
    }

    public List<User> updateBatch(List<User> listUser) {
        return userRepository.saveAll(listUser);
    }

    @Override
    public Page<User> getPage(String search, int page, int size) {
        return userRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (search!=null) {
                Predicate username = cb.like(cb.upper(root.get("username")), "%" + search.toUpperCase(Locale.ROOT) + "%");
                predicates.add(cb.or(username));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, PageRequest.of(page - 1,size, Sort.by(Sort.Direction.DESC,"id")));
    }

    @Override
    public User get(Long id) {
        return null;
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public JwtToken signIn(SignIn signIn) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getUsername(),signIn.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(tokenProvider.generateToken(authentication));
        return jwtToken;
    }
}
