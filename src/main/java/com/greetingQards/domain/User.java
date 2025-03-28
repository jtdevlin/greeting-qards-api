package com.greetingQards.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    private String id;
    private String name;
    private String email;
    private List<String> roles;
    private LocalDateTime createdTimestamp;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    public void addRole(String role) {
        if(roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }
}
