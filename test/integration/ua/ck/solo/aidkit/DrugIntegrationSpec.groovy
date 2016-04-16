package ua.ck.solo.aidkit

import grails.test.spock.IntegrationSpec

class DrugIntegrationSpec extends IntegrationSpec {

    User user
    Drug peroxide
    Drug validol
    Drug zelenka

    def setup() {
        user = new User(username: 'igor', email: 'igor@gmail.com', password: 'secret')
        user.save(failOnError: true)

        Structure liquid = new Structure(title: 'жидкость').save(failOnError: true)

        peroxide = new Drug(
                title: 'Перекис водню розчин',
                structure: liquid,
                endingDate: new Date('2016/12/01'),
                user: user
        ).save(failOnError: true)

        validol = new Drug(
                title: 'Валідол',
                structure: new Structure(title: "таблетки"),
                endingDate: new Date('2016/07/01'),
                user: user
        ).save(failOnError: true)

        zelenka = new Drug(
                title: 'Брильянтовий зелений',
                description: 'Зеленка',
                structure: liquid,
                endingDate: new Date('2017/06/01'),
                user: user
        ).save(failOnError: true)
    }

    def cleanup() {
    }

    def "Adding drugs to user links drug to user"() {

        given: "A brand new user"
        assert user.get(user.id) != null

        when: "Several drugs are added to the user"
        user.addToDrugs(peroxide)
        user.addToDrugs(validol)
        user.addToDrugs(zelenka)

        then: "The user has a list of drugs attached"
        3 == User.get(user.id).drugs.size()
    }

    def "Ensure drugs linked to a user can be retrieved"() {

        given: "A user with several drugs"
        user.addToDrugs(peroxide)
        user.addToDrugs(validol)
        user.addToDrugs(zelenka)
        user.save(failOnError: true)

        when: "The user is retrieved by their id"
        def foundUser = User.get(user.id)
        def sortedDrugContent = foundUser.drugs.collect { it.title }.sort()

        then: "The drugs appear on the retrieved user"
        sortedDrugContent == ['Брильянтовий зелений', 'Валідол', 'Перекис водню розчин']
    }

    def "Exercise tagging several drugs with various tags"() {

        given: "A user with a set of tags"
        def tagFirstHelp = new Tag(name: 'Перша допомога')
        def tagHeart = new Tag(name: 'Серцеві захворювання')
        user.addToTags(tagFirstHelp)
        user.addToTags(tagHeart)
        user.save(failOnError: true)

        when: "The user tags two new drugs"
        user.addToDrugs(peroxide)
        peroxide.addToTags(tagFirstHelp)

        user.addToDrugs(zelenka)
        zelenka.addToTags(tagFirstHelp)

        user.addToDrugs(validol)
        validol.addToTags(tagFirstHelp)
        validol.addToTags(tagHeart)

        then:
        user.tags*.name.sort() == ['Перша допомога', 'Серцеві захворювання']
        1 == peroxide.tags.size()
        1 == zelenka.tags.size()
        2 == validol.tags.size()

    }
}
