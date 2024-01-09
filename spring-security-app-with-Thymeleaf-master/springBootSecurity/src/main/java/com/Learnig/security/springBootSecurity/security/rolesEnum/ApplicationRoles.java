package com.Learnig.security.springBootSecurity.security.rolesEnum;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.Learnig.security.springBootSecurity.security.rolesEnum.ApplicationsPermissions.*;

public enum ApplicationRoles {
    USER(Sets.newHashSet(USER_READ)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, ADMIN_READ, ADMIN_WRITE));

    public final Set<ApplicationsPermissions> permissions;

    ApplicationRoles(Set<ApplicationsPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationsPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> perm = getPermissions().stream()
                .map(role -> new SimpleGrantedAuthority(role.getPermission()))
                .collect(Collectors.toSet());
        perm.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return perm;
    }

}
