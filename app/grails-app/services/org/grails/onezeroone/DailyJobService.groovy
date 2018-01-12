package org.grails.onezeroone


import groovy.transform.CompileStatic

@CompileStatic
class DailyJobService {

    DailyEmailService dailyEmailService

    def cron() {
        dailyEmailService.sendEmail()
    }
}
