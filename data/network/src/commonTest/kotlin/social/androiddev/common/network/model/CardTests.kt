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

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class CardTests {
    @Test
    fun `deserialize video card should succeed`() {
        // given
        val json = """
        {
            "url": "https://www.youtube.com/watch?v=OMv_EPMED8Y",
            "title": "♪ Brand New Friend (Christmas Song!)",
            "description": "",
            "type": "video",
            "author_name": "YOGSCAST Lewis & Simon",
            "author_url": "https://www.youtube.com/user/BlueXephos",
            "provider_name": "YouTube",
            "provider_url": "https://www.youtube.com/",
            "html": "<iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/OMv_EPMED8Y?feature=oembed\" frameborder=\"0\" allowfullscreen=\"\"></iframe>",
            "width": 480,
            "height": 270,
            "image": "https://files.mastodon.social/preview_cards/images/014/179/145/original/9cf4b7cf5567b569.jpeg",
            "embed_url": "",
            "blurhash": "UvK0HNkV,:s9xBR%njog0fo2W=WBS5ozofV@"
        }
        """.trimIndent()

        // when
        val card = Json.decodeFromString<Card>(json)

        // then
        assertEquals(expected = "https://www.youtube.com/watch?v=OMv_EPMED8Y", actual = card.url)
        assertEquals(expected = "♪ Brand New Friend (Christmas Song!)", actual = card.title)
        assertEquals(expected = "", actual = card.description)
        assertEquals(expected = CardType.video, actual = card.type)
        assertEquals(expected = "YOGSCAST Lewis & Simon", actual = card.authorName)
        assertEquals(expected = "https://www.youtube.com/user/BlueXephos", actual = card.authorUrl)
        assertEquals(expected = "YouTube", actual = card.providerName)
        assertEquals(expected = "https://www.youtube.com/", actual = card.providerUrl)
        assertEquals(expected = "<iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/OMv_EPMED8Y?feature=oembed\" frameborder=\"0\" allowfullscreen=\"\"></iframe>", actual = card.html)
        assertEquals(expected = 480, actual = card.width)
        assertEquals(expected = 270, actual = card.height)
        assertEquals(expected = "https://files.mastodon.social/preview_cards/images/014/179/145/original/9cf4b7cf5567b569.jpeg", actual = card.image)
        assertEquals(expected = "", actual = card.embedUrl)
        assertEquals(expected = "UvK0HNkV,:s9xBR%njog0fo2W=WBS5ozofV@", actual = card.blurhash)
    }

    @Test
    fun `deserialize photo card should succeed`() = runBlocking {
        // given
        val json = """
        {
            "url": "https://www.flickr.com/photos/tomfenskephotography/49088768431/",
            "title": "Oregon",
            "description": "",
            "type": "photo",
            "author_name": "Tom Fenske Photography",
            "author_url": "https://www.flickr.com/photos/tomfenskephotography/",
            "provider_name": "Flickr",
            "provider_url": "https://www.flickr.com/",
            "html": "",
            "width": 1024,
            "height": 427,
            "image": "https://files.mastodon.social/preview_cards/images/014/287/139/original/651b1c6976817824.jpeg",
            "embed_url": "https://live.staticflickr.com/65535/49088768431_6a4322b3bb_b.jpg",
            "blurhash": "UnE{@jt6M_oIAhjYs+ayT2WBf9ayRkkDXAj["
        }
        """.trimIndent()

        // when
        val card = Json.decodeFromString<Card>(json)

        // then
        assertEquals(expected = "https://www.flickr.com/photos/tomfenskephotography/49088768431/", actual = card.url)
        assertEquals(expected = "Oregon", actual = card.title)
        assertEquals(expected = "", actual = card.description)
        assertEquals(expected = CardType.photo, actual = card.type)
        assertEquals(expected = "Tom Fenske Photography", actual = card.authorName)
        assertEquals(expected = "https://www.flickr.com/photos/tomfenskephotography/", actual = card.authorUrl)
        assertEquals(expected = "Flickr", actual = card.providerName)
        assertEquals(expected = "https://www.flickr.com/", actual = card.providerUrl)
        assertEquals(expected = "", actual = card.html)
        assertEquals(expected = 1024, actual = card.width)
        assertEquals(expected = 427, actual = card.height)
        assertEquals(expected = "https://files.mastodon.social/preview_cards/images/014/287/139/original/651b1c6976817824.jpeg", actual = card.image)
        assertEquals(expected = "https://live.staticflickr.com/65535/49088768431_6a4322b3bb_b.jpg", actual = card.embedUrl)
        assertEquals(expected = "UnE{@jt6M_oIAhjYs+ayT2WBf9ayRkkDXAj[", actual = card.blurhash)
    }

    @Test
    fun `deserialize link card should succeed`() = runBlocking {
        // given
        val json = """
        {
            "url": "https://www.theguardian.com/money/2019/dec/07/i-lost-my-193000-inheritance-with-one-wrong-digit-on-my-sort-code",
            "title": "‘I lost my £193,000 inheritance – with one wrong digit on my sort code’",
            "description": "When Peter Teich’s money went to another Barclays customer, the bank offered £25 as a token gesture",
            "type": "link",
            "author_name": "",
            "author_url": "",
            "provider_name": "",
            "provider_url": "",
            "html": "",
            "width": 0,
            "height": 0,
            "image": null,
            "embed_url": "",
            "blurhash": null
        }
        """.trimIndent()

        // when
        val card = Json.decodeFromString<Card>(json)

        // then
        assertEquals(expected = "https://www.theguardian.com/money/2019/dec/07/i-lost-my-193000-inheritance-with-one-wrong-digit-on-my-sort-code", actual = card.url)
        assertEquals(expected = "‘I lost my £193,000 inheritance – with one wrong digit on my sort code’", actual = card.title)
        assertEquals(expected = "When Peter Teich’s money went to another Barclays customer, the bank offered £25 as a token gesture", actual = card.description)
        assertEquals(expected = CardType.link, actual = card.type)
        assertEquals(expected = "", actual = card.authorName)
        assertEquals(expected = "", actual = card.authorUrl)
        assertEquals(expected = "", actual = card.providerName)
        assertEquals(expected = "", actual = card.providerUrl)
        assertEquals(expected = "", actual = card.html)
        assertEquals(expected = 0, actual = card.width)
        assertEquals(expected = 0, actual = card.height)
        assertEquals(expected = null, actual = card.image)
        assertEquals(expected = "", actual = card.embedUrl)
        assertEquals(expected = null, actual = card.blurhash)
    }
}
