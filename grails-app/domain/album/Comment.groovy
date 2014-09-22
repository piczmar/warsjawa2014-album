package album

class Comment {
	String text
	Date dateCreated
	Date lastUpdated
	static belongsTo = [picture: Picture]
	static constraints = {
		text maxSize: 200
	}
	static mapping = {
		autoTimestamp true
	}
}
