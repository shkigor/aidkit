package ua.ck.solo.aidkit

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class DrugController {
    static scaffold = true

    static defaultAction = "home"

    SpringSecurityService springSecurityService

    def home(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def drugs = Drug.where {
            user == springSecurityService.loadCurrentUser()
        }
        [ drugInstanceList: drugs.list(params), drugInstanceCount: drugs.count() ]
    }
}
