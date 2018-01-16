package org.grails.onezeroone.plugins.gmail

import grails.plugins.mail.MailService
import grails.testing.services.ServiceUnitTest
import org.grails.onezeroone.Email
import org.grails.onezeroone.EmailImpl
import org.grails.onezeroone.SubscriptionDay
import org.grails.onezeroone.entities.CourseSubscriberImpl
import spock.lang.Specification

class GmailServiceSpec extends Specification implements ServiceUnitTest<GmailService> {

    void 'send an email to a list of subscribers'() {
        given: 'a list of courseSubscribers'
            def courseSubscriber1 = new CourseSubscriberImpl(email: 'user1@example.com', subscriptionDay: SubscriptionDay.FOUR)
            def courseSubscriber2 = new CourseSubscriberImpl(email: 'user2@example.com', subscriptionDay: SubscriptionDay.FOUR)
            def courseSubscriber3 = new CourseSubscriberImpl(email: 'user3@example.com', subscriptionDay: SubscriptionDay.FOUR)

        and: 'a mock for the emailService'
            service.mailService = Mock(MailService)

        and: 'an email to send'
            Email email = new EmailImpl()

        when: 'sending the email to the users'
            service.send([courseSubscriber1, courseSubscriber2, courseSubscriber3], email)

        then:
            3 * service.mailService.sendMail(_)
    }
}
