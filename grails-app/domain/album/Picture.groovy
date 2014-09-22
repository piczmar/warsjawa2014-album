package album

class Picture {
	Long id
	String title
	byte[] data
	Date dateCreated
	Date lastUpdated
	
	static hasMany= [comments: Comment]
    static constraints = {
		title nullable: true
		data nullable: true, maxSize: 1024 * 1024 * 2// Limit upload file size to 2MB
    }
	static mapping = {
		autoTimestamp true
		table "PIC"
		id column: 'PIC_ID'
	}
	String filename(){
		Picture.filename(id)
	}
	static String filename(long id) {
        "${id}.jpeg"
    }

}
