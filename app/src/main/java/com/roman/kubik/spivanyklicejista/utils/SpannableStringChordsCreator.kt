package com.roman.kubik.spivanyklicejista.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Created by kubik on 2/19/18.
 */

class SpannableStringChordsCreator @Inject constructor() {

    private val bracketsPattern: Pattern = Pattern.compile("(<\\S+>)")

    fun selectChords(text: String, clickListener: OnChordClickListener): SpannableString {
        val selections = findSelections(text)
        val formattedText = clearFormatting(text)
        val spannableString = SpannableString(formattedText)
        return attachClickableSpan(spannableString, clickListener, selections)
    }


    private fun findSelections(text: String): List<IntRange> {
        val selectionsList = mutableListOf<IntRange>()
        val matcher = bracketsPattern.matcher(text)
        while (matcher.find()) {
            val chord = matcher.group(1)
            val start = text.indexOf(chord, if (selectionsList.size > 0) selectionsList.last().last else 0)
            val end = start + chord.length
            selectionsList.add(IntRange(start, end))
        }
        return selectionsList
    }

    private fun clearFormatting(text: String): String {
        var txt = text.replace('<', ' ')
        txt = txt.replace('>', ' ')
        return txt
    }

    private fun attachClickableSpan(spannableString: SpannableString, clickListener: OnChordClickListener, spans: List<IntRange>): SpannableString {

        for (range in spans) {
            val chord = spannableString.substring(range).removeSurrounding(" ")
            val clickable = object : ClickableSpan() {
                override fun onClick(v: View?) = clickListener.onChordClicked(chord)


                override fun updateDrawState(ds: TextPaint?) {
                    super.updateDrawState(ds)
                    ds?.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    ds?.isUnderlineText = false
                }
            }
            spannableString.setSpan(clickable, range.first, range.last, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString

    }
}
