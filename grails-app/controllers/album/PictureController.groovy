package album



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PictureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Picture.list(params), model:[pictureInstanceCount: Picture.count()]
    }

    def show(Picture pictureInstance) {
        respond pictureInstance
    }

    def create() {
        respond new Picture(params)
    }

    @Transactional
    def save(Picture pictureInstance) {
        if (pictureInstance == null) {
            notFound()
            return
        }

        if (pictureInstance.hasErrors()) {
            respond pictureInstance.errors, view:'create'
            return
        }

        pictureInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'picture.label', default: 'Picture'), pictureInstance.id])
                redirect pictureInstance
            }
            '*' { respond pictureInstance, [status: CREATED] }
        }
    }

    def edit(Picture pictureInstance) {
        respond pictureInstance
    }

    @Transactional
    def update(Picture pictureInstance) {
        if (pictureInstance == null) {
            notFound()
            return
        }

        if (pictureInstance.hasErrors()) {
            respond pictureInstance.errors, view:'edit'
            return
        }

        pictureInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Picture.label', default: 'Picture'), pictureInstance.id])
                redirect pictureInstance
            }
            '*'{ respond pictureInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Picture pictureInstance) {

        if (pictureInstance == null) {
            notFound()
            return
        }

        pictureInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Picture.label', default: 'Picture'), pictureInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'picture.label', default: 'Picture'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
