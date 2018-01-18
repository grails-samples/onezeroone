package org.grails.onezeroone.job

import com.agileorbit.schwartz.QuartzService
import grails.testing.services.ServiceUnitTest
import org.grails.onezeroone.usecase.DailyEmailUseCaseService
import org.grails.spring.beans.factory.InstanceFactoryBean
import org.quartz.JobExecutionContext
import spock.lang.Specification

class DailyEmailJobServiceSpec extends Specification implements ServiceUnitTest<DailyEmailJobService> {

    Closure doWithSpring() {{ ->
        quartzService(InstanceFactoryBean, Mock(QuartzService), QuartzService)
    }}

    void 'test daily email job'() {
        given: 'a mocked service'
            service.dailyEmailUseCaseService = Mock(DailyEmailUseCaseService)

        when: 'executing the job'
            service.execute(Mock(JobExecutionContext))

        then:
            1 * service.dailyEmailUseCaseService.sendEmail()
    }
}
