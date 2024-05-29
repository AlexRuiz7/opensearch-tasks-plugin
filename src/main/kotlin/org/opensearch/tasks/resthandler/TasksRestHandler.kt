/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.tasks.resthandler


import org.opensearch.client.node.NodeClient
import org.opensearch.core.rest.RestStatus
import org.opensearch.commons.utils.logger
import org.opensearch.rest.BaseRestHandler
import org.opensearch.rest.BytesRestResponse


import org.opensearch.rest.RestHandler.Route
import org.opensearch.rest.RestRequest
import org.opensearch.tasks.TasksPlugin.Companion.TASKS_URI

import org.opensearch.rest.RestRequest.Method.DELETE
import org.opensearch.rest.RestRequest.Method.GET
import org.opensearch.rest.RestRequest.Method.POST
import org.opensearch.rest.RestRequest.Method.PUT
import org.opensearch.tasks.action.CreateTaskAction

import org.opensearch.tasks.model.RestTag.OBJECT_ID_FIELD


class TasksRestHandler : BaseRestHandler() {
    companion object {
        private const val TASKS_ACTION = "tasks_actions"
        private val log by logger(TasksRestHandler::class.java)
    }

    override fun getName(): String = TASKS_ACTION

    override fun routes(): List<Route> {
        return listOf(
            /**
             * Create a new object
             * Request URL: POST TASKS_URI
             */
            Route(POST, TASKS_URI),
            /**
             * Update object
             * Request URL: PUT OBSERVABILITY_URL/{objectId}
             */
            Route(PUT, "$TASKS_URI/{$OBJECT_ID_FIELD}"),
            /**
             * Get object(s)
             * Request URL: GET TASKS_URI/{objectId}
             */
            Route(GET, "$TASKS_URI/{$OBJECT_ID_FIELD}"),
            Route(GET, TASKS_URI),
            /**
             * Delete object
             * Request URL: DELETE TASKS_URI/{objectId}
             */
            Route(DELETE, "$TASKS_URI/{$OBJECT_ID_FIELD}"),
            Route(DELETE, TASKS_URI)
        )
    }

    override fun prepareRequest(request: RestRequest, client: NodeClient): RestChannelConsumer {
        return when (request.method()) {
            POST -> {
                executePostRequest(request, client)
            }
            PUT -> {
                TODO("Not yet implemented")
//                 executePutRequest(request, client)
            }
            GET -> {
                TODO("Not yet implemented")
//                executeGetRequest(request, client)
            }
            DELETE -> {
                TODO("Not yet implemented")
//                executeDeleteRequest(request, client)
            }
            else -> RestChannelConsumer {
                it.sendResponse(BytesRestResponse(RestStatus.METHOD_NOT_ALLOWED, "${request.method()} is not allowed"))
            }
        }
    }

    private fun executePostRequest(request: RestRequest, client: NodeClient): RestChannelConsumer {
        return RestChannelConsumer {
            try {
                it.sendResponse(CreateTaskAction.buildResponse(request, client))
            } catch (e: Exception) {
                it.sendResponse(BytesRestResponse(it, e))
            }
        }
    }

//        return RestChannelConsumer { restChannel: RestChannel ->
//
//            client.index(
//                indexRequest,
//                object: ActionListener<IndexResponse> {
//                    override fun onResponse(indexResponse: IndexResponse) {
//
//                    }
//                }
//            )
//
//        }

//        if (response.result != DocWriteResponse.Result.CREATED) {
//            // TODO log error
//            return null
//        } else {
//            return response.id
//        }
//
//        client.exe
//
//        return RestChannelConsumer {
//            client.index(indexRequest)
//        }
//    }


}