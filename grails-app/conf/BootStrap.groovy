import album.*
class BootStrap {

    def init = { servletContext ->
		(0..50).each{
			def p= new Picture(title: "title ${it}").save(failOnError:true)
			(0..1).each{
				new Comment(text: "Comment ${it}", picture: p).save(failOnError: true)
			}
		}
    }
    def destroy = {
    }
}
