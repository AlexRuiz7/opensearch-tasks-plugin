/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.path.to.plugin

import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope
import org.apache.hc.core5.http.ParseException
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.hamcrest.Matchers.containsString
import org.opensearch.client.Request
import org.opensearch.client.Response
import org.opensearch.test.OpenSearchIntegTestCase
import java.io.IOException
import java.nio.charset.StandardCharsets

@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@OpenSearchIntegTestCase.ClusterScope(scope = OpenSearchIntegTestCase.Scope.SUITE)
class RenamePluginIT : OpenSearchIntegTestCase() {

    @Throws(IOException::class, ParseException::class)
    fun testPluginInstalled() {
        val response: Response = getRestClient().performRequest(Request("GET", "/_cat/plugins"))
        val body: String = EntityUtils.toString(response.entity, StandardCharsets.UTF_8)

        logger.info("response body: {}", body)
        assertThat(body, containsString("rename"))
    }
}
