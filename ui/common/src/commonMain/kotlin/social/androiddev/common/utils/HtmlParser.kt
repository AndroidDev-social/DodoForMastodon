package social.androiddev.common.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import io.ktor.util.escapeHTML
import it.skrape.core.htmlDocument
import it.skrape.selects.eachAttribute
import it.skrape.selects.eachHref
import it.skrape.selects.eachLink
import it.skrape.selects.eachText
import it.skrape.selects.html5.a
import it.skrape.selects.html5.p


/**
 * The tags to interpret. Add tags here and in [tagToStyle].
 */
private val tags = linkedMapOf(
    "<b>" to "</b>",
    "<i>" to "</i>",
    "<u>" to "</u>"
)

/**
 * The main entry point. Call this on a String and use the result in a Text.
 */
@Composable
fun String.renderHtml(modifier: Modifier) {
    val newlineReplace = this.replace("<br>", "\n")
    if (newlineReplace.contains("<p>")) {
        val content = htmlDocument(newlineReplace)
        val paragraph = content.p { findAll { eachText } }
        val links = try {
            content.a { findAll { eachLink } }
        } catch (exception: Exception) {
            emptyMap()
        }
        val attribute = try {
            content.a { findAll { eachAttribute } }
        } catch (exception: Exception) {
            emptyMap()
        }

        val href =
            try {
                content.a { findAll { eachHref } }
            } catch (exception: Exception) {
                listOf("")
            }

        paragraph.size
        links?.size

        val groups = paragraph.first().split(if(links.isNotEmpty())links.keys.first() else "")
        val before = groups[0]
        val after = if(groups.size > 1) groups[1] else ""

        val annotatedText = buildAnnotatedString {
            if(links.isNullOrEmpty()){
                append(before)
                append(after)
            }
            else if(before.isNullOrEmpty() && after.isNotEmpty()){
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                    appendLink(links.keys.first(), links.values.first())
                    append(after)
                }
            }
            else if (before == after && before == "") {
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                    appendLink(links.keys.first(), links.values.first())
                }
            } else if (before != after && after.isNullOrEmpty()) {
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                    appendLink(before, links.values.first())
                }
            } else {
                append(before)
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                    appendLink(links.keys.first(), links.values.first())
                }
                append(after)
            }
        }

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                annotatedText.onLinkClick(offset) { link ->
                    println("Clicked URL: $link")
                    // Open link in WebView.
                }
            }
        )
    }
}


fun AnnotatedString.Builder.appendLink(linkText: String, linkUrl: String) {
    pushStringAnnotation(tag = linkUrl, annotation = linkUrl)
    append(linkText)
    pop()
}

fun AnnotatedString.onLinkClick(offset: Int, onClick: (String) -> Unit) {
    getStringAnnotations(start = offset, end = offset).firstOrNull()?.let {
        onClick(it.item)
    }
}