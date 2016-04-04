package ua.ck.solo.aidkit

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class DrugController {
    static scaffold = true
}
