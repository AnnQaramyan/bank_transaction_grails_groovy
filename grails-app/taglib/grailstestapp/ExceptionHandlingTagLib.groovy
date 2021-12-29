package grailstestapp

import org.grails.encoder.CodecLookup
import org.grails.encoder.Encoder
import org.grails.exceptions.ExceptionUtils
import org.grails.web.util.WebUtils
import org.springframework.http.HttpStatus
import org.springframework.util.StringUtils

class ExceptionHandlingTagLib {
    static namespace = "bs"
    CodecLookup codecLookup
    def fieldErrors = { attrs ->
        def bean = attrs['bean']
        def controlClass = attrs['class']
        if (bean) {
            out << eachError(attrs, {
                if (controlClass)
                    out << "<em class=\"${controlClass}\">${message(error: it)}</em>"
                else
                    out << "<em class=\"invalid\">${message(error: it)}</em>"
            })
        }
    }
    def exH = {
        Map attrs ->
            if (!(attrs?.exception instanceof Throwable)) {
                return
            }
            Throwable exception = (Throwable)attrs.exception

            Encoder htmlEncoder = codecLookup.lookupEncoder('HTML')

            def currentOut = out


            def root = ExceptionUtils.getRootCause(exception)
            currentOut << "<dt>Message</dt><dd>${htmlEncoder.encode(root.message)}</dd>"
    }
    private String prettyPrintStatus(int statusCode) {
        String httpStatusReason = HttpStatus.valueOf(statusCode).getReasonPhrase()
        "$statusCode: ${httpStatusReason}"
    }
}
