import org.grails.onezeroone.plugins.gmail.GmailService
import org.grails.onezeroone.plugins.gsp.GspComposerService
import org.grails.onezeroone.repository.gorm.CourseSubscriberGormService

beans = {

    courseSubscriberRepository(CourseSubscriberGormService) {
        courseSubscriberDataService = ref('courseSubscriberDataService')
    }

    emailService(GmailService)
    emailComposer(GspComposerService)
}
