package com.halil.bookwebsite.seeds;

import com.halil.bookwebsite.entities.Permission;
import com.halil.bookwebsite.entities.User;
import com.halil.bookwebsite.exceptions.NotFoundException;
import com.halil.bookwebsite.repositories.PermissionRepository;
import com.halil.bookwebsite.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AdminPermissionSeeder {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String adminPassword;

    @EventListener
    @Transactional
    public void LoadAdmin(PermissionsSeededEvent event) {
        User admin = User.builder()
                .fullName(adminName)
                .username(username)
                .password(passwordEncoder.encode(adminPassword))
                .permissions(new ArrayList<>())
                .build();

        List<String> permissions = List.of(
                "manageUserAdmin",
                "manageReviewAdmin",
                "manageCategoryAdmin",
                "manageBookAdmin",
                "manageAuthorAdmin"
        );

        for (String permission : permissions) {
            Optional<Permission> optional = permissionRepository.findByName(permission);
            if (optional.isEmpty()) {
                throw new NotFoundException("no such permission: " + permission);
            }
            admin.getPermissions().add(optional.get());
        }
        userRepository.save(admin);
    }
}
