package org.grails.onezeroone


import groovy.transform.CompileStatic
import org.grails.onezeroone.usecase.DailyEmailUseCaseService

@CompileStatic
class DailyJobService {

    DailyEmailUseCaseService dailyEmailUseCaseService

    def cron() {
        dailyEmailUseCaseService.sendEmail()
    }
}
