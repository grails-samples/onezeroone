package org.grails.onezeroone.entities

import groovy.transform.CompileStatic
import org.grails.onezeroone.CourseSubscriber
import org.grails.onezeroone.SubscriptionDay

@CompileStatic
class CourseSubscriberImpl implements CourseSubscriber {
    String email
    SubscriptionDay subscriptionDay
}
