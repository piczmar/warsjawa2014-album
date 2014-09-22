package album

class ImageTagLib{

	static namespace = "al"

	def pictureImg = { attrs ->
		def picture = attrs.remove('picture')
		out << createPictureImage(picture, attrs)
	}

	private def createPictureImage(picture, attrs) {

		def caption = attrs?.caption
		if (!caption) {
			caption = '...'
		}
		def alt = attrs?.remove('alt')
		if (!alt) {
			alt = caption
		}
		alt = alt.encodeAsHTML()
		def title = attrs?.remove('title')
		if (!title) {
			title = caption
		}
		title = title.encodeAsHTML()
		StringBuilder sb = new StringBuilder(40)
		sb.append("<img src=\"${createPictureLink(picture.id).encodeAsHTML()}\" alt=\"$alt\" title=\"$title\" ")
		if (attrs) {
			attrs.each { key, value ->
				sb.append(" $key=\"$value\"")
			}
		}
		sb.append(' />')
		sb.toString()
	}

	private def createPictureLink(id) {
		def params = [filename: Picture.filename(id) ]
		createLink(url: [ controller: 'image', action: 'view', id: id, params: params ])
	}
}