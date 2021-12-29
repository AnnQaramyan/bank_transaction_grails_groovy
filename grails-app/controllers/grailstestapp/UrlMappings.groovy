package grailstestapp

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home", action: 'home')
        "500"(view:'/customError')
        "404"(view:'/notFound')
    }
}
