/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/card/
 */
@Serializable
data class Card(
    // base attributes
    @SerialName("url") val url: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("type") val type: CardType,

    // optional attributes
    @SerialName("author_name") val authorName: String? = null,
    @SerialName("author_url") val authorUrl: String? = null,
    @SerialName("provider_name") val providerName: String? = null,
    @SerialName("provider_url") val providerUrl: String? = null,
    @SerialName("html") val html: String? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("embed_url") val embedUrl: String? = null,
    @SerialName("blurhash") val blurhash: String? = null,
)

@Serializable
enum class CardType {
    link,
    photo,
    video,
    rich,
}
