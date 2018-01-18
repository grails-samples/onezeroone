package org.grails.onezeroone.usecase

import grails.testing.services.ServiceUnitTest
import org.grails.onezeroone.CourseSubscriberRepository
import org.grails.onezeroone.EmailComposer
import org.grails.onezeroone.EmailImpl
import org.grails.onezeroone.EmailService
import spock.lang.Specification

class DailyEmailUseCaseServiceSpec extends Specification implements ServiceUnitTest<DailyEmailUseCaseService> {

    void 'test the daily send email'() {
        given: 'mocked collaborators'
        service.emailComposer = Stub(EmailComposer) {
            compose(_) >> new EmailImpl()
        }
        service.courseSubscriberRepository = Mock(CourseSubscriberRepository) {
            findAllByDay(_) >> []
        }
        service.emailService = Mock(EmailService)

        when: 'executing the service to send the emails'
        service.sendEmail()

        then:
        7 * service.emailService.send(*_)
        7 * service.courseSubscriberRepository.moveToDay(*_)
    }
}
