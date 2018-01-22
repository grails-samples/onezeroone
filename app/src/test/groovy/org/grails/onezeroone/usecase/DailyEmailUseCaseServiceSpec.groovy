package org.grails.onezeroone.usecase

import grails.testing.services.ServiceUnitTest
import org.grails.onezeroone.CourseSubscriberRepository
import org.grails.onezeroone.EmailComposer
import org.grails.onezeroone.EmailImpl
import org.grails.onezeroone.EmailService
import org.grails.onezeroone.SubscriptionDay
import org.grails.onezeroone.entities.CourseSubscriberImpl
import spock.lang.Specification

class DailyEmailUseCaseServiceSpec extends Specification implements ServiceUnitTest<DailyEmailUseCaseService> {

    void 'test the daily send email'() {
        given: 'mocked collaborators'
        service.emailComposer = Stub(EmailComposer) {
            compose(_) >> new EmailImpl()
        }
        service.courseSubscriberRepository = Mock(CourseSubscriberRepository) {
            findAllByDay(_) >> [new CourseSubscriberImpl(email: 'foo@email.com')]
        }
        service.emailService = Mock(EmailService)
        int expectedIterations = SubscriptionDay.values().size() - 1 // Substract FINISHED

        when: 'executing the service to send the templates'
        service.sendEmailToSubscribers()

        then:
        expectedIterations * service.emailService.send(*_)
    }
}
