package ua.ck.solo.aidkit

import groovy.transform.ToString

@ToString(includes='title', includeNames=false, includePackage=false)
class Structure {

    String title

    static hasMany = [ drugs: Drug ]

    static constraints = {
        title nullable: false
    }
}
