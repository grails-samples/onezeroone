package org.grails.onezeroone

import groovy.transform.CompileStatic

@CompileStatic
class DailyEmailService {

    CourseSubscriberRepository courseSubscriberRepository
    EmailComposer emailComposer
    EmailService emailService

    void sendEmail() {
        for ( SubscriptionDay day : SubscriptionDay.values() ) {
            if ( day != SubscriptionDay.FINISHED ) {
                Email email = emailComposer.compose(day)
                List<CourseSubscriber> subscribers = courseSubscriberRepository.findAllByDay(day)
                emailService.send(subscribers, email)
                courseSubscriberRepository.moveToDay(subscribers, nextDay(day))
            }
        }
    }

    SubscriptionDay nextDay(SubscriptionDay day ){

    }
}
