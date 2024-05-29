/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.tasks.action

import org.opensearch.action.DocWriteResponse
import org.opensearch.action.index.IndexRequest
import org.opensearch.client.node.NodeClient
import org.opensearch.core.rest.RestStatus
import org.opensearch.rest.BytesRestResponse
import org.opensearch.rest.RestRequest
import org.opensearch.rest.RestResponse
import org.opensearch.rest.action.RestToXContentListener
import org.opensearch.tasks.TasksPlugin.Companion.TASKS_INDEX
import org.opensearch.tasks.index.TasksIndex
import org.opensearch.tasks.model.Task

internal class CreateTaskAction {

    companion object {
        fun buildResponse(request: RestRequest, client: NodeClient): RestResponse {
            // TODO use parseRequest to validate data into model
            var name = ""
            if (request.hasContent()) {
                name = request.contentParser().mapStrings()["name"].toString()
            }

            val task = Task(name, false)
//            val task = object {
//                val title = name
//                val isCompleted = false
//
//                override fun toString(): String = "{\"name\": $title, \"isCompleted\": $isCompleted}"
//            }
            println(task.toString())

            // TODO need to create action/CreateTaskRequest:ActionRequest
//            client.execute(CreateTaskRequest, request, RestToXContentListener)

//            val indexRequest = IndexRequest(TASKS_INDEX).source(task.title, task.isCompleted)
//            val actionFuture = client.index(indexRequest)
//            val response = actionFuture.actionGet(6000)

            val response = TasksIndex.createTask(task)
            var message = "-1"

//            if (response.result != DocWriteResponse.Result.CREATED) {
////                log.warn("$LOG_PREFIX:createReportInstance - response:$response")
//            } else {
//                message = response.id
//            }

//            message = "Hi ${name}! Your plugin is installed and working."
            return BytesRestResponse(RestStatus.OK, response)
        }
    }


    fun parseRequest(request: RestRequest) {

    }
}