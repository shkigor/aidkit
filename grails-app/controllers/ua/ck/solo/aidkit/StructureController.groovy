package ua.ck.solo.aidkit

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class StructureController {
    static scaffold = true
}
