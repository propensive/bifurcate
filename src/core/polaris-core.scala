/*
    Polaris, version [unreleased]. Copyright 2025 Jon Pretty, Propensive OÜ.

    The primary distribution site is: https://propensive.com/

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
    file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.
*/

package polaris

import anticipation.*

extension (bytes: Bytes)
  def unpackFrom[DataType: Unpackable](offset: Int): DataType.Result =
    DataType.unpack(Buffer(bytes, offset))

  def buffer[ResultType](lambda: Buffer ?=> ResultType): ResultType = lambda(using Buffer(bytes))

def unpack[ValueType: Unpackable](using buffer: Buffer): ValueType.Result =
  ValueType.unpack(buffer)

def byteWidth[DataType: Debufferable]: Int = DataType.width
