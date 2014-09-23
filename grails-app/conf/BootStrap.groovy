import album.*
import album.security.*
class BootStrap {

    def init = { servletContext ->

	    def user1 = new User(username: 'user1', password: 'password').save(failOnError: true)
        def user2 = new User(username: 'user2', password: 'password').save(failOnError: true)
        def admin = new User(username: 'admin', password: 'password').save(failOnError: true)

        def userRole = new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        UserRole.create user1, userRole, true
        UserRole.create user2, userRole, true
        UserRole.create admin, adminRole, true


		(0..50).each{
			def p= new Picture(title: "title ${it}", createdBy: user1).save(failOnError:true)
			(0..1).each{
				new Comment(text: "Comment ${it}", picture: p).save(failOnError: true)
			}
		}
    }
    def destroy = {
    }
}
