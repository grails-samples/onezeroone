package org.grails.onezeroone.plugins.gsp

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import org.grails.onezeroone.Email
import org.grails.onezeroone.EmailComposer
import org.grails.onezeroone.SubscriptionDay

@CompileStatic
class GspComposerService implements EmailComposer, GrailsConfigurationAware {

    List<String> titles
    List<String> bodys
    List<String> days
    List<String> guides

    @Override
    Email compose(SubscriptionDay day) {
        return null
    }

    @Override
    void setConfiguration(Config co) {
        this.days = co.getProperty('onezeroone.email.days', List, [])
        this.titles = co.getProperty('onezeroone.email.titles', List, [])
        this.guides = co.getProperty('onezeroone.email.guides', List, [])
        this.bodys = co.getProperty('onezeroone.email.bodys', List, [])

        if ( !configurationValid() ) {
         //   throw new IllegalStateException('titles, days, guides, and bodys should have the same size. There should be an entry per SubscriptionDay ')
        }
    }

    boolean configurationValid() {
        days.size() == titles.size() == guides.size() == bodys.size() && (SubscriptionDay.values().size() - 1)
    }
}

class GspModel {
    String title
    String body
    String day
    String guideUrl

}