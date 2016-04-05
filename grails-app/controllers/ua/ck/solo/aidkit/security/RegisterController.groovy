package ua.ck.solo.aidkit.security

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.RegisterCommand

@Secured(['permitAll'])
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {

    @Override
    def register(RegisterCommand registerCommand) {
        registerCommand.username = registerCommand.email
        super.register(registerCommand)
    }
}
