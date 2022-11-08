package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class CardTests {
    @Test
    fun `deserialize video card should succeed`() = runBlocking {
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
        Truth.assertThat(card.url).isEqualTo("https://www.youtube.com/watch?v=OMv_EPMED8Y")
        Truth.assertThat(card.title).isEqualTo("♪ Brand New Friend (Christmas Song!)")
        Truth.assertThat(card.description).isEqualTo("")
        Truth.assertThat(card.type).isEqualTo(CardType.video)
        Truth.assertThat(card.authorName).isEqualTo("YOGSCAST Lewis & Simon")
        Truth.assertThat(card.authorUrl).isEqualTo("https://www.youtube.com/user/BlueXephos")
        Truth.assertThat(card.providerName).isEqualTo("YouTube")
        Truth.assertThat(card.providerUrl).isEqualTo("https://www.youtube.com/")
        Truth.assertThat(card.html).isEqualTo("<iframe width=\"480\" height=\"270\" src=\"https://www.youtube.com/embed/OMv_EPMED8Y?feature=oembed\" frameborder=\"0\" allowfullscreen=\"\"></iframe>")
        Truth.assertThat(card.width).isEqualTo(480)
        Truth.assertThat(card.height).isEqualTo(270)
        Truth.assertThat(card.image).isEqualTo("https://files.mastodon.social/preview_cards/images/014/179/145/original/9cf4b7cf5567b569.jpeg")
        Truth.assertThat(card.embedUrl).isEqualTo("")
        Truth.assertThat(card.blurhash).isEqualTo("UvK0HNkV,:s9xBR%njog0fo2W=WBS5ozofV@")
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
        Truth.assertThat(card.url).isEqualTo("https://www.flickr.com/photos/tomfenskephotography/49088768431/")
        Truth.assertThat(card.title).isEqualTo("Oregon")
        Truth.assertThat(card.description).isEqualTo("")
        Truth.assertThat(card.type).isEqualTo(CardType.photo)
        Truth.assertThat(card.authorName).isEqualTo("Tom Fenske Photography")
        Truth.assertThat(card.authorUrl).isEqualTo("https://www.flickr.com/photos/tomfenskephotography/")
        Truth.assertThat(card.providerName).isEqualTo("Flickr")
        Truth.assertThat(card.providerUrl).isEqualTo("https://www.flickr.com/")
        Truth.assertThat(card.html).isEqualTo("")
        Truth.assertThat(card.width).isEqualTo(1024)
        Truth.assertThat(card.height).isEqualTo(427)
        Truth.assertThat(card.image).isEqualTo("https://files.mastodon.social/preview_cards/images/014/287/139/original/651b1c6976817824.jpeg")
        Truth.assertThat(card.embedUrl).isEqualTo("https://live.staticflickr.com/65535/49088768431_6a4322b3bb_b.jpg")
        Truth.assertThat(card.blurhash).isEqualTo("UnE{@jt6M_oIAhjYs+ayT2WBf9ayRkkDXAj[")
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
        Truth.assertThat(card.url).isEqualTo("https://www.theguardian.com/money/2019/dec/07/i-lost-my-193000-inheritance-with-one-wrong-digit-on-my-sort-code")
        Truth.assertThat(card.title).isEqualTo("‘I lost my £193,000 inheritance – with one wrong digit on my sort code’")
        Truth.assertThat(card.description).isEqualTo("When Peter Teich’s money went to another Barclays customer, the bank offered £25 as a token gesture")
        Truth.assertThat(card.type).isEqualTo(CardType.link)
        Truth.assertThat(card.authorName).isEqualTo("")
        Truth.assertThat(card.authorUrl).isEqualTo("")
        Truth.assertThat(card.providerName).isEqualTo("")
        Truth.assertThat(card.providerUrl).isEqualTo("")
        Truth.assertThat(card.html).isEqualTo("")
        Truth.assertThat(card.width).isEqualTo(0)
        Truth.assertThat(card.height).isEqualTo(0)
        Truth.assertThat(card.image).isEqualTo(null)
        Truth.assertThat(card.embedUrl).isEqualTo("")
        Truth.assertThat(card.blurhash).isEqualTo(null)
    }
}
