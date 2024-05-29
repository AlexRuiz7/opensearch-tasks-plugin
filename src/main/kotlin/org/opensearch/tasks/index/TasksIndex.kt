package org.opensearch.tasks.index

import org.opensearch.action.DocWriteResponse
import org.opensearch.action.index.IndexRequest
import org.opensearch.client.Client
import org.opensearch.cluster.service.ClusterService
import org.opensearch.common.lifecycle.LifecycleListener
import org.opensearch.tasks.model.Task
import org.opensearch.tasks.TasksPlugin.Companion.TASKS_INDEX

internal object TasksIndex : LifecycleListener() {

    private lateinit var client: Client
    private lateinit var clusterService: ClusterService

    /**
     * Initialize the class. Invoked from plugin's main class.
     * @param client The OpenSearch client
     * @param clusterService The OpenSearch cluster service
     */
    fun initialize(client: Client, clusterService: ClusterService) {
        this.client = client
        this.clusterService = clusterService
    }

    /**
     * once lifecycle indicate start has occurred - instantiating system index creation
     */
    override fun afterStart() {
        super.afterStart()
        // create default index
        createIndex()
    }

    /**
     * Create index using the mapping and settings defined in resource
     */
    private fun createIndex() {
        TODO("Not yet implemented")
    }

    /**
     * Check if the index is created and available.
     * @param index
     * @return true if index is available, false otherwise
     */
    private fun indexExists(index: String): Boolean {
        val clusterState = clusterService.state()
        return clusterState.routingTable.hasIndex(index)
    }

    /**
     * Create task object
     *
     * @param task
     * @param id
     * @return object id if successful, otherwise null
     */
    fun createTask(task: Task /*ObservabilityObjectDoc*/, id: String? = null): String? {
//        createIndex()

        // TODO read data from the request. Parse into model
        val indexRequest = IndexRequest(TASKS_INDEX).source(task.title, task.isCompleted)
        val actionFuture = client.index(indexRequest)
        val response = actionFuture.actionGet()

        if (response.result != DocWriteResponse.Result.CREATED) {
            // TODO log error
            return null
        } else {
            return response.id
        }

    }


}