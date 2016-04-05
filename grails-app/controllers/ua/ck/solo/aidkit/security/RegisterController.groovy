package ua.ck.solo.aidkit.security

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
}
