package org.grails.onezeroone.repository.gorm

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.grails.onezeroone.SubscriptionDay
import org.grails.onezeroone.entities.CourseSubscriberImpl
import spock.lang.Specification

@Integration
@Rollback
class CourseSubscriberGormServiceIntegrationSpec extends Specification {

    CourseSubscriberGormService courseSubscriberGormService
    CourseSubscriberDataService courseSubscriberDataService

    void 'test find all courseSubscribers by day'() {
        given: 'a few courseSubscribers'
            courseSubscriberDataService.save('user1A@example.com', SubscriptionDay.ONE)
            courseSubscriberDataService.save('user1B@example.com', SubscriptionDay.ONE)
            courseSubscriberDataService.save('user2A@example.com', SubscriptionDay.TWO)
            courseSubscriberDataService.save('user3A@example.com', SubscriptionDay.THREE)
            courseSubscriberDataService.save('user4A@example.com', SubscriptionDay.FOUR)
            courseSubscriberDataService.save('user5A@example.com', SubscriptionDay.FIVE)
            courseSubscriberDataService.save('user6A@example.com', SubscriptionDay.SIX)
            courseSubscriberDataService.save('finished@example.com', SubscriptionDay.FINISHED)

        when: 'finding all by day'
            def result = courseSubscriberGormService.findAllByDay(SubscriptionDay.ONE)

        then:
            result != null
            result.size() == 2
            result.email == ['user1A@example.com', 'user1B@example.com']
            result.subscriptionDay.every { it == SubscriptionDay.ONE }
    }

    void 'move a list of courseSubscribers to another day'() {
        given: 'a few courseSubscribers'
            def day1A = CourseSubscriberGormEntityUtils.of(courseSubscriberDataService.save('user1A@example.com', SubscriptionDay.ONE))
            def day1B = CourseSubscriberGormEntityUtils.of(courseSubscriberDataService.save('user1B@example.com', SubscriptionDay.ONE))
            CourseSubscriberGormEntityUtils.of(courseSubscriberDataService.save('user2A@example.com', SubscriptionDay.TWO))
            CourseSubscriberGormEntityUtils.of(courseSubscriberDataService.save('user3A@example.com', SubscriptionDay.THREE))

        when: 'moving the courses from day ONE to day TWO'
            courseSubscriberGormService.moveToDay([day1A, day1B], SubscriptionDay.TWO)

        then:
            courseSubscriberGormService.findAllByDay(SubscriptionDay.TWO).size() == 3
    }

    void 'persist a courseSubscriber'() {
        given: 'a courseSubscriber'
            def courseSubscriber = new CourseSubscriberImpl(email, subscriptionDay)

        when: 'persisting it'
            courseSubscriberGormService.save(courseSubscriber)

        then: 'it is persisted'
            courseSubscriberGormService.findAllByDay(SubscriptionDay.FIVE).size() == 1
            courseSubscriberGormService.findAllByDay(SubscriptionDay.FIVE).first().email == email
            courseSubscriberGormService.findAllByDay(SubscriptionDay.FIVE).first().subscriptionDay == subscriptionDay

        where:
            email = 'user1@example.com'
            subscriptionDay = SubscriptionDay.FIVE
    }
}
