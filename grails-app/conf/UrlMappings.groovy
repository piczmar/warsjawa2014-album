class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		"/my"(view: "/my/view")
		"/image/$id/$filename?" (controller: "image", action: "view")
		
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
