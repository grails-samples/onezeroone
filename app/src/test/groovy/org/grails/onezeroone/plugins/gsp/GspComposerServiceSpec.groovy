package org.grails.onezeroone.plugins.gsp

import grails.gsp.PageRenderer
import grails.testing.services.ServiceUnitTest
import grails.web.mapping.LinkGenerator
import org.grails.onezeroone.SubscriptionDay
import org.springframework.context.MessageSource
import spock.lang.Specification

class GspComposerServiceSpec extends Specification implements ServiceUnitTest<GspComposerService> {

    Closure doWithConfig() {
        { config ->
            config.onezeroone.email.from = 'EMAIL_FROM'
            config.onezeroone.email.replyTo = 'EMAIL_REPLY_TO'
        }
    }

    void 'compose an email for one specific day'() {
        given: 'mocks for collaborators'
        service.grailsLinkGenerator = Stub(LinkGenerator) {
            link(_) >> url
        }
        service.messageSource = Stub(MessageSource) {
            getMessage(*_) >> subject
        }
        service.groovyPageRenderer = Stub(PageRenderer) {
            render([
                    view: '/emails/dayfive',
                    model: [url: url]
            ]) >> body
        }

        when: 'trying to render one day email'
        def email = service.compose(SubscriptionDay.FIVE)

        then: 'the email is rendered correctly'
        email != null
        email.subject == subject
        email.body == body
        email.from == 'EMAIL_FROM'
        email.replyTo == 'EMAIL_REPLY_TO'

        where:
        url = 'http://my-domain.com/'
        subject = 'The subject'
        body = 'The email body'
    }
}
