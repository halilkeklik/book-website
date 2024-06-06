package com.halil.bookwebsite.seeds;

import com.halil.bookwebsite.entities.Permission;
import com.halil.bookwebsite.repositories.PermissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionSeeder {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @EventListener
    @Transactional
    public void LoadPermissions(ContextRefreshedEvent event) {

        List<String> permissions = List.of(
                "manageUserAdmin",
                "manageUserSelf",
                "manageReviewAdmin",
                "manageReviewUser",
                "manageCategoryAdmin",
                "manageBookAdmin",
                "manageAuthorAdmin"
        );
        for (String permission : permissions) {
            permissionRepository.save(Permission.builder().name(permission).build());
        }
        eventPublisher.publishEvent(new PermissionsSeededEvent(this));
    }
}
