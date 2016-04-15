package ua.ck.solo.aidkit

class Structure {

    String title

    static hasMany = [ drugs: Drug ]

    static constraints = {
        title nullable: false
    }

    @Override
    public String toString() {
        return title
    }
}
