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

package com.rubensousa.dpadrecyclerview.test.layoutmanager.mock

import com.rubensousa.dpadrecyclerview.layoutmanager.alignment.LayoutAlignment
import io.mockk.every
import io.mockk.mockk

internal class LayoutAlignmentMock(private val parentKeyline: Int) {

    private val mock = mockk<LayoutAlignment>()

    init {
        every { mock.calculateScrollForAlignment(any()) }.answers { 0 }
        every { mock.getParentKeyline() }.answers { parentKeyline }
        every { mock.updateScrollLimits() }.answers {  }
    }

    fun get(): LayoutAlignment = mock

}
