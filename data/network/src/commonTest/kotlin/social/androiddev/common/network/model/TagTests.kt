package social.androiddev.common.network.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TagTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "name": "nowplaying",
            "url": "https://mastodon.social/tags/nowplaying",
            "history":
            [
                {
                    "day": "1574553600",
                    "uses": "200",
                    "accounts": "31"
                },
                {
                    "day": "1574467200",
                    "uses": "272",
                    "accounts": "39"
                },
                {
                    "day": "1574380800",
                    "uses": "345",
                    "accounts": "40"
                },
                {
                    "day": "1574294400",
                    "uses": "366",
                    "accounts": "46"
                },
                {
                    "day": "1574208000",
                    "uses": "226",
                    "accounts": "32"
                },
                {
                    "day": "1574121600",
                    "uses": "217",
                    "accounts": "42"
                },
                {
                    "day": "1574035200",
                    "uses": "214",
                    "accounts": "34"
                }
            ]
        }
        """.trimIndent()

        // when
        val tag = Json.decodeFromString<Tag>(json)

        // then
        val day1 = History("1574553600", "200", "31")
        val day2 = History("1574467200", "272", "39")
        val day3 = History("1574380800", "345", "40")
        val day4 = History("1574294400", "366", "46")
        val day5 = History("1574208000", "226", "32")
        val day6 = History("1574121600", "217", "42")
        val day7 = History("1574035200", "214", "34")

        assertEquals(expected = "nowplaying", actual = tag.name)
        assertEquals(expected = "https://mastodon.social/tags/nowplaying", actual = tag.url)
        assertEquals(
            expected = listOf(
                day1,
                day2,
                day3,
                day4,
                day5,
                day6,
                day7,
            ),
            actual = tag.history
        )
    }
}
