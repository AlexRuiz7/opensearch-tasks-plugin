package org.opensearch.tasks.action

import org.opensearch.action.ActionRequest
import org.opensearch.action.ActionRequestValidationException
import org.opensearch.core.xcontent.ToXContent
import org.opensearch.core.xcontent.ToXContentObject
import org.opensearch.core.xcontent.XContentBuilder


internal class CreateTaskRequest : ActionRequest(), ToXContentObject {


    override fun validate(): ActionRequestValidationException {
        TODO("Not yet implemented")
    }

    override fun toXContent(builder: XContentBuilder?, params: ToXContent.Params?): XContentBuilder {
        TODO("Not yet implemented")
    }

}