/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/preferences/
 */
@Serializable
data class Preferences(
    // required attributes
    @SerialName("posting:default:visibility") val postingVisibility: PostingVisibility,
    @SerialName("posting:default:sensitive") val postingSensitive: Boolean,
    @SerialName("posting:default:language") val postingLanguage: String? = null,
    @SerialName("reading:expand:media") val readingMedia: ReadingMedia,
    @SerialName("reading:expand:spoilers") val readingSpoilers: Boolean,

    // optional attributes
    @SerialName("history") val history: List<History>? = null,
)

enum class PostingVisibility {
    public, unlisted, private, direct
}

enum class ReadingMedia {
    default, show_all, hide_all
}
