package ua.ck.solo.aidkit

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class DrugController {
    static scaffold = true

    static defaultAction = "home"

    SpringSecurityService springSecurityService

    def home(Integer max, String drugTitle) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = params.sort ?: Drug.declaredFields[0].name

        def drugs = Drug.where {
            user == springSecurityService.loadCurrentUser()
            if (drugTitle) {
                title =~ "%${drugTitle}%" || titleEn =~ "%${drugTitle}%"
            }
        }
        [ drugInstanceList: drugs.list(params), drugInstanceCount: drugs.count(), drugTitle: drugTitle ]
    }
}
