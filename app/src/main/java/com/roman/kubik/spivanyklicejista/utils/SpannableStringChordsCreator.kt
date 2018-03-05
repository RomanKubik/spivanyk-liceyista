package com.roman.kubik.spivanyklicejista.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Created by kubik on 2/19/18.
 */

class SpannableStringChordsCreator @Inject constructor() {

    private val bracketsPattern: Pattern = Pattern.compile("(<\\S+>)")

    fun selectChords(text: String): SpannableString = attachSpan(SpannableString(text), Typeface.BOLD, findSelections(text))


    private fun findSelections(text: String): List<IntRange> {
        val selectionsList = mutableListOf<IntRange>()
        val matcher = bracketsPattern.matcher(text)
        while (matcher.find()) {
            val group = matcher.group(1)
            val start = text.indexOf(group, if (selectionsList.size > 0) selectionsList.last().last else 0)
            val end = start + group.length
            selectionsList.add(IntRange(start, end))
        }
        return selectionsList
    }

    private fun attachSpan(spannableString: SpannableString, spanTypeface: Int, spans: List<IntRange>): SpannableString {
        val click = object : ClickableSpan() {
            override fun onClick(v: View?) {

            }
        }

        for (range in spans)
            spannableString.setSpan(StyleSpan(spanTypeface), range.first, range.last, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }

}
