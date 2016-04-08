package ua.ck.solo.aidkit

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class DrugController {
    static scaffold = true
}
