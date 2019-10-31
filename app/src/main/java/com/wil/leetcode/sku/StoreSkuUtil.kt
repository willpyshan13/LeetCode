package com.will.ndklearn.sku

import com.wil.leetcode.sku.StoreDetailSkuEntity
import java.util.*

class StoreSkuUtil {
    companion object {
        fun getSkuCollection(data: Map<String, StoreDetailSkuEntity>): Map<String, StoreDetailSkuEntity> {
            val map: HashMap<String, StoreDetailSkuEntity> = HashMap()
            for ((key, value) in data) {
                val skuKeyAttrs = key.split(",")
                val combArr = combInArray(skuKeyAttrs)

                combArr?.let {
                    if (combArr.isNotEmpty()){
                        for (i in 0 until combArr.size) {
                            add2SKUResult(map, combArr[i], value)
                        }
                    }
                }

                map[key] = value
            }

            return map
        }

        /**
         *
         * Desc:添加到数组中
         * <p>
         * Author: pengyushan
         * Date: 2019-10-30
         * @param result HashMap<String, StoreDetailSkuEntity>
         * @param newKeyList ArrayList<String>
         * @param skuModel StoreDetailSkuEntity
         */
        private fun add2SKUResult(result: HashMap<String, StoreDetailSkuEntity>, newKeyList: ArrayList<String>, skuModel: StoreDetailSkuEntity) {
            val key = join(",", newKeyList)
            if (result.keys.contains(key)) {
                if (result[key]!!.isSkuSaleing) {
                    result[key]!!.skuStatus = 1
                }
            } else {
                result[key] = skuModel
            }
        }

        private fun combInArray(skuKeyAttrs: List<String>): ArrayList<ArrayList<String>>? {
            if (skuKeyAttrs.isNullOrEmpty()) {
                return null
            }
            val aResult: ArrayList<ArrayList<String>> = ArrayList()
            val len = skuKeyAttrs.size
            for (key in 1 until len) {
                val aaFlags = getCombFlags(len, key)
                for (i in 0 until aaFlags.size) {
                    val aFlag = aaFlags[i]
                    val aComb: ArrayList<String> = ArrayList()
                    for (j in aFlag.indices) {
                        if (aFlag[j] == 1) {
                            aComb.add(skuKeyAttrs[j])
                        }
                    }
                    aResult.add(aComb)
                }
            }
            return aResult
        }

        private fun join(delimiter: CharSequence, tokens: List<*>): String {
            val sb: StringBuilder = StringBuilder()
            val it = tokens.listIterator()
            if (it.hasNext()) {
                sb.append(it.next())
                while (it.hasNext()) {
                    sb.append(delimiter)
                    sb.append(it.next())
                }
            }
            return sb.toString()
        }

        /**
         * 算法拆分组合 用1和0 的移位去做控制
         * （这块需要你打印才能看的出来）
         *
         * @param len      长度
         * @param n        位置
         * @return
         */
        private fun getCombFlags(len: Int, n: Int): ArrayList<List<Int>> {
            if (n <= 0) {
                return ArrayList()
            }
            val aResult: ArrayList<List<Int>> = ArrayList()

            val aFlag: ArrayList<Int> = ArrayList()
            var bNext = len >1
            var iCnt1: Int
            for (i in 0 until len) {
                aFlag.add((if (i < n) 1 else 0))
            }
            aResult.add(aFlag)
            while (bNext) {
                iCnt1 = 0
                for (i in 0 until len-1) {
                    if (aFlag[i] == 1 && aFlag[i + 1] == 0) {
                        for (j in 0 until i) {
                            aFlag[j] = (if (j < iCnt1) 1 else 0)
                        }
                        aFlag[i] = 0
                        aFlag[i + 1] = 1
                        aResult.add(aFlag)
                        if (!join("", aFlag).substring(len - n).contains("0")) {
                            bNext = false
                        }
                        if (aFlag[i] == 1) {
                            iCnt1++
                        }
                    }
                }
            }
            return aResult
        }
    }
}