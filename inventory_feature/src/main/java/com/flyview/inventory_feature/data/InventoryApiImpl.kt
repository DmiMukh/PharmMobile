package com.flyview.inventory_feature.data

import com.flyview.inventory_feature.domain.request.DocumentRequest
import com.flyview.inventory_feature.domain.response.ArticulResponse
import com.flyview.inventory_feature.domain.response.CertificateResponse
import com.flyview.inventory_feature.domain.response.MarkResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class InventoryApiImpl(private val client: HttpClient) : InventoryApi {

    private val host = "127.0.0.1:8080"

    override suspend fun getArticuls(limit: Long, offset: Long): List<ArticulResponse> {
        return client.get("http://${host}/v1/inventory/articuls") {
            url {
                parameters.append("limit", limit.toString())
                parameters.append("offset", offset.toString())
            }
        }.body()
    }

    override suspend fun getCertificates(limit: Long, offset: Long, stock: Int): List<CertificateResponse> {
        return client.get("http://${host}/v1/inventory/certificates") {
            url {
                parameters.append("limit", limit.toString())
                parameters.append("offset", offset.toString())
                parameters.append("stock", stock.toString())
            }
        }.body()
    }

    override suspend fun getMarks(limit: Long, offset: Long, stock: Int): List<MarkResponse> {
        return client.get("http://${host}/v1/inventory/marks") {
            url {
                parameters.append("limit", limit.toString())
                parameters.append("offset", offset.toString())
                parameters.append("stock", stock.toString())
            }
        }.body()
    }

    override suspend fun putDocument(document: DocumentRequest): Long {
        val url = "http://127.0.0.1:8080/v1/inventory/document"
        return client.put(url) {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(document))
        }.body()
    }
}