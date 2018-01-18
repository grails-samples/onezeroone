package org.grails.onezeroone.plugins.ui.controllers

import grails.testing.web.UrlMappingsUnitTest
import grails.web.mapping.UrlMappings
import spock.lang.Specification

class UrlMappingsSpec extends Specification implements UrlMappingsUnitTest<UrlMappings> {

    void "/subscribe => SubscribeController.subscribe"() {
        expect:
        verifyForwardUrlMapping("/subscribe", controller: 'subscribe', action: 'subscribe', method: 'POST')
    }
}
