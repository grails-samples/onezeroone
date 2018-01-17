package org.grails.onezeroone.usecase

import groovy.transform.CompileStatic
import org.grails.onezeroone.CourseSubscriber
import org.grails.onezeroone.CourseSubscriberRepository
import org.grails.onezeroone.Email
import org.grails.onezeroone.EmailComposer
import org.grails.onezeroone.EmailService
import org.grails.onezeroone.SubscriptionDay

@CompileStatic
class DailyEmailUseCaseService {

    CourseSubscriberRepository courseSubscriberRepository
    EmailComposer emailComposer
    EmailService emailService

    void sendEmail() {
        for (SubscriptionDay day : SubscriptionDay.values()) {
            if (!SubscriptionDay.hasFinished(day)) {
                List<CourseSubscriber> subscribers = courseSubscriberRepository.findAllByDay(day)
                Email email = emailComposer.compose(day)

                emailService.send(subscribers, email)
                courseSubscriberRepository.moveToDay(subscribers, SubscriptionDay.nextDay(day))
            }
        }
    }
}
