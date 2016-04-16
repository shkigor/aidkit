package ua.ck.solo.aidkit

class Structure {

    String title

    static belongsTo = [ Drug ]

    static hasMany = [ drugs: Drug ]

    static constraints = {
        title nullable: false
    }

    @Override
    public String toString() {
        return title
    }
}
