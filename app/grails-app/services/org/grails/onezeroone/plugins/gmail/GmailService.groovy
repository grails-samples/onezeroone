package org.grails.onezeroone.plugins.gmail

import grails.plugins.mail.MailService
import groovy.transform.CompileStatic
import org.grails.onezeroone.CourseSubscriber
import org.grails.onezeroone.Email
import org.grails.onezeroone.EmailService

@CompileStatic
class GmailService implements EmailService {

    MailService mailService

    @Override
    void send(List<CourseSubscriber> subscriberList, Email email) {
        subscriberList.each { CourseSubscriber courseSubscriber ->
            log.info "sending email for day ${courseSubscriber.subscriptionDay} to ${courseSubscriber.email}"
            try {
                mailService.sendMail {
                    from email.from
                    to courseSubscriber.email
                    replyTo email.replyTo
                    subject email.subject
                    text email.body
                }
            } catch (Exception e) {
                log.error(e.message, e)
            }
        }
    }
}
