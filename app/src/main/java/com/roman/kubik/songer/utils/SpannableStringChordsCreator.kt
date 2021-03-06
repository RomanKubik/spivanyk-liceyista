package com.roman.kubik.songer.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.VisibleForTesting
import com.roman.kubik.songer.domain.formatting.ChordsMarker
import com.roman.kubik.songer.domain.formatting.OnChordClickListener
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Created by kubik on 2/19/18.
 */

class SpannableStringChordsCreator @Inject constructor() : ChordsMarker {

    private val bracketsPattern: Pattern = Pattern.compile("(<\\S+>)")


    override fun format(text: String, clickListener: OnChordClickListener?, textColor: Int, backgroundColor: Int): CharSequence {
        val selections = findSelections(text)
        val formattedText = clearFormatting(text)
        val spannableString = SpannableString(formattedText)
        return attachClickableSpan(spannableString, clickListener, selections, textColor, backgroundColor)
    }

    @VisibleForTesting
    fun findSelections(text: String): List<IntRange> {
        val selectionsList = mutableListOf<IntRange>()
        val matcher = bracketsPattern.matcher(text)
        while (matcher.find()) {
            val chord = matcher.group(1)
            val start = text.indexOf(chord, if (selectionsList.size > 0) selectionsList.last().last else 0)
            val end = if (start + chord.length < text.length) start + chord.length else text.length - 1
            selectionsList.add(IntRange(start, end))
        }
        return selectionsList
    }

    private fun clearFormatting(text: String): String {
        var txt = text.replace('<', ' ')
        txt = txt.replace('>', ' ')
        return txt
    }

    private fun attachClickableSpan(spannableString: SpannableString, clickListener: OnChordClickListener?, spans: List<IntRange>, textColor: Int, backgroundColor: Int): SpannableString {

        for (range in spans) {
            val chord = spannableString.substring(range)
                    .replace(" ","")
                    .replace("\n", "")
            val clickable = object : ClickableSpan() {
                override fun onClick(v: View?) {
                    clickListener?.onChordClicked(chord)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.bgColor = backgroundColor
                    ds.color = textColor
                    ds.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    ds.isUnderlineText = false
                }
            }
            spannableString.setSpan(clickable, range.first, range.last, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString

    }
}
