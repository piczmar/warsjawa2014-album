package album

import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource

class ImageController {

	ResourceLocator grailsResourceLocator
	
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
}
