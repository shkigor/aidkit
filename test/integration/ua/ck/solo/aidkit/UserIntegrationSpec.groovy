package ua.ck.solo.aidkit

import grails.test.spock.IntegrationSpec

class UserIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    def "Saving our first user to the database"() {

        given: "A brand new user"
        def joe = new User(username: 'joe', email: 'joe@gmail.com', password: 'secret')

        when: "the user is saved"
        joe.save()

        then: "it saved successfully and can be found in the database"
        joe.errors.errorCount == 0
        joe.id != null
        User.get(joe.id).email == joe.email
        User.get(joe.id).username == joe.username
    }

    def "Updating a saved user changes its properties"() {

        given: "An existing user"
        def existingUser = new User(username: 'joe', email: 'joe@gmail.com', password: 'secret')
        existingUser.save(failOnError: true)

        when: "A property is changed"
        def foundUser = User.get(existingUser.id)
        foundUser.password = 'sesame'
        foundUser.save(failOnError: true)

        then: "The change is reflected in the database"
        User.get(existingUser.id).password == 'sesame'
    }

    def "Deleting an existing user removes it from the database"() {

        given: "An existing user"
        def user = new User(username: 'joe', email: 'joe@gmail.com', password: 'secret')
        user.save(failOnError: true)

        when: "The user is deleted"
        def foundUser = User.get(user.id)
        foundUser.delete(flush: true)

        then: "The user is removed from the database"
        !User.exists(foundUser.id)
    }

    def "Saving a user with invalid properties causes an error"() {

        given: "A user which fails several field validations"
        def user = new User(username: 'joe', password: '')

        when:  "The user is validated"
        user.validate()

        then:
        user.hasErrors()

        'nullable' == user.errors.getFieldError("email").code
        null == user.errors.getFieldError("password").rejectedValue
        !user.errors.getFieldError("username")
    }

    def "Recovering from a failed save by fixing invalid properties"() {

        given: "A user that has invalid properties"
        def chuck = new User(username: 'joe', password: 'secret')
        assert chuck.save()  == null
        assert chuck.hasErrors()

        when: "We fix the invalid properties"
        chuck.email = 'chuck@gmail.com'
        chuck.validate()

        then: "The user saves and validates fine"
        !chuck.hasErrors()
        chuck.save()
    }

    def "Ensure a user can have friends"() {

        given: "A set of baseline users"
        def igor = new User(username: 'igor', email: 'igor@gmail.com', password: 'secret').save()
        def dima = new User(username: 'dima', email: 'dima@gmail.com', password: 'secret').save()
        def vova = new User(username: 'vova', email: 'vova@gmail.com', password: 'secret').save()

        when: "Igor add friends Dima & Vova, and Dima add friend Vova"
        igor.addToFriends(dima)
        igor.addToFriends(vova)
        dima.addToFriends(vova)

        then: "Friends counts should match following people"
        2 == igor.friends.size()
        1 == dima.friends.size()
    }
}
