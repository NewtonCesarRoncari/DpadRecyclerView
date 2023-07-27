/*
 * Copyright 2022 Rúben Sousa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rubensousa.dpadrecyclerview.sample.ui.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rubensousa.dpadrecyclerview.sample.R
import com.rubensousa.dpadrecyclerview.sample.databinding.HorizontalAdapterListBinding
import com.rubensousa.dpadrecyclerview.sample.ui.model.ListModel
import com.rubensousa.dpadrecyclerview.sample.ui.widgets.common.MutableListAdapter
import com.rubensousa.dpadrecyclerview.sample.ui.widgets.item.ItemViewHolder
import com.rubensousa.dpadrecyclerview.sample.ui.widgets.list.DpadStateHolder

class HorizontalListAdapter(
    private val stateHolder: DpadStateHolder,
    private val config: HorizontalListConfig
) : MutableListAdapter<ListModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListModel>() {
            override fun areItemsTheSame(oldItem: ListModel, newItem: ListModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ListModel, newItem: ListModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val view =
                LayoutInflater.from(parent.context).inflate(config.itemLayoutId, parent, false)
            ItemViewHolder(view, view.findViewById(R.id.textView), config.animateFocusChanges)
        } else {
            HorizontalListViewHolder(
                HorizontalAdapterListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                ),
                config
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isItemGrid(position)) 1 else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HorizontalListViewHolder) {
            val item = getItem(position)
            holder.bind(item)
            stateHolder.restore(holder.recyclerView, item.title, holder.adapter)
        } else if (holder is ItemViewHolder) {
            holder.bind(getItem(position).items.first(), null)
        }
    }

    private fun isItemGrid(position: Int) = position in 3.rangeTo(18)

}