package org.grails.onezeroone.plugins.ui.controllers

import grails.testing.web.controllers.ControllerUnitTest
import org.grails.onezeroone.usecase.SubscribeUseCaseService
import spock.lang.Specification

class SubscribeControllerSpec extends Specification implements ControllerUnitTest<SubscribeController> {

    void 'the params are not correct and the validation fails'() {
        given: 'an invalid email'
        params.email = 'not-valid'

        when: 'executing the controller'
        request.method = 'POST'
        controller.subscribe()

        then:
        response.status == 302
    }

    void 'the user subscribes to the course'() {
        given: 'a valid email'
        params.email = email

        and: 'a mocked use case service'
        controller.subscribeUseCaseService = Mock(SubscribeUseCaseService)

        when: 'executing the controller'
        request.method = 'POST'
        controller.subscribe()

        then:
        response.status == 200
        1 * controller.subscribeUseCaseService.subscribe(email)
        view == '/subscribe/subscriptionConfirmation'

        where:
        email = 'john.doe@example.com'
    }
}
