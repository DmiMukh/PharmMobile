package com.flyview.inventory_feature.data

import android.util.Log
import com.flyview.core.storage.SettingsStorage
import com.flyview.inventory_feature.domain.HOST
import com.flyview.inventory_feature.domain.request.DocumentRequest
import com.flyview.inventory_feature.domain.response.ArticulResponse
import com.flyview.inventory_feature.domain.response.CertificateResponse
import com.flyview.inventory_feature.domain.response.DocResultResponse
import com.flyview.inventory_feature.domain.response.MarkResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class InventoryApiImpl(
    private val client: HttpClient,
    private val storage: SettingsStorage
) : InventoryApi {

    private val host = storage.getString(HOST)

    override suspend fun getArticuls(limit: Long, offset: Long): List<ArticulResponse> {

        val response = client.get("http://${host}/v1/inventory/articuls") {
            url {
                parameters.append("limit", limit.toString())
                parameters.append("offset", offset.toString())
            }
        }

        if (response.status == HttpStatusCode.NoContent) return emptyList()

        return Json.decodeFromString(response.body())
    }

    override suspend fun getCertificates(
        limit: Long,
        offset: Long,
        stock: Int
    ): List<CertificateResponse> {
        val response = client.get("http://${host}/v1/inventory/certificates") {
            url {
                parameters.append("limit", limit.toString())
                parameters.append("offset", offset.toString())
                parameters.append("stock", stock.toString())
            }
        }

        if (response.status == HttpStatusCode.NoContent) return emptyList()

        return Json.decodeFromString(response.body())
    }

    override suspend fun getMarks(
        limit: Long,
        offset: Long,
        firm: Int,
        stock: Int
    ): List<MarkResponse> {
        val response = client.get("http://${host}/v1/inventory/marks") {
            url {
                parameters.append("limit", limit.toString())
                parameters.append("offset", offset.toString())
                parameters.append("firm", firm.toString())
                parameters.append("stock", stock.toString())
            }
        }

        if (response.status == HttpStatusCode.NoContent) return emptyList()

        return Json.decodeFromString(response.body())
    }

    override suspend fun putDocument(document: DocumentRequest): Long {
        val request = client.put("http://${host}/v1/inventory/document") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(document))
        }

        Log.d("TEST_LIST", request.status.toString())
        Log.d("TEST_LIST", request.body())

        val insertResult: DocResultResponse = Json.decodeFromString(request.body())

        return insertResult.id.toLong()
    }
}