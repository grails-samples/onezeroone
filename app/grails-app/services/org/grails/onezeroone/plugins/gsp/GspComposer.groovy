package org.grails.onezeroone.plugins.gsp

import groovy.transform.CompileStatic
import org.grails.onezeroone.Email
import org.grails.onezeroone.EmailComposer
import org.grails.onezeroone.SubscriptionDay

@CompileStatic
class GspComposerService implements EmailComposer {

    @Override
    Email compose(SubscriptionDay day) {
        return null
    }
}