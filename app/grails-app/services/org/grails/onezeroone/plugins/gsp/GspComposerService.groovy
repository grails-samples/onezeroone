package org.grails.onezeroone.plugins.gsp

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import grails.gsp.PageRenderer
import grails.web.mapping.LinkGenerator
import groovy.transform.CompileStatic
import org.grails.onezeroone.Email
import org.grails.onezeroone.EmailComposer
import org.grails.onezeroone.EmailImpl
import org.grails.onezeroone.SubscriptionDay
import org.springframework.context.MessageSource

@CompileStatic
class GspComposerService implements EmailComposer, GrailsConfigurationAware {

    LinkGenerator grailsLinkGenerator
    PageRenderer groovyPageRenderer
    MessageSource messageSource

    String from
    String replyTo

    @Override
    Email compose(SubscriptionDay day) {
        String url = grailsLinkGenerator.link(mapping: 'home', absolute: true)

        String subject = messageSource.getMessage("onezeroone.email.subject.${day.toString().toLowerCase()}",
                                                  [] as Object[],
                                                  Locale.getDefault())

        String dayTemplate = this.findSubscriptionDayTemplate(day)
        String emailBody = groovyPageRenderer.render(
            view: "/emails/${dayTemplate}",
            model: [
                url: url
            ]
        )

        return new EmailImpl(
            subject: subject,
            body: emailBody,
            from: from,
            replyTo: replyTo
        )
    }

    private String findSubscriptionDayTemplate(SubscriptionDay day) {
        "day${day.toString().toLowerCase()}"
    }

    @Override
    void setConfiguration(Config co) {
        from = co.getRequiredProperty('onezeroone.email.from', String)
        replyTo = co.getRequiredProperty('onezeroone.email.replyTo', String)
    }
}
