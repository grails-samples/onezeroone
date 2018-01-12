package org.grails.onezeroone.plugins.gmail

import groovy.transform.CompileStatic
import org.grails.onezeroone.CourseSubscriber
import org.grails.onezeroone.Email
import org.grails.onezeroone.EmailService

@CompileStatic
class GmailService implements EmailService {

    @Override
    void send(List<CourseSubscriber> subscriberList, Email email) {

    }
}