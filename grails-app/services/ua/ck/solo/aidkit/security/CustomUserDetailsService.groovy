package ua.ck.solo.aidkit.security

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import grails.transaction.Transactional
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import ua.ck.solo.aidkit.User

class CustomUserDetailsService extends GormUserDetailsService {

    @Override
    @Transactional(readOnly=true, noRollbackFor=[IllegalArgumentException, UsernameNotFoundException])
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        def user = User.findByUsernameOrEmail(username, username)
        if (!user) {
            log.warn "User not found: $username"
            throw new NoStackUsernameNotFoundException()
        }

        Collection<GrantedAuthority> authorities = loadAuthorities(user, username, loadRoles)
        createUserDetails user, authorities
    }
}
