// Place your Spring DSL code here
beans = {
    userDetailsService(ua.ck.solo.security.CustomUserDetailsService){
        grailsApplication = ref('grailsApplication')
    }
}
