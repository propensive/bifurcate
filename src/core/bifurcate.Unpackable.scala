/*
    Bifurcate, version [unreleased]. Copyright 2024 Jon Pretty, Propensive OÜ.

    The primary distribution site is: https://propensive.com/

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
    file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.
*/

package bifurcate

import rudiments.*

object Unpackable:
  given [PackType: Unspoolable](using ClassTag[PackType]) => IArray[PackType] is Unpackable:
    type Wrap[Type] = Int => Type
    def unpack(spool: Spool): Int => IArray[PackType] = count =>
      IArray.create[PackType](count): array =>
        array.indices.each: index =>
          array(index) = PackType.unspool(spool)

  given [PackType: Unspoolable] => PackType is Unpackable:
    type Wrap[Type] = Type
    def unpack(spool: Spool): PackType = PackType.unspool(spool)

trait Unpackable:
  type Self
  type Wrap[_]
  type Result = Wrap[Self]
  def unpack(spool: Spool): Wrap[Self]
