package com.fxn.parser

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.XmlResourceParser
import android.graphics.Color
import android.util.AttributeSet
import android.util.Xml
import androidx.annotation.MenuRes
import com.fxn.bubbletabbar.R
import org.xmlpull.v1.XmlPullParser.*

internal class MenuParser(private val context: Context) {

    private companion object {
        const val XML_MENU_TAG = "menu"
        const val XML_MENU_ITEM_TAG = "item"
    }

    fun parse(@MenuRes menuRes: Int): List<MenuItem> {
        @SuppressLint("ResourceType")
        val parser = context.resources.getLayout(menuRes)
        val attrs = Xml.asAttributeSet(parser)

        skipMenuTagStart(parser)

        return parseMenu(parser, attrs)
    }

    private fun skipMenuTagStart(parser: XmlResourceParser) {
        var currentEvent = parser.eventType
        do {
            if (currentEvent == START_TAG) {
                val name = parser.name
                require(name == XML_MENU_TAG) { "Expecting menu, got $name" }
                break
            }
            currentEvent = parser.next()
        } while (currentEvent != END_DOCUMENT)
    }

    private fun parseMenu(parser: XmlResourceParser, attrs: AttributeSet): List<MenuItem> {
        val items = mutableListOf<MenuItem>()
        var eventType = parser.eventType
        var isEndOfMenu = false

        while (!isEndOfMenu) {
            val name = parser.name
            when {
                eventType == START_TAG && name == XML_MENU_ITEM_TAG -> {
                    val item = parseMenuItem(attrs)
                    items.add(
                        item
                    )
                }
                eventType == END_TAG && name == XML_MENU_TAG -> isEndOfMenu = true
                eventType == END_DOCUMENT -> throw RuntimeException("Unexpected end of document")

            }
            eventType = parser.next()
        }
        return items
    }

    private fun parseMenuItem(attrs: AttributeSet): MenuItem {
        val sAttr = context.obtainStyledAttributes(attrs, R.styleable.Bubble)

        val item = MenuItem(
            id = sAttr.getResourceId(R.styleable.Bubble_android_id, 0),
            title = sAttr.getText(R.styleable.Bubble_android_title),
            icon = sAttr.getResourceId(R.styleable.Bubble_android_icon, 0),
            enabled = sAttr.getBoolean(R.styleable.Bubble_android_enabled, true),
            checked = sAttr.getBoolean(R.styleable.Bubble_android_checked, false),
            iconColor = sAttr.getColor(R.styleable.Bubble_android_iconTint, Color.RED)
        )

        sAttr.recycle()
        return item
    }
}