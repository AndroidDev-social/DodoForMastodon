package social.androiddev.common.network.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AttachmentTests {
    @Test
    fun `deserialize image example should succeed`() {
        // given
        val json = """
        {
            "id": "22345792",
            "type": "image",
            "url": "https://files.mastodon.social/media_attachments/files/022/345/792/original/57859aede991da25.jpeg",
            "preview_url": "https://files.mastodon.social/media_attachments/files/022/345/792/small/57859aede991da25.jpeg",
            "remote_url": null,
            "text_url": "https://mastodon.social/media/2N4uvkuUtPVrkZGysms",
            "meta":
            {
                "original":
                {
                    "width": 640,
                    "height": 480,
                    "size": "640x480",
                    "aspect": 1.3333333333333333
                },
                "small":
                {
                    "width": 461,
                    "height": 346,
                    "size": "461x346",
                    "aspect": 1.3323699421965318
                },
                "focus":
                {
                    "x": -0.27,
                    "y": 0.51
                }
            },
            "description": "test media description",
            "blurhash": "UFBWY:8_0Jxv4mx]t8t64.%M-:IUWGWAt6M}"
        }
        """.trimIndent()

        // when
        val attachment = Json.decodeFromString<Attachment>(json)

        // then
        assertNotNull(actual = attachment)
        assertEquals(expected = "22345792", actual = attachment.id)
        assertEquals(expected = AttachmentType.image, actual = attachment.type)
    }

    @Test
    fun `deserialize video example should succeed`() = runBlocking {
        // given
        val json = """
        {
            "id": "22546306",
            "type": "video",
            "url": "https://files.mastodon.social/media_attachments/files/022/546/306/original/dab9a597f68b9745.mp4",
            "preview_url": "https://files.mastodon.social/media_attachments/files/022/546/306/small/dab9a597f68b9745.png",
            "remote_url": null,
            "text_url": "https://mastodon.social/media/wWd1HJIBmH1MZGDfg50",
            "meta":
            {
                "length": "0:01:28.65",
                "duration": 88.65,
                "fps": 24,
                "size": "1280x720",
                "width": 1280,
                "height": 720,
                "aspect": 1.7777777777777777,
                "audio_encode": "aac (LC) (mp4a / 0x6134706D)",
                "audio_bitrate": "44100 Hz",
                "audio_channels": "stereo",
                "original":
                {
                    "width": 1280,
                    "height": 720,
                    "frame_rate": "6159375/249269",
                    "duration": 88.654,
                    "bitrate": 862056
                },
                "small":
                {
                    "width": 400,
                    "height": 225,
                    "size": "400x225",
                    "aspect": 1.7777777777777777
                }
            },
            "description": null,
            "blurhash": "U58E0g8_0M.94T?bIr00?bD%NGoM?bD%oLt7"
        }
        """.trimIndent()

        // when
        val attachment = Json.decodeFromString<Attachment>(json)

        // then
        assertNotNull(actual = attachment)
        assertEquals(expected = "22546306", actual = attachment.id)
        assertEquals(expected = AttachmentType.video, actual = attachment.type)
    }

    @Test
    fun `deserialize GIFV example should succeed`() = runBlocking {
        // given
        val json = """
        {
            "id": "21130559",
            "type": "gifv",
            "url": "https://files.mastodon.social/media_attachments/files/021/130/559/original/bc84838f77991326.mp4",
            "preview_url": "https://files.mastodon.social/media_attachments/files/021/130/559/small/bc84838f77991326.png",
            "remote_url": null,
            "text_url": "https://mastodon.social/media/2ICiasGyjezmi7cQYM8",
            "meta":
            {
                "length": "0:00:01.11",
                "duration": 1.11,
                "fps": 33,
                "size": "600x332",
                "width": 600,
                "height": 332,
                "aspect": 1.8072289156626506,
                "original":
                {
                    "width": 600,
                    "height": 332,
                    "frame_rate": "100/3",
                    "duration": 1.11,
                    "bitrate": 1627639
                },
                "small":
                {
                    "width": 400,
                    "height": 221,
                    "size": "400x221",
                    "aspect": 1.8099547511312217
                }
            },
            "description": null,
            "blurhash": "URHT%Jm,2a1d%MRO%LozkrNH${'$'}*n*oMnRjt7"
        }
        """.trimIndent()

        // when
        val attachment = Json.decodeFromString<Attachment>(json)

        // then
        assertNotNull(actual = attachment)
        assertEquals(expected = "21130559", actual = attachment.id)
        assertEquals(expected = AttachmentType.gifv, actual = attachment.type)
    }

    @Test
    fun `deserialize audio example should succeed`() = runBlocking {
        // given
        val json = """
        {
            "id": "21165404",
            "type": "audio",
            "url": "https://files.mastodon.social/media_attachments/files/021/165/404/original/a31a4a46cd713cd2.mp3",
            "preview_url": "https://files.mastodon.social/media_attachments/files/021/165/404/small/a31a4a46cd713cd2.mp3",
            "remote_url": null,
            "text_url": "https://mastodon.social/media/5O4uILClVqBWx0NNgvo",
            "meta":
            {
                "length": "0:06:42.86",
                "duration": 402.86,
                "audio_encode": "mp3",
                "audio_bitrate": "44100 Hz",
                "audio_channels": "stereo",
                "original":
                {
                    "duration": 402.860408,
                    "bitrate": 166290
                }
            },
            "description": null,
            "blurhash": null
        }
        """.trimIndent()

        // when
        val attachment = Json.decodeFromString<Attachment>(json)

        // then
        assertNotNull(attachment)
        assertEquals(expected = "21165404", actual = attachment.id)
        assertEquals(expected = AttachmentType.audio, actual = attachment.type)
    }
}
