class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		"/my"(view: "/my/view")
		
		
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
