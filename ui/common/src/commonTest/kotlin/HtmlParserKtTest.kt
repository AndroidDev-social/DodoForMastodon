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
package social.androiddev.common.utils
import org.junit.Test
import kotlin.test.assertTrue

class HtmlParserKtTest {

    @Test
    fun renderHtml() {
        val result = """
           <p>Been debating for a few days, and I think its time to open the <a href="https://eggy.app/tags/relay" class="mention hashtag" rel="nofollow noopener noreferrer" target="_blank">#<span>relay</span></a> to anyone. If you're looking for a general Relay please feel free to add <a href="https://relay.eggy.app/inbox" rel="nofollow noopener noreferrer" target="_blank"><span class="invisible">https://</span><span class="">relay.eggy.app/inbox</span><span class="invisible"></span></a> to your instances. More information can be found <a href="https://relay.eggy.app" rel="nofollow noopener noreferrer" target="_blank"><span class="invisible">https://</span><span class="">relay.eggy.app</span><span class="invisible"></span></a>, but Its a general open relay for anybody to use. <a href="https://eggy.app/tags/mastoadmin" class="mention hashtag" rel="nofollow noopener noreferrer" target="_blank">#<span>mastoadmin</span></a> Only thing I'm missing is a metrics tracking type page.. Hoping to have something soon.</p>
    """.extractContentFromMicroFormat()

        assertTrue { result.spanStyles.size == 4 }
        assertTrue { result.paragraphStyles.isEmpty() }
        assertTrue { result.length == 393 }
    }
}
