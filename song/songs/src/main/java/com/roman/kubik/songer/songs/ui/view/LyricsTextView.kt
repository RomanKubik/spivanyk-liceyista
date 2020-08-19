package com.roman.kubik.songer.songs.ui.view

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import com.roman.kubik.songer.chords.ChordsFormatter
import com.roman.kubik.songs.R
import java.util.regex.Pattern

class LyricsTextView : MaterialTextView {

    companion object {
        private val bracketsPattern: Pattern = ChordsFormatter.bracketsPattern
        private val emptyLinePattern = Pattern.compile("(\\n\\W+\\n)")
    }

    @ColorInt
    var chordsColor: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    @ColorInt
    var chordsBackground: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    var chordsClickListener: ChordClickListener? = null
        set(value) {
            field = value
            movementMethod = LinkMovementMethod.getInstance()
        }

    var showChords: Boolean = true
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.textViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.LyricsTextView, defStyleAttr, 0).also {

            chordsColor = it.getColor(R.styleable.LyricsTextView_chordColor, ContextCompat.getColor(context, R.color.chord))
            chordsBackground = it.getColor(R.styleable.LyricsTextView_chordColorBackground, ContextCompat.getColor(context, R.color.chord_background))

            it.recycle()
        }
    }


    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(format(text.toString()), type)
    }

    private fun format(text: String): CharSequence {
        return if (showChords) {
            val selections = findSelections(text)
            val formattedText = clearFormatting(text)
            val spannableString = SpannableString(formattedText)
            attachClickableSpan(spannableString, selections)
        } else {
            removeChords(text)
        }
    }

    private fun findSelections(text: String): List<IntRange> {
        val selectionsList = mutableListOf<IntRange>()
        val matcher = bracketsPattern.matcher(text)
        while (matcher.find()) {
            matcher.group(1)?.apply {
                val start = text.indexOf(this, if (selectionsList.size > 0) selectionsList.last().last else 0)
                val end = if (start + this.length < text.length) start + this.length else text.length - 1
                selectionsList.add(IntRange(start, end))
            }
        }
        return selectionsList
    }

    private fun clearFormatting(text: String): String {
        var txt = text.replace('<', ' ')
        txt = txt.replace('>', ' ')
        return txt
    }

    private fun attachClickableSpan(spannableString: SpannableString, spans: List<IntRange>): SpannableString {
        for (range in spans) {
            val chord = spannableString.substring(range)
                    .replace(" ", "")
                    .replace("\n", "")
            val clickable = object : ClickableSpan() {
                override fun onClick(v: View) {
                    chordsClickListener?.onChordClicked(chord)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.bgColor = chordsBackground
                    ds.color = chordsColor
                    ds.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    ds.isUnderlineText = false
                }
            }
            spannableString.setSpan(clickable, range.first, range.last, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString
    }

    private fun removeChords(text: String): String {
        val matcher = ChordsFormatter.bracketsPattern.matcher(text)
        var str = text
        while (matcher.find()) {
            matcher.group(1)?.apply {
                str = str.replace(toRegex(), " ")
            }
        }
        return removeEmptyLines(str)
    }

    private fun removeEmptyLines(text: String): String {
        var str = text
        val matcher = emptyLinePattern.matcher(str)
        while (matcher.find()) {
            matcher.group(1)?.apply {
                str = str.replace(toRegex(), "\n")
            }
        }
        return str
    }
}