package ua.ck.solo.aidkit

import groovy.transform.ToString

@ToString(includes='title', includeNames=false, includePackage=false)
class Drug {
    String title
    String titleEn
    Integer quantity = 1
    String description
    Structure structure
    String instructions
    String pathToImage
    Date endingDate
    Date dateCreated
    String comment

    static belongsTo = [ user: User ]

    static constraints = {
        title blank: false
        titleEn nullable: true
        quantity nullable: false
        description nullable: true
        structure nullable: false
        instructions nullable: true
        endingDate nullable: false
        comment nullable: true
        pathToImage nullable: true
    }

    static mapping = {
        sort title: "asc"
        instructions type: 'text'
    }
}
