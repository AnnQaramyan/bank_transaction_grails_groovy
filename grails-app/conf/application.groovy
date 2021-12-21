grails.plugin.springsecurity.userLookup.userDomainClassName = 'grailstestapp.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'grailstestapp.UserRole'
grails.plugin.springsecurity.authority.className = 'grailstestapp.Role'
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/home/home'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/shutdown',       access: ['permitAll']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]