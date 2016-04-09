import ua.ck.solo.aidkit.User
import ua.ck.solo.aidkit.bootstrap.BootStrapData

class BootStrap {

    def init = { servletContext ->
        if (!User.count()) BootStrapData.createData()
    }
    def destroy = {
    }
}
