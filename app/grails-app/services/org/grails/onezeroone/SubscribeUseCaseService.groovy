package org.grails.onezeroone

import groovy.transform.CompileStatic

@CompileStatic
class SubscribeUseCaseService {
    CourseSubscriberRepository courseSubscriberRepository

    void subscribe(CourseSubscriber courseSubscriber) {
        courseSubscriberRepository.save(courseSubscriber)
    }
}
