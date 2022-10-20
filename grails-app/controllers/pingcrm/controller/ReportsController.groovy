package pingcrm.controller

import grails.plugin.springsecurity.annotation.Secured
import groovy.transform.CompileStatic

@CompileStatic
@Secured('IS_AUTHENTICATED_REMEMBERED')
class ReportsController {

    def index() { renderInertia 'Reports/Index' }

}