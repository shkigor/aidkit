// Place your Spring DSL code here
beans = {
    userDetailsService(ua.ck.solo.aidkit.security.CustomUserDetailsService){
        grailsApplication = ref('grailsApplication')
    }
}
