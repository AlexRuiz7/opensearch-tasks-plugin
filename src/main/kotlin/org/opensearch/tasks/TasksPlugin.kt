/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.tasks

import org.opensearch.action.ActionRequest
import org.opensearch.client.Client
import org.opensearch.cluster.metadata.IndexNameExpressionResolver
import org.opensearch.cluster.node.DiscoveryNodes
import org.opensearch.cluster.service.ClusterService
import org.opensearch.common.settings.ClusterSettings
import org.opensearch.common.settings.IndexScopedSettings
import org.opensearch.common.settings.Settings
import org.opensearch.common.settings.SettingsFilter
import org.opensearch.core.action.ActionResponse
import org.opensearch.core.common.io.stream.NamedWriteableRegistry
import org.opensearch.core.xcontent.NamedXContentRegistry
import org.opensearch.env.Environment
import org.opensearch.env.NodeEnvironment
import org.opensearch.plugins.ActionPlugin
import org.opensearch.plugins.Plugin
import org.opensearch.repositories.RepositoriesService
import org.opensearch.rest.RestController
import org.opensearch.rest.RestHandler
import org.opensearch.script.ScriptService
import org.opensearch.tasks.index.TasksIndex
import org.opensearch.tasks.resthandler.TasksRestHandler
import org.opensearch.threadpool.ThreadPool
import org.opensearch.watcher.ResourceWatcherService
import java.util.function.Supplier


class TasksPlugin : Plugin(), ActionPlugin {
    // Implement the relevant Plugin Interfaces here

    companion object {
        const val PLUGINS_BASE_URI = "/_plugins"
        const val PLUGIN_NAME = "tasks"
        const val TASKS_URI = "$PLUGINS_BASE_URI/tasks"
        const val TASKS_INDEX = "tasks"
    }

    override fun createComponents(
        client: Client,
        clusterService: ClusterService,
        threadPool: ThreadPool,
        resourceWatcherService: ResourceWatcherService,
        scriptService: ScriptService,
        xContentRegistry: NamedXContentRegistry,
        environment: Environment,
        nodeEnvironment: NodeEnvironment,
        namedWriteableRegistry: NamedWriteableRegistry,
        indexNameExpressionResolver: IndexNameExpressionResolver,
        repositoriesServiceSupplier: Supplier<RepositoriesService>
    ): Collection<Any> {
        TasksIndex.initialize(client, clusterService)
        return emptyList()
    }

    override fun getRestHandlers(
        settings: Settings?,
        restController: RestController?,
        clusterSettings: ClusterSettings?,
        indexScopedSettings: IndexScopedSettings?,
        settingsFilter: SettingsFilter?,
        indexNameExpressionResolver: IndexNameExpressionResolver?,
        nodesInCluster: Supplier<DiscoveryNodes>?
    ): List<RestHandler> {
        return listOf(TasksRestHandler())
    }

    override fun getActions(): List<ActionPlugin.ActionHandler<out ActionRequest, out ActionResponse>> {
        return super.getActions()
    }
}
