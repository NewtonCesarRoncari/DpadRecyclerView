package com.rubensousa.dpadrecyclerview

import android.os.Parcel
import android.os.Parcelable
import android.view.View

/**
 * Alignment configuration for aligning views in relation
 * to other sibling views inside the same RecyclerView
 */
data class ChildAlignment(
    /**
     * The id of the child view that should be used for the alignment.
     * If it is [View.NO_ID], then the root view will be used instead.
     * This is not necessarily the same view that will receive focus.
     */
    val alignmentViewId: Int = View.NO_ID,
    /**
     * The id of the child view that will receive focus during alignment.
     * Otherwise, [alignmentViewId] will be the one to receive focus.
     */
    private val focusViewId: Int = View.NO_ID,
    /**
     * Returns number of pixels to the end of the start edge.
     * In both LTR or vertical cases, it's the offset added to left/top edge.
     * In the RTL case, it's the offset subtracted from right edge.
     */
    val offset: Int = 0,
    /**
     * Sets the offset percent for item alignment in addition to the [offset].
     * Example: 40f means 40% of the width/height from the start edge.
     * In RTL it's 40% of the width from the right edge
     * Set [isPercentAlignmentEnabled] to false in case you want to disable this
     */
    val offsetPercent: Float = 50f,
    /**
     * True if padding should be included for the alignment.
     * Includes start/top padding if [offsetPercent] is 0.
     * Includes end/bottom padding if [offsetPercent] is 100.
     * If [offsetPercent] is not 0 or 100, padding isn't included
     */
    val includePadding: Boolean = false,
    /**
     *  When true, aligns to [View.getBaseline] for the view of with id equals [alignmentViewId]
     */
    val alignToBaseline: Boolean = false,
    /**
     * When enabled, [offsetPercent] will be used for the item alignment
     */
    val isPercentAlignmentEnabled: Boolean = true
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<ChildAlignment> {
        override fun createFromParcel(parcel: Parcel): ChildAlignment {
            return ChildAlignment(parcel)
        }

        override fun newArray(size: Int): Array<ChildAlignment?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(alignmentViewId)
        parcel.writeInt(focusViewId)
        parcel.writeInt(offset)
        parcel.writeFloat(offsetPercent)
        parcel.writeByte(if (includePadding) 1 else 0)
        parcel.writeByte(if (alignToBaseline) 1 else 0)
        parcel.writeByte(if (isPercentAlignmentEnabled) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun getFocusViewId(): Int {
        if (focusViewId != View.NO_ID) {
            return focusViewId
        }
        return alignmentViewId
    }

}
