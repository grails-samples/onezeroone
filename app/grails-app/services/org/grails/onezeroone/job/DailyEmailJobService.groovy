package org.grails.onezeroone.job

import com.agileorbit.schwartz.SchwartzJob
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.onezeroone.usecase.DailyEmailUseCaseService
import org.quartz.DateBuilder
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException

@Slf4j
@CompileStatic
class DailyEmailJobService implements SchwartzJob {

    private static final String JOB_NAME = 'DailyEmail'

    DailyEmailUseCaseService dailyEmailUseCaseService

    void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug "Executing ${JOB_NAME} at ${new Date()}"
        dailyEmailUseCaseService.sendEmail()
    }

    void buildTriggers() {
        triggers << factory(JOB_NAME)
            .startAt(DateBuilder.todayAt(7, 0, 0))
            .intervalInDays(1)
            .build()
    }
}
