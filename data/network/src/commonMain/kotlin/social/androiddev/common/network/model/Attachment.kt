/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/attachment/
 */
@Serializable
data class Attachment(
    // required attributes
    val id: String,
    val type: AttachmentType,
    val url: String,
    @SerialName("preview_url") val previewUrl: String,

    // optional attributes
    @SerialName("remote_url") val remoteUrl: String? = null,
    val meta: Hash? = null,
    val description: String? = null,
    val blurhash: String? = null,

    // deprecated attributes
    @Deprecated("Not used anymore")
    @SerialName("text_url") val textUrl: String? = null
)

enum class AttachmentType {
    unknown,
    image,
    gifv,
    video,
    audio,
}

@Serializable
data class Hash(
    val original: Original? = null,
    val small: Small? = null,
    val focus: Focus? = null,
    val length: String? = null,
    val duration: Float? = null,
    val fps: Int? = null,
    val size: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val aspect: Float? = null,
    @SerialName("audio_encode") val audioEncode: String? = null,
    @SerialName("audio_bitrate") val audioBitrate: String? = null,
    @SerialName("audio_channels") val audioChannels: String? = null,
    val description: String? = null,
    val blurhash: String? = null,
)

@Serializable
data class Original(
    val width: Int? = null,
    val height: Int? = null,
    val size: String? = null,
    val aspect: Float? = null,
    val duration: Float? = null,
    val frame_rate: String? = null,
    val bitrate: Int? = null,
)

@Serializable
data class Small(
    val width: Int? = null,
    val height: Int? = null,
    val size: String? = null,
    val aspect: Float? = null,
)

@Serializable
data class Focus(
    val x: Float? = null,
    val y: Float? = null,
)
