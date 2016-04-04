package ua.ck.solo.aidkit

import groovy.transform.ToString

@ToString(includes='title', includeNames=false, includePackage=false)
class Drug {
    String title
    Integer quantity = 1
    String description
    String pathToImage
    Date endingDate
    Date dateCreated
    String comment

    static belongsTo = [ user: User ]

    static constraints = {
        title blank: false
        quantity nullable: false
        description nullable: true
        endingDate nullable: false
        comment nullable: true
        pathToImage nullable: true
    }

    static mapping = {
        sort title: "asc"
    }
}
