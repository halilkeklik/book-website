package com.halil.bookwebsite.services.impl;

import com.halil.bookwebsite.dtos.SignInResultDto;
import com.halil.bookwebsite.entities.Permission;
import com.halil.bookwebsite.entities.User;
import com.halil.bookwebsite.exceptions.AlreadyExistsException;
import com.halil.bookwebsite.exceptions.NotFoundException;
import com.halil.bookwebsite.repositories.PermissionRepository;
import com.halil.bookwebsite.repositories.UserRepository;
import com.halil.bookwebsite.security.jwt.JwtUtils;
import com.halil.bookwebsite.security.user.UserDetailsImpl;
import com.halil.bookwebsite.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User getByID(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new NotFoundException("User not found");
        return userOptional.get();
    }

    @Override
    public User update(User user) {
        if (!userRepository.existsById(user.getId()))
            throw new NotFoundException("User not found");
        return userRepository.save(user);
    }

    @Override
    public void deleteByID(Long id) {
        if (!userRepository.existsById(id))
            throw new NotFoundException("User not found");
        userRepository.deleteById(id);
    }

    @Override
    public User registerUser(User user) {

        if (userRepository.existsByUsername(user.getUsername()))
            throw new AlreadyExistsException(user.getUsername());

        setDefaultPermission(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public SignInResultDto loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return SignInResultDto.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .token(jwt)
                .type("Bearer")
                .build();
    }

    @Override
    public boolean checkCurrentUserHasEntity(Long userId, Long entityId) {
        return true;
    }

    private void setDefaultPermission(User user) {
        List<String> permissions = List.of(
                "manageUserSelf",
                "manageReviewUser"
        );

        for (String permission : permissions) {
            Optional<Permission> optionalPermission = permissionRepository.findByName(permission);
            if (optionalPermission.isEmpty()) {
                throw new NotFoundException("Permission not found");
            }
            user.getPermissions().add(optionalPermission.get());
        }

    }
}
