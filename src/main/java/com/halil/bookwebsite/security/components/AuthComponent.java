package com.halil.bookwebsite.security.components;

import com.halil.bookwebsite.repositories.PermissionRepository;
import com.halil.bookwebsite.security.user.UserDetailsImpl;
import com.halil.bookwebsite.services.UserFunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component
public class AuthComponent {

    @Autowired
    private PermissionRepository permissionRepository;


    private UserDetailsImpl getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }


    public boolean hasPermission(String permissionName) {
        Collection<? extends GrantedAuthority> authorities = getAuthenticatedUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(permissionName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPermission(String permissionName, UserFunctionsService service, Long id) {
        UserDetailsImpl user = getAuthenticatedUser();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(permissionName) && service.checkCurrentUserHasEntity(user.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}
