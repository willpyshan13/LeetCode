package com.wil.leetcode.sku

/**
 * Desc:商家详情图片信息
 *
 *
 * Date: 2019-07-08 15:25
 * Copyright: Copyright (c) 2010-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [pengysh]
 */
class StoreDetailSkuEntity {
    /**
     * 价格
     */
    var price: Double? = null
    /**
     * id
     */
    var skuId: Int = 0
    /**
     * 库存
     */
    var stocks: Int = 0
    /**
     * 值
     */
    var values: String? = null

    var skuStatus: Int = 0


    /**
     * Desc:判断sku是否处于在售中
     *
     *
     * author: pengyushan
     * Date: 2019-10-16
     *
     * @return boolean
     */
    val isSkuSaleing: Boolean
        get() = skuStatus == 1 && (stocks > 0 || stocks == -99)

    /**
     * 购物篮符号
     */
    private val symbols = "S$"
}
