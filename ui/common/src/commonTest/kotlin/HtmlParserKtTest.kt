package social.androiddev.common.utils
import org.junit.Test
import kotlin.test.assertTrue

class HtmlParserKtTest {

   @Test
    fun renderHtml() {
     val result =   """
           <p>Been debating for a few days, and I think its time to open the <a href="https://eggy.app/tags/relay" class="mention hashtag" rel="nofollow noopener noreferrer" target="_blank">#<span>relay</span></a> to anyone. If you're looking for a general Relay please feel free to add <a href="https://relay.eggy.app/inbox" rel="nofollow noopener noreferrer" target="_blank"><span class="invisible">https://</span><span class="">relay.eggy.app/inbox</span><span class="invisible"></span></a> to your instances. More information can be found <a href="https://relay.eggy.app" rel="nofollow noopener noreferrer" target="_blank"><span class="invisible">https://</span><span class="">relay.eggy.app</span><span class="invisible"></span></a>, but Its a general open relay for anybody to use. <a href="https://eggy.app/tags/mastoadmin" class="mention hashtag" rel="nofollow noopener noreferrer" target="_blank">#<span>mastoadmin</span></a> Only thing I'm missing is a metrics tracking type page.. Hoping to have something soon.</p>
    """.renderHtml()

       assertTrue { result.spanStyles.size==4 }
       assertTrue { result.paragraphStyles.isEmpty() }
       assertTrue { result.length == 393 }
    }
}