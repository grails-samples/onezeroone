package org.grails.onezeroone.plugins.ui.controllers

class UrlMappings {

    static mappings = {
        name home: "/"(view: '/index')
        name subscribe: "/subscribe"(controller: 'subscribe', action: 'subscribe', method: 'POST')

        name e500: "500"(view:'/error')
        name e404: "404"(view:'/notFound')
    }
}
