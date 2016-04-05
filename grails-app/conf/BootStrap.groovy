import ua.ck.solo.aidkit.Role
import ua.ck.solo.aidkit.User
import ua.ck.solo.aidkit.UserRole

class BootStrap {

    def init = { servletContext ->
        if (!User.count()) createSampleData()
    }
    def destroy = {
    }

    private void createSampleData() {

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def testUser = new User(username: 'admin', password: 'admin', email: 'admin@solo.ck.ua')
        testUser.save(flush: true)

        UserRole.create testUser, adminRole, true

        assert User.count() == 1
        assert Role.count() == 2
        assert UserRole.count() == 1

    }
}
