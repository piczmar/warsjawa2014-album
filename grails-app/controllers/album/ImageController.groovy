package album

import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource
import album.spring.ImageService

class ImageController {

	ResourceLocator grailsResourceLocator
	ImageService oldSpringService

	def view() {
		def picture = Picture.get(params.id)
		if (picture) {

			if(picture.data){
				response.setContentType("image/jpeg")
				response.setContentLength(picture.data.size())
				response.setHeader('filename', picture.filename())
				OutputStream out = response.outputStream
				out.write(picture.data)
				out.close()
			}else{
				final Resource image = grailsResourceLocator.findResourceForURI('/images/empty.jpeg')
				render file: image.inputStream, fileName: 'empty.jpeg',contentType: 'image/jpeg'
			}
		}
		else {
			response.sendError(404)
		}
	}

	def rotateL(Picture pictureInstance){
		if(pictureInstance.data){
			pictureInstance.data = oldSpringService.transform(pictureInstance.data, ImageService.Operation.RotateAntiClockWise90)
			pictureInstance.save flush:true
			println "Rotate L"
		}
		redirect pictureInstance
	}
	def rotateR(Picture pictureInstance){
		if(pictureInstance.data){
			pictureInstance.data = oldSpringService.transform(pictureInstance.data, ImageService.Operation.RotateClockWise90)
			pictureInstance.save flush:true
			println "Rotate R"
		}
		redirect pictureInstance
	}
}
