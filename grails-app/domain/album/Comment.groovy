package album

class Comment {
	String text
	Date dateCreated
	Date lastUpdated
	static belongsTo = [picture: Picture]
	static constraints = {
		text maxSize:200, widget:'textarea' // this will force textArea creation in scaffold views
	}
	static mapping = {
		autoTimestamp true
	}
}
