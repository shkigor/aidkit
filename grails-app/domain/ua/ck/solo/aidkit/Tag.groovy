package ua.ck.solo.aidkit

import groovy.transform.ToString

@ToString(includes='name', includeNames=false, includePackage=false)
class Tag {

    String name
    User user

    static belongsTo = [ User, Drug ]
    static hasMany = [ drugs: Drug ]

    static constraints = {
        name blank: false
    }
}
